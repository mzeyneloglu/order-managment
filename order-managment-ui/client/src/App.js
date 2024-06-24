
import MainStore from "./components/MainStore"
import Login from './components/Login'
import Register from './components/Register'
import Success from './components/Success'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import Customer, {CustomerInfo} from './components/Customer'
import Checkout from './components/Checkout'
import OrderDetails from "./components/OrderDetails"
import Account from "./components/Account"


function App() {

  return (
    <>
      <Router>
        <Switch>
          <Route path="/orderdetails">
            <OrderDetails />
          </Route>
          <Route path="/success">
            <Success />
          </Route>
          <Route path="/checkout">
            <Checkout />
          </Route>
          <Route path = "/customer">
          <Customer /></Route>

          <Route path="/store">
            <MainStore />
          </Route>
          <Route path="/account">
            <Account />
          </Route>

          <Route path="/register">
            <Register />

          </Route>
          <Route path="/">
            <Login />

          </Route>
        </Switch>
      </Router>

    </>
  )
}

export default App
