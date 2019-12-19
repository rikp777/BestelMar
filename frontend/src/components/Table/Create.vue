<template>
  <div class="card">
    <div class="card-body">
      <h5 class="card-title">Create Table</h5>
      <form @submit.prevent="create">
        <div class="form-row">
          <div class="col">
            <label>Name</label>
            <input v-model="form.name" type="text" class="form-control" placeholder="Name">
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
              :to="{ name: 'tableList' }"
            >Back</router-link>
            <button class="btn btn-primary" type="submit">Create Table</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
    export default {
        name: "TableCreate",
        data(){
            return {
                form: {
                    name: '',
                    description: '',
                }
            }
        },
        methods: {
            create(){
                this.$store.dispatch("createTable", this.form).then(() => {
                    this.$router.push({ name: 'tableList' })
                })
            }
        }
    }
</script>

<style scoped>

</style>
