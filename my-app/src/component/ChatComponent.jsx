import React, {Component} from 'react'

class ChatComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            ws: null,
            messages: [],
            text: ''
        }
        this.handleChange = this.handleChange.bind(this)
        this.sendClicked = this.sendClicked.bind(this)
    }

    componentDidMount() {
        this.connect();
    }

    connect() {
        let client = new WebSocket('ws://localhost:8080/chat');
        client.onopen = () => {
            this.setState({ws: client})
            client.send(JSON.stringify({
                "from": localStorage.getItem('AUTH'),
                "text": "Hello!"
            }))
        };
        client.onmessage = (message) => {
            console.log(JSON.parse(message.data));
            this.setState({messages: this.state.messages.concat(JSON.parse((message.data)))})
        };
        client.onerror = () => {
            console.log('Auth error');
        }

    }


    handleChange(event) {
        this.setState(
            {
                [event.target.name]: event.target.value
            }
        )
    }

    sendClicked() {
        let message = {
            "from": localStorage.getItem('AUTH'),
            "text": this.state.text
        };
        this.state.ws.send(JSON.stringify(message));
        this.setState({text: ''})
    }


    render() {
        console.log('render')
        return (
            <div className="container">
                <h3>Chat</h3>
                <div className="container">
                    Text: <input type="text" name="text" value={this.state.text} onChange={this.handleChange}/>
                    <button className="btn btn-success" onClick={this.sendClicked}>Send</button>
                    <table className="table">
                        <thead>
                        <tr>
                            <th>Login</th>
                            <th>Message</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.messages.map(
                                message =>
                                    <tr key={message.from}>
                                        <td>{message.from}</td>
                                        <td>{message.text}</td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ChatComponent
