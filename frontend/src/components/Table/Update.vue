<template>
  <div class="card">
    <div class="card-body">
      <h5 class="card-title">Update Table</h5>
      <form @submit.prevent="update" v-if="form.name">
        <div class="form-row">
          <div class="col">
            <label>Name</label>
            <input v-model="form.name" type="text" class="form-control" placeholder="Name">
          </div>
          <div class="col">
            <div class="row justify-content-center">
              <label>Status: {{form.disabled}}</label>
            </div>
            <div class="row justify-content-center">
              <div class="btn-group btn-group-toggle" data-toggle="buttons">
                <label class="btn btn-warning">
                  <input v-model="form.disabled" type="radio" value="true"> Disabled
                </label>
                <label class="btn btn-success">
                  <input v-model="form.disabled" type="radio" value="false"> Active
                </label>
              </div>
            </div>
          </div>
        </div>
        <div class="form-row">
          <div class="col">
            <label for="validationTextarea">Description</label>
            <textarea v-model="form.description" class="form-control" id="validationTextarea" placeholder="Required example textarea" required></textarea>
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
        props: ['tableId'],
        data(){
            return {
                form: {
                    id: '',
                    name: '',
                    description: '',
                    disabled: false,
                },
                error: null,
            }
        },
        computed: {
            table() {
                return this.$store.getters.table;
            },
        },
        mounted(){
            if(this.tableId != null){
                this.getTable(this.tableId)
            }else if(this.$route.params.id){
                this.getTable(this.$route.params.id)
            }else{
                this.error = "No table found"
            }
        },
        methods: {
            getTable(id){
                this.$store.dispatch("getTable", id)
                    .then((data) => {
                        this.form = this.table
                        this.form.disabled = this.table.disabled
                    });
            },
            update(){
                this.$store.dispatch("updateTable", this.form).then(() => {
                    this.form = {}
                    this.$router.push({ name: 'tableList'})
                })
            }
        }
    }
</script>

<style scoped>

</style>
