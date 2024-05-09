import sounddevice as sd
import speech_recognition as sr
import keyboard


def speech_to_text():
    recognizer = sr.Recognizer()

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


