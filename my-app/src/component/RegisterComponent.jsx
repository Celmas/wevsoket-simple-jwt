import React, { Component } from 'react'
import AuthenticationService from '../service/AuthenticationService';

class RegisterComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            login: '',
            email: '',
            password: ''
        }

        this.handleChange = this.handleChange.bind(this)
        this.registerClicked = this.registerClicked.bind(this)
    }

    handleChange(event) {
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    registerClicked() {
        AuthenticationService
            .executeJwtRegister(this.state.login, this.state.email, this.state.password)
            .then((response) => {
                console.log(response.data);
                //AuthenticationService.registerSuccessfulLoginForJwt(this.state.login, response.data.value)
                this.props.history.push(`/login`)
            }).catch(() => {}
        )

    }

    render() {
        return (
            <div>
                <h1>Register</h1>
                <div className="container">

                    User Name: <input type="text" name="login" value={this.state.login} onChange={this.handleChange} />
                    Email: <input type="text" name="email" value={this.state.email} onChange={this.handleChange} />
                    Password: <input type="password" name="password" value={this.state.password} onChange={this.handleChange} />
                    <button className="btn btn-success" onClick={this.registerClicked}>Login</button>
                </div>
            </div>
        )
    }
}

export default RegisterComponent