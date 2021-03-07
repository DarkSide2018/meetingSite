import React from "react";


export class Routing extends React.Component {

    render() {
        return (
                <div>
                    <nav>
                        <ul>
                            <li>
                               About
                            </li>
                            <li>
                                <Home>

                                </Home>
                            </li>
                            <li>
                                 Users
                            </li>
                        </ul>
                    </nav>
                </div>
        )
    }
}

function Home() {
    return <h2>Home</h2>
}

function About() {
    return <h2>About</h2>
}

function Users() {
    return <h2>Users</h2>
}
