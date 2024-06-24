import React, { useState, useContext } from 'react';
import { FaMicrophoneSlash } from 'react-icons/fa';
import { FaMicrophone } from 'react-icons/fa6';
import axios from '../util/axios'
import './css/Microphone.css';
import Cookies from 'universal-cookie';
import { useHistory } from 'react-router-dom';
import Alert from '@mui/material/Alert';

const cookies = new Cookies();
function Microfon() {
    const history = useHistory();
    const [buttonState, setButtonState] = useState('mic-inactive');
    const [errorAlertOpen, setErrorAlertOpen] = useState(false); 
    const voiceOrder = () => {
        if (buttonState === 'mic-inactive') {    
            const response = axios.post('http://127.0.0.1:8000/start-recording', {})
            .then((response) => {
                
            })
            .catch((err) => {
                console.log("Server responded with error: ", err);
            });
            setButtonState("mic-active")
        } 
        if (buttonState === 'mic-active') {
            const response = axios.post('http://127.0.0.1:8000/stop-recording', {
                "customerId": cookies.get('customerId'),
                "quantity" : 1
            })
            .then((response) => {
                history.push('/success')
                console.log(response.data);
                
            })
            .catch((err) => {
                setErrorAlertOpen(true);
                console.log("Server responded with error: ", err);
            });
            setButtonState("mic-inactive")
        }  
 };
    return (
        <div className="microphone-container">
            {errorAlertOpen && (
                    <Alert style={{marginTop: "20px"}} severity="error" onClose={() => setErrorAlertOpen(false)}>Sipariş başarısız oldu !</Alert>
                  )}
            <button
                className={`button-mic ${buttonState}`}
                onClick={voiceOrder}
            >   
                {buttonState === 'mic-inactive' ? <FaMicrophoneSlash /> : <FaMicrophone />}
            </button>
        </div>
    );
  }

export default Microfon;