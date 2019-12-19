<template>
  <div class="card">
    <div class="card-body">
      <div class="row mb-2">
        <div class="col">
          <router-link
            class="btn btn-primary"
            :to="{ name: 'home' }"
          >Back</router-link>
          <router-link
            class="btn btn-secondary"
            :to="{ name: 'articleCreate'}"
          >
            <b>+</b> Add new article
          </router-link>
        </div>
      </div>

      <div class="table-responsive">
        <table class="table table-striped table-condensed">
        <thead>
          <tr>
            <th>name</th>
            <th>Description</th>
            <th>Orders</th>
            <th class="text-center">Edit/Disable</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="article in articles">
            <td>{{article.name}}</td>
            <td>{{article.description}}l</td>
            <td>{{article.price}}</td>
            <td class="text-center">
              <router-link
                class="btn btn-warning btn-xs"
                :to="{ name: 'articleUpdate', params: { id: article.id }}"
              >
                Update
              </router-link>
              <router-link
                class="btn btn-danger btn-xs"
                :to="{ name: 'articleDelete', params: { id: article.id }}"
              >
                Delete
              </router-link>
            </td>
          </tr>
        </tbody>
      </table>
      </div>
    </div>
  </div>
</template>
<script>
    export default {
        name: "articleList",
        data(){
            return {
                article: {}
            }
        },
        computed: {
            articles(){
                return this.$store.getters.articles
            }
        },
        mounted(){
            this.getAllArticles();
        },
        methods: {
            getAllArticles(){
                this.$store.dispatch("getAllArticles");
            },
        }
    }
</script>
