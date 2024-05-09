from fastapi import FastAPI, BackgroundTasks
from pydantic import BaseModel
import speech_recognition as sr
import keyboard
import re

app = FastAPI()


class DecodedText(BaseModel):
    text: str


def recognize_speech():
    recognizer = sr.Recognizer()

    def _speech_to_text():
        try:
            with sr.Microphone() as source:
                print("Adjusting noise...")
                recognizer.adjust_for_ambient_noise(source, duration=1)
                print("Press 'r' to start recording, 'q' to stop.")
                keyboard.wait('r')  # Wait for 'r' key press to start recording
                print("Recording...")
                recorded_audio = recognizer.listen(source, timeout=None, phrase_time_limit=None)
                print("Recording stopped.")
                try:
                    print("Recognizing the text...")
                    text = recognizer.recognize_google(recorded_audio, language="tr-TR")
                    print("Decoded Text:", text)
                    return text  # Return decoded text

                except sr.UnknownValueError:
                    print("Google Speech Recognition could not understand the audio.")
                except sr.RequestError:
                    print("Could not request results from Google Speech Recognition service.")
        except Exception as ex:
            print("Error during recognition:", ex)

        return None  # Return None if no text is recognized

    decoded_text = _speech_to_text()
    if decoded_text:
        return decoded_text

class VoiceInfoResponse:
    def __init__(self, text):
        if not (text is None):
            self.ticketNo = text
            self.customerNo = 1
            self.quantity = 1
        else:
            self.ticketNo = None
            self.customerNo = None
            self.quantity = None


@app.post("/speech-to-text")
async def speech_to_text():
    text = recognize_speech()
    response = VoiceInfoResponse(text)
    if not (response is None):
        print(response.ticketNo)
        return {"voiceInfoResponse": response}
    else:
        return {"message": "Not recognised voice..."}
