import React from "react"
import ReactDOM from "react-dom"
import { Provider } from "react-redux"
import { Router, Route, IndexRoute, hashHistory } from "react-router";

import Main from "./components/Main.jsx"



import store from "./store"
import {Routing} from "./components/Routing";

require('file-loader?name=index.html!../index.html');

const app = document.getElementById('app')

ReactDOM.render(<Provider store={store}>
  <Router history={hashHistory}>
      <IndexRoute component={Main}/>      
      <Route path="/main" name="main" component={Main}></Route>
    <IndexRoute component={Routing}/>
    <Route path="/routing" name="routing" component={Routing}></Route>
  </Router>
</Provider>, app);
