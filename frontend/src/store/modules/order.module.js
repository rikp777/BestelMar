import Vue from 'vue'
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'
import apiService from "../../endpoint/api.service";

const apiUrl = "order"


// Initial State
const state = {
  orders: {},
  order: null,
  isLoading: true,
  connected: false,
  webSocketData: [],
};

// Getters
const getters = {
  orders(state) {
    return state.orders;
  },
  order(state) {
    return state.order
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
  sendOrder(context, payload) {
    console.log("raak")
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.send('/send-data/orderweb', JSON.stringify(payload), {})
    }
  },
  sendStatus(context, payload){
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.send('/send-data/orderweb', JSON.stringify(payload), {})
    }
  },
  connect(context) {
    this.socket = new SockJS('http://localhost:8085/websocket-endpoint');
    this.stompClient = Stomp.over(this.socket);
    this.stompClient.connect({}, (frame) => {
      context.connected = true;
      this.stompClient.subscribe('/global/orderweb', (tick) => {
        let data = JSON.parse(tick.body)
        context.commit("setWebSocketData", data);
        //context.webSocketData.push(tick)
      })
    }, (error) => {
      console.log(error);
      context.connected = false
    })
  },
  disconnect(context) {
    if (this.stompClient) {
      this.stompClient.disconnect()
    }
    context.connected = false
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
  }
};

// Mutations
export const mutations = {
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
  setWebSocketData(state, webSocketData){
    // Array.prototype.extend = function (other_array) {
    //   other_array.forEach(function(v) {this.push(v)}, this);
    // }

    // if(state.order){
    //   if(state.order.table.id == webSocketData.table.id){
    //
    //     state.order.articleOrder.extend(webSocketData.articleOrder)
    //   }
    // }


    state.order = webSocketData;
    let order = state.orders.find(order => order.table.id === webSocketData.table.id)
    if(order){
      console.log("Order for table does exist")
      order.articleOrder = webSocketData.articleOrder;
    }else{
      console.log("New table will be pushed")
      state.orders.push(webSocketData);
    }

    console.log(state.orders)
    //console.log(webSocketData.articleOrder)
    //console.log(state.orders.find(order => order.table.id === webSocketData.table.id).articleOrder)

    // if(state.orders.length){
    //   let foundTable = state.orders.filter(function(el){
    //     // console.log(el.table.id)
    //     if (el.table.id === webSocketData.table.id){
    //       console.log("Table math: " + el.table.id + " with " + webSocketData.table.id)
    //       return true
    //     }
    //     return false;
    //   });
    //   console.log("found: " + foundTable)
    //   if(foundTable.length){
    //     // console.log("replacing data ")
    //     // console.log(state.orders)
    //
    //
    //     // state.orders.find(order => order.id === webSocketData.id).articleOrder = webSocketData.articleOrder
    //     //
    //     // console.log(state.orders)
    //
    //     //console.log(state.orders[0].length)
    //     // state.orders.forEach((el, index) => {
    //
    //
    //       // if (el.table.id === webSocketData.table.id) {
    //       //   Vue.set(state.orders, state.orders.findIndex(o => o.id === webSocketData.id), webSocketData)
    //       //   console.log("replacing data ")
    //       //   // el.articleOrder = webSocketData.articleOrder;
    //       //   // el.articleOrder.
    //       //   // console.log(el.articleOrder);
    //       //   // Vue.set(state.orders, index, webSocketData)
    //       //   //state.orders[index].articleOrder = webSocketData.articleOrder
    //       //   // el.articleOrder.extend(webSocketData.articleOrder);
    //       // }
    //     // })
    //   }else{
    //     console.log("pushing websocket data to existing data")
    //     state.orders.push(webSocketData)
    //   }
    // }
  }
};


export default {
  state,
  getters,
  actions,
  mutations
}
