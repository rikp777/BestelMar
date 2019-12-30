<template>
  <div>
    <order-table v-if="order.table.id" :table-id="order.table.id"/>
    <div class="card">
      <div class="card-body">
        <form @submit.prevent="sendOrder()">
          <div class="form-row">
            <div class="form-group col-md-6" v-if="order.table.id && orderd.status != 'Paid'">
              <label>Articles</label>
              <multiselect
                v-if="articles.length"
                v-model="order.articles"
                :options="articles"
                :multiple="true"
                :close-on-select="false"
                :clear-on-select="false"
                :preserve-search="true"
                :allow-empty="false"
                placeholder="Pick some"
                label="name"
                track-by="name"
                :preselect-first="true">
                <template
                  slot="selection"
                  slot-scope="{ values, search, isOpen }">
                  <span
                    class="multiselect__single"
                    v-if="values.length &amp;&amp; !isOpen">
                    {{ values.length }} options selected
                  </span>
                </template>
              </multiselect>
            </div>
            <div class="form-group col-md-6" v-if="!order.table.id">
              <label>Table that places an ordering</label>
              <multiselect
                v-if="tables.length"
                v-model="order.table"
                :allow-empty="false"
                :options="tables"
                :multiple="false"
                :close-on-select="false"
                :clear-on-select="false"
                :preserve-search="true"
                placeholder="Choose one"
                label="name"
                track-by="name">
                <template
                  slot="selection"
                  slot-scope="{ values, search, isOpen }">
                  <span
                    class="multiselect__single"
                    v-if="values.length &amp;&amp; !isOpen">
                    {{ values.length }} options selected
                  </span>
                </template>
              </multiselect>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group col-md-6">
              <div v-if="order.articles.length > 0">
                <h5>selected items</h5>
                <ul class="list-group" v-for="article in order.articles">
                  <li class="list-group-item">
                    <div class="d-flex w-100 justify-content-between row">
                      <div class="col-md-6">
                        <h5>{{article.name}}</h5>
                      </div>
                      <div class="col-md-5">
                        <input class="form-control form-control-sm" type="text" placeholder="comment" @change="addComment(article, $event)">
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          {{order.table.id}}
          <div v-if="orderd.status != 'Paid'">
            <button class="btn btn-primary" type="submit" >Create order</button>
          </div>
          <div v-else>Thanks for your visit - everything has been paid! - When your table is released by the barista this page is no longer available</div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
  import orderTable from './Table';
  export default {
    name: "order-index",
    components: { orderTable },
    data(){
      return {
        webSocketData: [],
        order: {
          table: {},
          articles: [],
        }
      }
    },
    methods: {
      addComment(article, $event){
        this.$set(article, 'comment', $event.target.value)
      },
      connect(){
        this.$store.dispatch("connectGlobal")
      },
      sendOrder(){
        console.log(this.order)
        this.$store.dispatch("createOrder", this.order).then(() => {
          this.$store.dispatch("sendGlobalOrder", this.order)
        })
      },
      getAllArticles(){
        this.$store.dispatch("getAllArticles");
      },
      getAllTables(){
        this.$store.dispatch("getAllTables");
      },
      getOrderTable(tableId){
        this.$store.dispatch("getOrderTable", tableId)
      },
    },
    computed: {
      table(){
        return this.order.table;
      },
      tables(){
        return this.$store.getters.tablesActive
      },
      articles(){
        return this.$store.getters.articles
      },
      orderd() {
        return this.$store.getters.order;
      },
      orderTable(){
        return this.$store.getters.orderTable
      }
    },
    watch: {
      table: function(newValue, oldValue) {
        if(newValue != oldValue){
          this.$store.dispatch("disconnect").then(() => {
            this.getOrderTable(newValue.id)
            this.connect(newValue.id)
          })
        }
      }
    },
    mounted(){
      this.order.table.id = null;
      if(this.orderTable){
        //this.$set(this.order.table, "id", this.orderTable)
        this.order.table.id = this.orderTable
      }

      this.getAllArticles();
      this.getAllTables();
    }
  }
</script>

