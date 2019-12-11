import React, { Component } from 'react';
import ChatComponent from './ChatComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import LoginComponent from './LoginComponent';
import RegisterComponent from './RegisterComponent';
import LogoutComponent from './LogoutComponent';
import MenuComponent from './MenuComponent';
import AuthenticatedRoute from './AuthenticatedRoute';
import Room1Component from "./Room1Component";
import Room2Component from "./Room2Component";
import Room3Component from "./Room3Component";

class InstructorApp extends Component {


    render() {
        return (
            <>
                <Router>
                    <>
                        <MenuComponent />
                        <Switch>
                            <Route path="/" exact component={LoginComponent} />
                            <Route path="/login" exact component={LoginComponent} />
                            <Route path="/register" exact component={RegisterComponent} />
                            <AuthenticatedRoute path="/logout" exact component={LogoutComponent} />
                            <AuthenticatedRoute path="/chat" exact component={ChatComponent} />
                            <AuthenticatedRoute path="/room1" exact component={Room1Component} />
                            <AuthenticatedRoute path="/room2" exact component={Room2Component} />
                            <AuthenticatedRoute path="/room3" exact component={Room3Component} />
                        </Switch>
                    </>
                </Router>
            </>
        )
    }
}

export default InstructorApp