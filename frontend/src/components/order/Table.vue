<template>
  <div class="card mb-2">
    <div class="card-body" v-if="order">


      <h5 class="card-title">Table Name:  {{order.table.name}}</h5>


      <button type="button" class="btn btn-primary mb-2" data-toggle="modal" data-target=".bd-example-modal-xl">Pay & reciept - Summary</button>
      <button type="button" class="btn btn-primary mb-2" @click="pay">Pay the receipt</button>

      <div class="list-group" v-for="articleOrder in order.articleOrder">
        <a href="#" class="list-group-item list-group-item-action">
          <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">{{articleOrder.article.name}}</h5>
            <small>{{articleOrder.date}}</small>
            <div>Status: <span class="badge badge-pill badge-primary">{{articleOrder.status}}</span></div>
          </div>
          <p class="mb-1">{{articleOrder.comment}}</p>
          <small>Price: €{{articleOrder.price.toFixed(2)}}</small>
        </a>
      </div>


      <div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl" role="document">
          <div class="modal-content">
            <div class="padding">
              <div class="card">
                <div class="card-header p-4">
                  <h3 class="pt-2 d-inline-block">BestelMar</h3>
                  <div class="float-right">
                    <h3 class="mb-0">Invoice #{{order.id}}</h3>
                    Date: {{order.date}}
                  </div>
                </div>
                <div class="card-body">
                  <div class="table-responsive-sm">
                    <table class="table table-striped">
                      <thead>
                      <tr>
                        <th class="center">#</th>
                        <th>Item</th>
                        <th>Description</th>
                        <th class="right">Price</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr v-for="(articleOrder, index) in order.articleOrder">
                        <td class="center">{{index + 1}}</td>
                        <td class="left strong">{{articleOrder.article.name}}</td>
                        <td class="left">{{articleOrder.comment}}</td>
                        <td class="right">€{{articleOrder.price.toFixed(2)}}</td>
                      </tr>
                      </tbody>
                    </table>
                  </div>
                  <div class="row">
                    <div class="col-lg-4 col-sm-5">
                    </div>
                    <div class="col-lg-4 col-sm-5 ml-auto">
                      <table class="table table-clear">
                        <tbody>
                        <tr>
                          <td class="left">
                            <strong class="text-dark">Subtotal</strong>
                          </td>
                          <td class="right">€{{(totalPrice * 0.79).toFixed(2)}}</td>
                        </tr>
                        <tr>
                          <td class="left">
                            <strong class="text-dark">btw (21%)</strong>
                          </td>
                          <td class="right">€{{(totalPrice * 0.21).toFixed(2)}}</td>
                        </tr>
                        <tr>
                          <td class="left">
                            <strong class="text-dark">Total</strong> </td>
                          <td class="right">
                            <strong class="text-dark">€{{totalPrice}}</strong>
                          </td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <div class="card-footer bg-white">
                  <button class="btn btn-primary float-right" @click="pay">Pay the receipt</button>
                  <p class="mb-0">BestelMar receipt</p>
                </div>
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
        name: "order-table",
        data(){
            return {
            }
        },
        methods: {
            getOrderTable(id) {
                this.$store.dispatch("getOrderTable", id)
            },
            pay(){

            }
        },
        computed: {
            order() {
                return this.$store.getters.order;
            },
            totalPrice(){
                return this.$store.getters.totalPrice;
            }
        },
        mounted() {
            this.getOrderTable(1)
        }
    }
</script>

