<template>
  <div class="card">
    <div class="card-body">
      <h5 class="card-title">Create Article</h5>
      <form @submit.prevent="create">
        <div class="form-row">
          <div class="col">
            <label>Name</label>
            <input v-model="article.name" type="text" class="form-control" placeholder="Name">
          </div>
          <div class="col">
            <label>Price</label>
            <input v-model="article.price" type="number" class="form-control" step="any">
          </div>
        </div>
        <div class="form-row">
          <div class="col">
            <label for="validationTextarea">Description</label>
            <textarea v-model="article.description" class="form-control" id="validationTextarea" placeholder="Required example textarea"></textarea>
          </div>
        </div>
        <div class="row mt-2">
          <div class="col">
            <router-link
              class="btn btn-primary"
              :to="{ name: 'articleList' }"
            >Back</router-link>
            <button class="btn btn-primary" type="submit">Create Article</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
    export default {
        name: "ArticleCreate",
        data(){
            return {
                article: {
                    name: '',
                    description: '',
                    price: 0,
                }
            }
        },
        methods: {
            create(){
                this.$store.dispatch("createArticle", this.article).then(() => {
                    this.$router.push({ name: 'articleList' })
                })
            }
        }
    }
</script>

<style scoped>

</style>
