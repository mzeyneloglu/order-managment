import React,{ useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import './css/Login.css'
import Button from '@material-ui/core/Button';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'universal-cookie';
import Alert from '@mui/material/Alert';


const cookies = new Cookies();
function Login() {
    const [errorAlertOpen, setErrorAlertOpen] = useState(false); 
    const [name,setName] = useState("");
    const [password,setPassword] = useState("");
    const [error,setError] = useState(false);
    const history = useHistory();
    const useStyles = makeStyles((theme) => ({
        root: {
          '& > *': {
            margin: theme.spacing(1),
            width: '25ch',
          },
        },
        btn: {
            marginTop: "5ch"
        }
    }));
    const classes = useStyles();
    const submit = async (e) => {
        e.preventDefault();
        const response = await axios.post('http://localhost:8195/auth-service/api/auth/login', {
                username: name,
                password: password
        })
        .then((response) => {
            if(response.data){
                cookies.set("customerId", response.data.customerId)
                console.log(response.data)
                history.push('/store')
            }
     })
     .catch((err) => {
        setErrorAlertOpen(true);
        console.log("Server respondend with error: ", err);
    })
}
    const reg = () => {
    history.push("/register")
    }
    return (
        <>
            <section className="main-login" data-testid="mainpage">
                <h1 className="header">BEETBAZAAR </h1>
                {error ?(
                    <h1 className="wrong">Bilgiler Eksik veya Hatalı </h1>
                ) :" "}
                <form className={classes.root} noValidate autoComplete="off" data-testid="login-1">

                    <TextField id="standard-basic"
                     label="Kullanıcı Adı" 
                     name='kullanıcı Adı' 
                     value={name}
                     onChange={e => setName(e.target.value)}
                     data-testid="namefield" />

                    <TextField
                        id="standard-password-input"
                        label="Şifre"
                        type="password"
                        autoComplete="current-password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        data-testid="passwordField"
                    />

                {errorAlertOpen && (
                <Alert style={{marginTop: "20px"}} severity="error" onClose={() => setErrorAlertOpen(false)}>Kullanıcı adı yada şifre hatalı !</Alert>
                )}
                </form>
                <Button  className={classes.btn} variant="contained" onClick={submit}>Giriş</Button>
                <Button className={classes.btn} color="secondary" onClick={() => reg()}>BeetBazaar'li Degil misin? Kayit ol</Button>
            </section>
        </>
    )
}
export default Login
