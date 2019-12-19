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
        </div>
      </div>
      <h3 class="card-title">Current Orders</h3>
      <div class="card-columns">
        <div class="card" v-for="order in orders">
          <div class="card-body" v-if="order.table">
            <h5>{{order.table.name}}</h5>
            <p class="card-text">{{order.table.description}}</p>
            <p class="card-text">Status: <b>not paid</b></p>
            <div class="list-group" v-if="order.articleOrder">
              <div v-for="articleOrder in order.articleOrder">
                  <a class="list-group-item list-group-item-action flex-column align-items-start">
                    <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1">{{articleOrder.article.name}}</h5>
                    </div>
                    <p class="card-text">{{articleOrder.comment}}</p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    <button class="btn btn-primary" @click="updateOrderStatus(articleOrder.id)">Maak</button>
                  </a>
                  <br>
                </div>
            </div>
            <div class="list-group" v-if="order.articles">
              {{order}}
              <div v-for="article in order.articles">
                <a class="list-group-item list-group-item-action flex-column align-items-start">
                  <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">{{article.name}}</h5>
                  </div>
                  <p class="card-text">{{article.comment}}</p>
                  <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                  <button class="btn btn-primary">Maak</button>
                </a>
                <br>
              </div>
            </div>
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
        this.$store.dispatch("connect")
      },
      getAllTables() {
        this.$store.dispatch("getAllTables");
      },
      getAllLastOrders() {
        this.$store.dispatch("getAllLastOrders");
      },
      updateOrderStatus(id){
          console.log(id)
      }
    },
    computed: {
      liveOrders() {
        return this.$store.getters.webSocketData;
      },
      tables() {
        return this.$store.getters.tables
      },
      orders() {
          return this.$store.getters.orders
      },
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
