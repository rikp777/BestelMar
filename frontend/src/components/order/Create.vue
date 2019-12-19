<template>
  <div>
    <order-table></order-table>
    <div class="card">
      <div class="card-body">
        <form @submit.prevent="sendOrder">
          <div class="form-row">
            <div class="form-group col-md-6">
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
            <div class="form-group col-md-6">
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
          <button class="btn btn-primary" type="submit">Create order</button>
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
                    table: [],
                    articles: [],
                }
            }
        },
        methods: {
            addComment(article, $event){
                this.$set(article, 'comment', $event.target.value)
            },
            sendOrder () {
                this.$store.dispatch("sendOrder", this.order);
            },
            connect () {
                this.$store.dispatch("connect")
            },
            getAllArticles(){
                this.$store.dispatch("getAllArticles");
            },
            getAllTables(){
                this.$store.dispatch("getAllTables");
            },
        },
        computed: {
            tables(){
                return this.$store.getters.tables
            },
            articles(){
                return this.$store.getters.articles
            }
        },
        mounted(){
            this.connect();
            this.getAllArticles();
            this.getAllTables();
        }
    }
</script>

