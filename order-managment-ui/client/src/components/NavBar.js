import React, { useState } from "react";
import { Link, NavLink } from "react-router-dom";
import './css/Navbar.css'


export const Navbar = () => {
    const [menuOpen, setMenuOpen] = useState(false);

    return (
      <nav>
        <Link to="/store" className="title">
          BeetBazaar
        </Link>
        <div className="menu" onClick={() => setMenuOpen(!menuOpen)}>
          <span></span>
          <span></span>
          <span></span>
        </div>
        <ul className={menuOpen ? "open" : ""}>
         <li>
            <NavLink to="/orderdetails">Siparişlerim</NavLink>
          </li>
          <li>
            <NavLink to="/account">Hesap</NavLink>
          </li>
          <li>
            <NavLink to="/customer">Kullanıcı Bilgilerim</NavLink>
          </li>
          <li>
            <NavLink to="/login">Çıkış</NavLink>
          </li>
        </ul>
      </nav>
    );
  };