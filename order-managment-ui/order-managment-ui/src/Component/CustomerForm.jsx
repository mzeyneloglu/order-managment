import React, { useState } from 'react';
import axios from 'axios';
import '../Styles/CustomerForm.css'


const CustomerForm = () => {
    const [formData, setFormData] = useState({
        customerName: '',
        customerSurname: '',
        customerEmail: '',
        customerPhoneNumber: '',
        customerAddress: ''
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8182/api/customer/create', formData);
            console.log('Customer created:', response.data);
        } catch (error) {
            console.error('Error creating customer:', error);
        }
    };
    return (
        <form onSubmit={handleSubmit}>
        <div className='container'>
            <div className='header'>
                <div className='text'>Sign Up</div>
                <div className='underline'></div>
            </div>
            <div className='inputs'>
                <div className='input'>
                    <input type='text' name="customerName" value={formData.customerName} onChange={handleChange} placeholder='   Name' />
                 </div>
                <div className='input'>
                    <input type='text' name="customerSurname" value={formData.customerSurname} onChange={handleChange} placeholder='   Surname' />
                </div>
                <div className='input'>
                    <input type='text' name="customerAddress" value={formData.customerAddress} onChange={handleChange} placeholder='   Address' />
                </div>
                <div className='input'>
                    <input type='text' name="customerPhone" value={formData.customerPhone} onChange={handleChange} placeholder='   Phone' />
                </div>
                <div className='input'>
                    <input type='text' name="customerEmail" value={formData.customerEmail} onChange={handleChange} placeholder='   Email' />
                </div>
            </div>
            <div>
                <button className="submit gray">Submit</button>
            </div>
        </div>
        </form>
    );
};
export default CustomerForm;
