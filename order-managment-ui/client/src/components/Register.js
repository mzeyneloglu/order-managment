import React, { useState } from 'react'
import './css/Register.css'
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import './css/Login.css'
import Button from '@material-ui/core/Button';
import { useHistory } from 'react-router-dom';
import './css/Register.css'
import axios from '../util/axios'
import Cookies from 'universal-cookie';


const cookies = new Cookies();
function Register() {
    const[name,setName] = useState("");
    const[surname,setSurname] = useState("");
    const[username,setUsername] = useState("");
    const[email, setEmail] = useState("");
    const[address, setAddress] = useState("");
    const[phone, setPhone] = useState("");
    const[password,setPassword] = useState("");
    const history = useHistory();
    const [error,setError] = useState(false);
    const useStyles = makeStyles((theme) => ({
        root: {
          '& > * .MuiTextField-root': {
            margin: theme.spacing(1),
            width: '25ch',
            flexDirection: 'column',
          },
        },
        btn: {
            marginTop: "5ch"
        }
    }));
    const classes = useStyles();
    const register = (e) => {
        e.preventDefault();
        if(!name || !surname || !username || !email || !address || !phone || !password){
            console.log("Please check your informatins!...")
        }
        const response = axios.post('http://localhost:8195/auth-service/api/auth/register', {
            name: name,
            surname: surname,
            username: username,
            email: email,
            address: address,
            phone: phone,
            password : password
         }) 
         .then((response) => {
                console.log(response.data)
                cookies.set('customerId', response.data, { path: '/' });
                history.push('/login')
         })
         .catch((err) => {
            console.log("Server respondend with error: ", err);
        })
   }
    return (
        <>
            <section className="main">
                <h1 className="header">Bit Degil BeetBazaar</h1>
                <form className="form-c" noValidate autoComplete="off">
                <TextField id="standard-basic" 
                        label="Ad" color="secondary" focused 
                        variant="filled"
                        value={name}
                        onChange={e => setName(e.target.value)}
                    />
                <TextField id="standard-basic" 
                        label="Soyad" color="secondary" focused 
                        name='surname' 
                        variant="filled"
                        value={surname}
                        onChange={e => setSurname(e.target.value)}
                    />
                <TextField 
                        id="standard-basic" 
                        label="Kullanıcı Adı" color="secondary" focused 
                        name='username' 
                        variant="filled"
                        value={username} 
                        onChange={e => setUsername(e.target.value)}
                />
                <TextField id="standard-basic" 
                        label="E-Posta" color="secondary" focused
                        variant="filled" 
                        name='email' 
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                />
                <TextField
                        id="standard-textarea"
                        label="Adres" color="secondary" focused
                        variant="filled"
                        name="address"
                        multiline
                        value={address}
                        onChange={e => setAddress(e.target.value)}
                />
                <TextField id="standard-basic"  
                        label="Telefon" color="secondary" focused
                        name='phone' 
                        variant="filled"
                        value={phone}
                        onChange={e => setPhone(e.target.value)}
                />
                <TextField
                        id="standard-password-input"
                        label="Şifre" color="secondary" focused
                        type="password"
                        variant="filled"
                        autoComplete="current-password"
                        value ={password}     
                        onChange={e => setPassword(e.target.value)}
                />
                </form>
                <Button 
                    size="medium"
                    color='success' 
                    className={classes.btn}
                    variant="contained"
                    onClick={register}>Kayıt Ol
                </Button>
            </section>

        </>
    )
}

export default Register
