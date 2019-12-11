import React, {Component} from 'react'
import SockJsClient from 'react-stomp';
import {TalkBox} from "react-talk";
import Fetch from "json-fetch";

class Room2Component extends Component {

    constructor(props) {
        super(props);
        this.state = {
            clientRef: null,
            ws: null,
            messages: [],
            text: '',
            clientConnected: null
        }
        this.handleChange = this.handleChange.bind(this)
    }

    onMessageReceive = (msg, topic) => {
        this.setState(prevState => ({
            messages: [...prevState.messages, msg]
        }));
    }

    componentWillMount() {
        Fetch("/history/" + "room2", {
            method: "GET"
        }).then((response) => {
            this.setState({messages: response.body});
        });
    }


    handleChange(event) {
        this.setState(
            {
                [event.target.name]: event.target.value
            }
        )
    }

    sendMessage = (msg, selfMsg) => {
        try {
            this.clientRef.sendMessage("/app/room/" + "room2", JSON.stringify(selfMsg));
            return true;
        } catch (e) {
            return false;
        }
    }


    render() {
        console.log('render')
        return (
            <div className="container">
                <h3>Chat</h3>
                <div className="container">
                    <TalkBox topic="Room 2" currentUserId={localStorage.getItem("AUTH")}
                             currentUser={localStorage.getItem("authenticatedUser")} messages={this.state.messages}
                             onSendMessage={this.sendMessage} connected={this.state.clientConnected}/>
                    <SockJsClient url={"http://localhost:8080/messages"} topics={["/topic/room/room2"]}
                                  onMessage={this.onMessageReceive} ref={(client) => {
                        this.clientRef = client
                    }}
                                  onConnect={() => {
                                      this.setState({clientConnected: true});
                                  }}
                                  onDisconnect={() => {
                                      this.setState({clientConnected: false})
                                  }}
                                  debug={true}/>
                </div>
            </div>
        )
    }
}

export default Room2Component
