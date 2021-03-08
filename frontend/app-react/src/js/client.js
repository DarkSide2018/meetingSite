import React from "react"
import ReactDOM from "react-dom"
import {Provider} from "react-redux"
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Main from "./components/Main.jsx"


import store from "./store"
import {Routing} from "./components/Routing";
import {Dashboard} from "./components/Dashboard";

require('file-loader?name=index.html!../index.html');

const app = document.getElementById('app')

ReactDOM.render(<Provider store={store}>
    <Router>
        <Switch>
            <Route path="/">
                <Main/>
            </Route>
            <Route path="/routing">
                <Routing/>
            </Route>
        </Switch>
    </Router>
</Provider>, app);
