import React, { useState, useEffect } from "react"; 
import Alert from '@mui/material/Alert';
import { validateBalance } from '../util/validate';
import './css/Account.css';
import { Navbar } from "./NavBar";
import axios from '../util/axios'
import Cookies from 'universal-cookie';


const BalanceErrorMessage = () => { 
    return ( 
      <p className="FieldError">Balance should have at least 0 and maximum 99.000 amaount</p> 
    ); 
   }; 
   
const cookies = new Cookies();
function AccountInfo() { 
 const [accountName, setAccountName] = useState(""); 
 const [accountType, setAccountType] = useState(""); 
 const [walletName, setWalletName] = useState(""); 
 const [balance, setBalance] = useState({
    isTouched: false
 }); 
 const [createdAt, setCreatedAt] = useState(""); 
 const [expriyDate, setExpriyDate] = useState(""); 
 const [successAlertOpen, setSuccessAlertOpen] = useState(false); 
 const [errorAlertOpen, setErrorAlertOpen] = useState(false); 
 
 const getIsFormValid = () => { 
    return ( 
      validateBalance(balance) 
    ); 
  }; 

 const customerId = cookies.get("customerId");
 const fetchAccountInfo = async () => {
   try {
     const response = await axios.get(`http://localhost:8188/account-service/api/account/external/get-general-account/${customerId}`);
     if(response.data){
       setAccountName(response.data.accountName)
       setAccountType(response.data.accountType)
       setWalletName(response.data.walletName)
       setCreatedAt(response.data.createdAt)
       setExpriyDate(response.data.expiryDate)
       setBalance(response.data.balance)
     }
   } catch (error) {
     console.error(error);
   }
 };

 const handleSubmit = async (e) => {
   e.preventDefault();
   try {
     await axios.post(`http://localhost:8188/account-service/api/account/external/update-balance-by-customer-id`, {
        
        balance: balance,
        customerId: customerId          
     });
     setSuccessAlertOpen(true);
   } catch (error) {
     setErrorAlertOpen(true);
     console.log(error)
     
   }
 };

 useEffect(() => {
  fetchAccountInfo(); 
}, []);

 return ( 
   <div className="App"> 
     <Navbar />
     <form onSubmit={handleSubmit}> 
       <fieldset> 
         <h2 style={{textAlign: "center", paddingBottom: "12px"}}>HESAP BILGILERI</h2> 
         <div className="Field"> 
           <label> 
             Hesap Adı <sup></sup> 
           </label> 
           <input 
             value={accountName}
             onChange={(e) => { 
               setAccountName(e.target.value); 
             }} 
             disabled
             placeholder="Hesap Adı"
           /> 
         </div> 
         <div className="Field"> 
           <label> 
             Hesap Tipi 
           </label> 
           <input 
             value={accountType}
             onChange={(e) => { 
               setAccountType(e.target.value); 
             }} 
             disabled
             placeholder="Hesap Tipi"
           /> 
         </div> 
         <div className="Field"> 
           <label>Cüzdan</label> 
           <input 
             value={walletName}
             onChange={(e) => { 
               setWalletName(e.target.value); 
             }}
             disabled
             placeholder="Cüzdan"  
           /> 
         </div>  
         <div className="Field"> 
           <label> 
             Oluşturulma Tarihi
           </label> 
           <input 
             value={createdAt}
             onChange={(e) => { 
               setCreatedAt(e.target.value); 
             }}
             disabled 
             placeholder="Oluşturulma Tarihi" 
           /> 
         </div> 
         <div className="Field"> 
           <label> 
             Sona Erme Tarihi
           </label> 
           <input 
             value={expriyDate} 
             onChange={(e) => { 
               setExpriyDate(e.target.value); 
             }} 
             disabled
             placeholder="Sona Erme Tarihi"
           /> 
         </div>
         <div className="Field"> 
           <label> 
              Bakiye
           </label> 
           <input 
             value={balance}
             onChange={(e) => { 
               setBalance(e.target.value); 
             }} 
             placeholder="Bakiye"
           /> 
         </div>
         {balance.isTouched && !getIsFormValid() ? ( 
             <BalanceErrorMessage /> 
           ) : null}   
         <button id= "button" type="submit"  disabled={!getIsFormValid()}> 
           GÜNCELLE
         </button> 
         {successAlertOpen && (
        <Alert  style={{marginTop: "20px"}} severity="success" onClose={() => setSuccessAlertOpen(false)}>Hesap bilgileri başarıyla güncellendi !</Alert>
      )}
      {errorAlertOpen && (
        <Alert style={{marginTop: "20px"}} severity="error" onClose={() => setErrorAlertOpen(false)}>Hesap bilgilerini güncelleme başarısız oldu !</Alert>
      )}

       </fieldset> 
     </form> 
   </div> 
 ); 
}

export default AccountInfo;
