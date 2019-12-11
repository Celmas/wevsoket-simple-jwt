import React, {Component} from 'react'
import {Link} from "react-router-dom";

class ChatComponent extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        console.log('render')
        return (
            <div className="container">
                <h3>Rooms:</h3>
                <Link className="btn btn-primary" to="/room1">Room1</Link>
                <Link className="btn btn-primary" to="/room2">Room2</Link>
                <Link className="btn btn-primary" to="/room3">Room3</Link>

            </div>
        )
    }
}

export default ChatComponent
