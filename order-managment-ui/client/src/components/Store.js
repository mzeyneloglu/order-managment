import React, { useState } from 'react';
import StoreItem from "./StoreItem";
import Microfon from "./Microfon";
import './css/Store.css';
import Footer from './Footer';
import Cookies from 'universal-cookie';
import { useHistory } from 'react-router-dom';
import { Navbar } from './NavBar';


export default function Store({ items }) {
  const cookies = new Cookies();
  const name = cookies.get('username');
  const history = useHistory();

  return (
    <section className="text-gray-700 body-font" data-testid="storePage">

      <Navbar />
      <div className="container px-5 py-24 mx-auto">
        <div style={{ display: 'flex', alignItems: 'center' }}>
        </div>
        <div className="items flex flex-wrap -m-4" style={{ marginTop: "40px" }}>
          {items.map(item => (
            <StoreItem key={item.id} item={item} />
          ))}
        </div>
        <Microfon />
      </div>
      <Footer />
    </section>
  );
}
