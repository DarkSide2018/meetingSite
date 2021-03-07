import React from "react";
import {
    BrowserRouter,
    Switch,
    Route,
    Link
} from "react-router-dom";


export default function Routing(){
    return (
        <BrowserRouter>
            <div>
                <nav>
                    <ul>
                        <li>
                            <Link to="/about"> About</Link>
                        </li>
                        <li>
                            <Link to="/home"> Home</Link>
                        </li>
                        <li>
                            <Link to="/users"> Users</Link>
                        </li>
                    </ul>
                </nav>
            </div>
            <Switch>
                <Route path="/about">
                    <About></About>
                </Route>
                <Route path="/users">
                    <Users></Users>
                </Route>
                <Route path="/home">
                    <Home></Home>
                </Route>
            </Switch>
        </BrowserRouter>
    )
}

function Home(){
    return <h2>Home</h2>
}
function About(){
    return <h2>About</h2>
}
function Users(){
    return <h2>Users</h2>
}
