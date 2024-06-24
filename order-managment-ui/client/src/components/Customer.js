import React, { useState, useEffect } from "react"; 
import Alert from '@mui/material/Alert';
import { validateEmail } from '../util/validate';
import './css/Customer.css';
import Cookies from 'universal-cookie';
import { Navbar } from "./NavBar";
import axios from '../util/axios'

const cookies = new Cookies();
const PasswordErrorMessage = () => { 
 return ( 
   <p className="FieldError">Password should have at least 8 characters</p> 
 ); 
}; 

function CustomerInfo() { 
 const [firstName, setFirstName] = useState(""); 
 const [lastName, setLastName] = useState(""); 
 const [email, setEmail] = useState(""); 
 const [address, setAddress] = useState(""); 
 const [phone, setPhone] = useState(""); 
 const [password, setPassword] = useState({ 
   value: "", 
   isTouched: false, 
 }); 
 const [successAlertOpen, setSuccessAlertOpen] = useState(false); 
const [errorAlertOpen, setErrorAlertOpen] = useState(false); 
 const getIsFormValid = () => { 
   return ( 
     firstName && 
     validateEmail(email) && 
     password.value.length >= 8 
   ); 
 }; 

 /*
 
 const clearForm = () => { 
   setFirstName(""); 
   setLastName(""); 
   setEmail(""); 
   setAddress("");
   setPhone("");
   setPassword({ 
     value: "******", 
     isTouched: false, 
   }); 
 }; 

*/
 const customerId = cookies.get('customerId')

 const fetchUserInfo = async () => {
   try {
     const response = await axios.get(`http://localhost:8182/customer-service/api/customer/get${customerId}`);
     if(response.data){
       setFirstName(response.data.customerName);
       setLastName(response.data.customerSurname);
       setEmail(response.data.customerEmail);
       setAddress(response.data.customerAddress);
       setPhone(response.data.customerPhone);
     }
   } catch (error) {
     console.error(error);
   }
 };

 const handleSubmit = async (e) => {
   e.preventDefault();
   try {
     await axios.post(`http://localhost:8182/customer-service/api/customer/update-external`, {
      
     customerDto: {
      customerId: cookies.get('customerId'),
      customerName: firstName,
      customerSurname: lastName,
      customerEmail: email,
      customerPhone: phone,
      customerAddress: address
    }
     });
     setSuccessAlertOpen(true);
   } catch (error) {
     setErrorAlertOpen(true);
     console.log(error)
     
   }
 };

 useEffect(() => {
  fetchUserInfo(); 
}, []);

 return ( 
   <div className="App"> 
     <Navbar />
     <form onSubmit={handleSubmit}> 
       <fieldset> 
         <h2 style={{textAlign: "center", paddingBottom: "12px"}}>KULLANICI BILGILERI</h2> 
         <div className="Field"> 
           <label> 
             Ad <sup></sup> 
           </label> 
           <input 
             value={firstName}
             onChange={(e) => { 
               setFirstName(e.target.value); 
             }} 
           /> 
         </div> 
         <div className="Field"> 
           <label>Soyad</label> 
           <input 
             value={lastName}
             onChange={(e) => { 
               setLastName(e.target.value); 
             }}  
           /> 
         </div> 
         <div className="Field"> 
           <label> 
             Email 
           </label> 
           <input 
             value={email}
             onChange={(e) => { 
               setEmail(e.target.value); 
             }} 
           /> 
         </div>  
         <div className="Field"> 
           <label> 
             Adres
           </label> 
           <input 
             value={address}
             onChange={(e) => { 
               setAddress(e.target.value); 
             }}  
           /> 
         </div> 
         <div className="Field"> 
           <label> 
             Telefon
           </label> 
           <input 
             value={phone} 
             onChange={(e) => { 
               setPhone(e.target.value); 
             }} 
           /> 
         </div> 
         <div className="Field"> 
           <label> 
             Şifre
           </label> 
           <input 
             value={password.value}
             type="password" 
             onChange={(e) => { 
               setPassword({ ...password, value: e.target.value }); 
             }} 
             onBlur={() => { 
               setPassword({ ...password, isTouched: true }); 
             }} 
             placeholder="********"
           /> 
           {password.isTouched && password.value.length < 8 ? ( 
             <PasswordErrorMessage /> 
           ) : null} 
         </div> 
         <button id= "button" type="submit" disabled={!getIsFormValid()}> 
           GÜNCELLE
         </button> 
         {successAlertOpen && (
        <Alert  style={{marginTop: "20px"}} severity="success" onClose={() => setSuccessAlertOpen(false)}>Kullanıcı bilgileri başarıyla güncellendi !</Alert>
      )}
      {errorAlertOpen && (
        <Alert style={{marginTop: "20px"}} severity="error" onClose={() => setErrorAlertOpen(false)}>Kullanıcı bilgilerini güncelleme başarısız oldu !</Alert>
      )}
      
       </fieldset> 
     </form> 
   </div> 
 ); 
}

export default CustomerInfo;
