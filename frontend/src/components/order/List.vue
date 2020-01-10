<template>
  <div class="card">
    <div class="card-body">
      <div class="row mb-2">
        <div class="col">
          <router-link
            class="btn btn-primary"
            :to="{ name: 'home' }"
          >Back
          </router-link>
          <router-link
            class="btn btn-secondary"
            :to="{ name: 'orderCreate'}"
          >
            <b>+</b> Add new order
          </router-link>
          <router-link
            class="btn btn-secondary"
            :to="{ name: 'orderHistory'}"
          >
            History
          </router-link>
        </div>
      </div>
      <h3 class="card-title">Current Orders</h3>
      {{data}}
      <div class="card-columns">
        <div class="card" v-for="(order, orderIndex) in orders" v-bind:class="[order.status === 'Paid' ? 'border-danger mb-3' : '']">
          <div class="card-body" v-if="order.table">
            <h5>{{order.table.name}}</h5>
            <p class="card-text">{{order.table.description}}</p>
            <p class="card-text">Status: <b>{{order.status}}</b></p>
            <div class="list-group" v-if="order.articleOrder">
              <div v-for="(articleOrder, articleOrderIndex) in order.articleOrder">
                <a class="list-group-item list-group-item-action flex-column align-items-start" v-bind:class="[(articleOrder.status === 'Waiting' ? 'border-info mb-3' : '')]">
                  <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">{{articleOrder.article.name}}</h5>
                  </div>
                  <p class="card-text">{{articleOrder.comment}}</p>
                  <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                  <button class="btn btn-primary" @click="updateOrderTableStatus(orderIndex, articleOrderIndex)">{{articleOrder.status}}</button>
                </a>
                <br>
              </div>
            </div>
<!--            <div class="list-group" v-if="order.articles">-->
<!--              {{order}}-->
<!--              <div v-for="article in order.articles">-->
<!--                <a class="list-group-item list-group-item-action flex-column align-items-start" v-bind:class="[(articleOrder.status === 'paid' ? '' : 'border-danger mb-3'),(articleOrder.status === 'Waiting' ? '' : 'border-info mb-3')]">-->
<!--                  <div class="d-flex w-100 justify-content-between">-->
<!--                    <h5 class="mb-1">{{article.name}}</h5>-->
<!--                  </div>-->
<!--                  <p class="card-text">{{article.comment}}</p>-->
<!--                  <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>-->
<!--                  <button class="btn btn-primary">Maak</button>-->
<!--                </a>-->
<!--                <br>-->
<!--              </div>-->
<!--            </div>-->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "List",
    methods: {
      connect() {
        this.$store.dispatch("connectGlobal").then(() => {
          this.$store.dispatch("subscribeGlobalOrder")
        })
      },
      getAllTables() {
        this.$store.dispatch("getAllTables");
      },
      getAllLastOrders() {
        this.$store.dispatch("getAllLastOrders");
      },
      updateOrderTableStatus(orderIndex, articleOrderIndex){
        this.orders[orderIndex].articleOrder[articleOrderIndex].status = "Waiting"

        this.$store.dispatch("sendGlobalOrderTable", this.orders[orderIndex]).then(() => {
          console.log(this.orders[orderIndex])
          this.$store.dispatch("updateOrder", this.orders[orderIndex])
        })
      }
    },
    computed: {
      tables() {
        return this.$store.getters.tables
      },
      orders() {
        console.log(this.$store.getters.orders);
        return this.$store.getters.orders
      },
      data(){
        return this.$store.getters.webSocketData
      }
    },
    mounted() {
      this.connect();
      this.getAllTables();
      this.getAllLastOrders()
    }
  }
</script>

<style scoped>

</style>
