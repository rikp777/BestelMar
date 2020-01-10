import Vue from 'vue'
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'
import apiService from "../../endpoint/api.service";
import cookieService from "../../helpers/cookie.service";

const apiUrl = "order"


// Initial State
const state = {
  orderPaid: false,
  orders: {},
  order: null,
  webSocketOrder: null,
  isLoading: true,
  connected: false
};

// Getters
const getters = {
  orderTable(state) {
    return cookieService.getToken('table')
  },
  orders(state) {
    return state.orders;
  },
  order(state) {
    return state.order
  },
  orderPaid(state){
    return state.orderPaid
  },
  totalPrice(state){
    return state.order.articleOrder.reduce((acc, item) => acc + item.price, 0).toFixed(2);
  },
  orderIsLoading(state) {
    return state.isLoading
  },
  webSocketIsConnected(state){
    return state.connected
  },
  webSocketData(state){
    return state.webSocketData;
  }
};

// Actions
const actions = {
  createOrder(context, payload){
    return apiService.post(apiUrl, payload)
      .then(({data}) => {
        context.commit("setOrder", data);
      }).catch((error) => {
        throw error
      })
  },
  updateOrder(context, payload){
    return apiService.put(apiUrl, payload.id, payload)
      .then(({data}) => {
        context.commit("setOrder", data);
      }).catch((error) => {
        throw error
      })
  },


  sendGlobalOrderTable(context, payload) {
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.send('/send-data/orderweb/table/' + payload.table.id, JSON.stringify(payload), {})
    }else{
      console.log("Can not send data not connected")
    }
  },
  connectGlobal(context) {
    return new Promise((resolve, reject) => {
      this.socket = new SockJS('http://localhost:8085/websocket-endpoint');
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect({}, (frame) => {
        context.connected = true;
        resolve()
      }, (error) => {
        console.log(error);
        context.connected = false
        reject(error);
      })
    })
  },
  sendGlobalOrder(context, payload) {
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.send('/send-data/orderweb', JSON.stringify(payload.table), {})
    }else{
      console.log("Can not send data not connected")
    }
  },
  subscribeGlobalOrder(context){
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.subscribe('/global/orderweb', (tick) => {
        let data = JSON.parse(tick.body)
        context.commit("setWebSocketDataOrders", data);
      })
    }else{
      console.log("Can not subscribe not connected")
    }
  },
  subscribeGlobalOrderTable(context, id){
    cookieService.saveToken("table", id)
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.subscribe('/global/orderweb/table/' + id, (tick) => {
        let data = JSON.parse(tick.body)
        context.commit("setWebSocketDataOrder", data);
      })
    }else{
      console.log("Can not subscribe not connected")
    }
  },



  disconnect(context) {
    return new Promise((resolve) => {
      if (this.stompClient) {
        this.stompClient.disconnect()
        context.connected = false
        resolve()
      }
    })
  },
  tickleConnection(context) {
    this.connected ? this.disconnect() : this.connect()
  },
  getAllOrders(context){
    context.commit("startLoading");
    return apiService.get(apiUrl)
      .then(({ data }) => {
        context.commit("setOrders", data);
        context.commit("endLoading");
      }).catch((error) => {
        throw error
      })
  },
  getAllLastOrders(context){
    context.commit("startLoading");
    return apiService.get(apiUrl, "last")
      .then(({ data }) => {
        context.commit("setOrders", data);
        context.commit("endLoading");
      }).catch((error) => {
        throw error
      })
  },
  getOrderTable(context, slug){
    //console.log("raak orderTable")
    context.commit("startLoading");
    return apiService.get(apiUrl, "table/" + slug)
      .then(({ data }) => {
        context.commit("setOrder", data);
        context.commit("endLoading");
      }).catch((error) => {
        throw error
      })
  },
  getOrderForTablePaid(context, slug){
    return apiService.get(apiUrl, "table/" + slug + "/paid").then(({data}) =>{
      context.commit("setOrderPaid", data)
    }).catch((error) => {
      throw error
    })
  },
  payTableOrder(context, payload){
    cookieService.destroyToken("table")
    context.commit("reset")
    return apiService.post(apiUrl + "/table/" + payload.table.id + "/pay", payload)
  }

};

// Mutations
export const mutations = {
  reset(state){
    state.order = null;
    state.orderPaid = false;
    console.log(state.orderPaid)
  },
  startLoading(state) {
    state.isLoading = true;
  },
  endLoading(state) {
    state.isLoading = false;
  },
  setOrders(state, orders){
    state.orders = orders;
  },
  setOrder(state, order){
    state.order = order
  },
  setOrderPaid(state, status){
    state.orderPaid = status
  },
  setWebSocketDataOrder(state, webSocketData){
    state.order = webSocketData;
  },
  setWebSocketDataOrders(state, webSocketData){
    let order = state.orders.find(order => order.table.id === webSocketData.table.id)
    if(order){
      console.log("Order for table does exist")
      order.articleOrder = webSocketData.articleOrder;
    }else{
      console.log("New table will be pushed")
      state.orders.push(webSocketData);
    }
  }
};


export default {
  state,
  getters,
  actions,
  mutations
}
