<template>
  <div class="card">
    <div class="card-body">
      <h5 class="card-title">Update Article</h5>
      <form @submit.prevent="update" v-if="form.name">
        <div class="form-row">
          <div class="col">
            <label>Name</label>
            <input v-model="form.name" type="text" class="form-control" placeholder="Name">
          </div>
          <div class="col">
            <label>Price</label>
            <input v-model="form.price.toFixed(2)" type="number" class="form-control" step="any">
          </div>
        </div>
        <div class="form-row">
          <div class="col">
            <label for="validationTextarea">Description</label>
            <textarea v-model="form.description" class="form-control" id="validationTextarea" placeholder="Required example textarea"></textarea>
          </div>
        </div>
        <div class="row mt-2">
          <div class="col">
            <router-link
              class="btn btn-primary"
              :to="{ name: 'articleList' }"
            >Back</router-link>
            <button class="btn btn-primary" type="submit">Update Article</button>
          </div>
        </div>

      </form>
      <div v-else class="alert alert-warning">{{error}}</div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "ArticleUpdate",
        props: ['articleId'],
        data(){
            return {
                form: {
                    id: '',
                    name: '',
                    description: '',
                    price: 0,
                },
                error: null,
            }
        },
        computed: {
            article() {
                return this.$store.getters.article;
            },
        },
        mounted(){
            if(this.articleId != null){
                this.getArticle(this.articleId)
            }else if(this.$route.params.id){
                this.getArticle(this.$route.params.id)
            }else{
                this.error = "No article found"
            }
        },
        methods: {
            getArticle(id){
                this.$store.dispatch("getArticle", id)
                    .then((data) => {
                        this.form = this.article
                    });
            },
            update(){
                this.$store.dispatch("updateArticle", this.article).then(() => {
                    this.$router.push({ name: 'articleList'})
                })
            }
        }
    }
</script>

<style scoped>

</style>
