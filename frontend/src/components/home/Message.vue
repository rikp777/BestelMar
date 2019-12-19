<template>
  <div class="card">
    <div class="card-body">
      <h1>Home</h1>
      <h2>Connected? {{ connected}}</h2>
      <div>
        <button @click="tickleConnection">{{ connected ? 'disconnect' : 'connect' }}</button>
      </div>
      <h2>Send Message</h2>
      <div>
        <input v-model="send_message" placeholder="Send Message">
        <button @click="send">Send</button>
      </div>
      <h2>Message Received</h2>
      <p>{{ received_messages }}</p>
    </div>
  </div>
</template>

<script>
    import SockJS from 'sockjs-client'
    import Stomp from 'webstomp-client'
    export default {
        name: 'home',
        data () {
            return {
                received_messages: [],
                send_message: null,
                connected: false
            }
        },
        methods: {
            send () {
                console.log('Send message:' + this.send_message)
                if (this.stompClient && this.stompClient.connected) {
                    this.stompClient.send('/send-data/orderweb', this.send_message, {})
                }
            },
            connect () {
                this.socket = new SockJS('http://localhost:8085/websocket-endpoint');
                this.stompClient = Stomp.over(this.socket);
                this.stompClient.connect({}, (frame) => {
                    this.connected = true;
                    console.log("====Frame: " + frame);
                    this.stompClient.subscribe('/global/orderweb', (tick) => {
                        console.log("====Subscribe: " + tick);
                        this.received_messages.push(tick)
                    })
                }, (error) => {
                    console.log(error);
                    this.connected = false
                })
            },
            disconnect () {
                if (this.stompClient) {
                    this.stompClient.disconnect()
                }
                this.connected = false
            },
            tickleConnection () {
                this.connected ? this.disconnect() : this.connect()
            }
        },
        mounted () {
            this.connect()
        }
    }
</script>
