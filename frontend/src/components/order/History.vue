<template>
  <div class="card">
    <div class="card-body">
      <div class="table-responsive">
        <table class="table">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Date</th>
              <th scope="col">Table</th>
              <th scope="col">Order</th>
              <th scope="col">Status</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(order, index) in orders">
              <td>{{index + 1}}</td>
              <td>{{order.date}}</td>
              <td>{{order.table.id}}</td>
              <td>
                <div v-for="article in order.articleOrder">
                  {{article.article.name}}
                </div>
              </td>
              <td>{{order.status}}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "History",
    methods: {

      getAllOrders() {
        this.$store.dispatch("getAllOrders");
      },
    },
    computed: {

      orders() {
        return this.$store.getters.orders
      },

    },
    mounted() {
      this.getAllOrders()
    }
  }
</script>

<style scoped>

</style>

