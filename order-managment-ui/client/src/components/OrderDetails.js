import React, { useEffect, useState } from 'react';
import './css/OrderDetails.css';
import axios from '../util/axios';
import { Navbar } from './NavBar';
import { Card, CardContent, Typography, Button } from '@material-ui/core';
import Cookies from 'universal-cookie';

const cookies = new Cookies();
function OrderDetails() {
    const [orders, setOrders] = useState([]);
    const _customerId = cookies.get('customerId');

    const cancelOrder = (orderId, productName) => {
        var checkCancel = window.confirm(`${productName} isimli ürünü silmek istediğinizden emin misiniz ?`);
        if (checkCancel) {
            axios.post(`http://localhost:8183/api/order/delete-order/${orderId}`)
            .then((response) => {
                getOrders(); 
            })
            .catch((err) => {
                console.log("Server responded with error: ", err);
            });
        }  
    }
    const getOrders = () => {
        axios.get(`http://localhost:8183/api/order/get-orders/${_customerId}`)
        .then((response) => {
            setOrders(response.data.orders);
        })
        .catch((err) => {
            console.log("Server responded with error: ", err);
        });
    }
    useEffect(() => {
        getOrders(); 
    }, []);
    return (
        <>
            <section className="text-gray-700 body-font" data-testid="storePage">
                <Navbar />
                <h1 className="header" style={{ marginTop: "10vh" }}>Siparişlerim</h1>
                <section className="card__main" style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))', gap: '20px' }}>
                    {orders.length > 0 ? (
                        orders.map((order, index) => (
                            <Card key={index} className="order-card">
                                <CardContent>
                                    <Typography className="card-text" variant="h6" component="h2">
                                        <span style={{ fontWeight: "bold", fontFamily: "Bebas Neue", fontStyle: "normal" }}>{order.productClientResponse.productName}</span>
                                    </Typography>
                                    <Typography style={{ fontFamily: "Bebas Neue", fontStyle: "normal" }} color="textSecondary">
                                        <span>Sipariş Tarihi:</span> <span className='span1'>{order.dateOfOrder}</span>
                                    </Typography>
                                    <Typography style={{ fontFamily: "Bebas Neue", fontStyle: "normal" }} className="card-text" color="textSecondary">
                                        <span>Kategori:</span> {order.productClientResponse.productCategory}
                                    </Typography>
                                    <Typography style={{ fontFamily: "Bebas Neue", fontStyle: "normal" }} className="card-text" color="textSecondary">
                                        <span>Fiyat: $</span>{order.productClientResponse.productPrice}
                                    </Typography>
                                    <Typography style={{ fontFamily: "Bebas Neue", fontStyle: "normal" }} className="card-text" color="textSecondary">
                                        <span>Miktar:</span> {order.quantity}
                                    </Typography>
                                    <Typography style={{ fontFamily: "Bebas Neue", fontStyle: "normal" }} className="card-text" color="textSecondary">
                                        <span>Kurye Statü:</span> {order.courierClientResponse.packageStatus}
                                    </Typography>
                                    <img src={order.productClientResponse.imageUrl} alt={order.productClientResponse.productName} style={{ width: '90%', height: '100px', objectFit: 'contain', marginTop: '10px', borderRadius: '5px', mixBlendMode: 'multiply' }} />
                                    <Button
                                        id='buttonWrapper'
                                        variant="contained"
                                        color="secondary"
                                        onClick={() => cancelOrder(order.orderId, order.productClientResponse.productName)}
                                        style={{ marginTop: '10px', fontFamily: "Bebas Neue" }}
                                    >
                                        <p>Siparişi İptal Et</p>
                                    </Button>
                                </CardContent>
                            </Card>
                        ))
                    ) : (
                        <Typography variant="h6" style={{ marginLeft: "-6vh", fontFamily: "sans-serif" }}>
                            
                        </Typography>
                    )}
                </section>
                <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Poetsen+One&display=swap" />
            </section>
        </>
    )
}

export default OrderDetails;
