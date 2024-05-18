from response.response_state import VoiceInfoResponse, RecognizerState, Request, OrderResponse
from fastapi import FastAPI, BackgroundTasks, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import speech_recognition as sr
import threading
import logging
import time
import requests
import uvicorn
import json


app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
recognizer = sr.Recognizer()
stop_recording_event = threading.Event()


recognizer_state = RecognizerState()

def recognize_speech(stop_event, state):
    try:
        with sr.Microphone() as source:
            recognizer.adjust_for_ambient_noise(source, duration=1)
            print("Recording...")
            recorded_audio = None
            while not stop_event.is_set():
                try:
                    recorded_audio = recognizer.listen(source, timeout=None, phrase_time_limit=10)
                except sr.WaitTimeoutError:
                    print("Listening timed out while waiting for phrase to start.")
                    continue
                try:
                    print("Recognizing the text...")
                    text = recognizer.recognize_google(recorded_audio, language="tr-TR")
                    print("Decoded Text:", text)
                    state.text = text
                    stop_event.set()  # Stop recording after successful recognition
                except sr.UnknownValueError:
                    print("Google Speech Recognition could not understand the audio.")
                except sr.RequestError as e:
                    print(f"Could not request results from Google Speech Recognition service; {e}")
    except Exception as ex:
        print(f"Error during recognition: {ex}")

async def start_recording():
    global stop_recording_event
    stop_recording_event.clear()
    recognizer_state.text = None
    recognize_speech(stop_recording_event, recognizer_state)

@app.post("/start-recording")
async def start_recording_api(background_tasks: BackgroundTasks):
    background_tasks.add_task(start_recording)
    return {"message": "Recording started"}


def make_request(url, data):
    response = requests.post(url, json=data)
    print(response)
    return response.json()

@app.post("/stop-recording")
async def stop_recording(request: Request):
    global stop_recording_event
    stop_recording_event.set()

    if recognizer_state.text:
        ticket_no = recognizer_state.text

        java_api_url = "http://localhost:9191/ai-service/api/ai/with-voice-order"
        request_body = {
            "speechText": ticket_no,
            "quantity": request.quantity,
            "customerId": request.customerId
        }
        try:
            logging.info(f"Request body: {request_body}")
            print(request_body)
            order_response_data = make_request(java_api_url, request_body)
            logging.info(f"Response data: {order_response_data}")
            order_response = OrderResponse(**order_response_data)
            print(order_response)
            return order_response
        except requests.exceptions.RequestException as e:
            logging.error(f"Request exception: {e}")
            raise HTTPException(status_code=500, detail=str(e))
        except Exception as e:
            logging.error(f"Unexpected error: {e}")
            raise HTTPException(status_code=500, detail="An unexpected error occurred.")
    else:
        raise HTTPException(status_code=400, detail="No recognized text found")


if __name__ == "__main__":
    uvicorn.run(app)
