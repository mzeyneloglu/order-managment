import React from 'react'
import './css/Success.css'
import { useHistory } from 'react-router-dom';


function Success() {
    const history = useHistory();
    const goback = (e) => {
        e.preventDefault();
        history.push('/store');
    }
    return (
        <>
        <section className="main">
        
            <div className="success">

            <h2 className="header"> Başarılı </h2>
            <h2 className="desp">Daha fazla ayrıntı için e-postanızı kontrol edin!</h2>
            <button class="button" onClick={goback}>Alışverişe Devam Et</button>
            </div>
        </section>
        </>
    )
}
export default Success
