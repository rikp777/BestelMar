import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'

let resourcePoint = "send-data";
let port = "8085";
let endpoint = "websocket-endpoint";
let websiteUrl = "http://localhost";
let subBroker = "global";

let received_messages = [];
export const WebSocketService  = {
  send(slug, payload){
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.send(`/${resourcePoint}/${slug}`, payload, {})
    }
  },
  connect(){
    this.socket = new SockJS(`${websiteUrl}:${port}/${endpoint}`);
    this.stompClient = Stomp.over(this.socket);
    this.stompClient.connect({})
  },
  subscribe(){
    return this.stompClient.subscribe("/global/order");
  },
  disconnect () {
    if (this.stompClient) {
      this.stompClient.disconnect()
    }
    this.connected = false
  },
  tickleConnection(resource) {
    if (this.connected) {
      this.disconnect();
    } else {
      this.connect();
    }
  }
};
