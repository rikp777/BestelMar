<template>
  <div class="card">
    <div class="card-body">
      <h5 class="card-title">Update User</h5>
      <form @submit.prevent="update" v-if="form.email">
        <div class="form-row">
          <div class="col">
            <label>Name</label>
            <input v-model="form.email" type="text" class="form-control" placeholder="Email">
          </div>
        </div>
        <div class="form-row">
          <div class="col">
            <label>first name</label>
            <input v-model="form.firstName" type="text" class="form-control" placeholder="First name">
          </div>
          <div class="col">
            <label>Last name</label>
            <input v-model="form.lastName" type="text" class="form-control" placeholder="Last name">
          </div>
        </div>
        <div class="row mt-2">
          <div class="col">
            <router-link
              class="btn btn-primary"
              :to="{ name: 'userList' }"
            >Back</router-link>
            <button class="btn btn-primary" type="submit">Update User</button>
          </div>
        </div>

      </form>
      <div v-else class="alert alert-warning">{{error}}</div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "userUpdate",
        props: ['userId'],
        data(){
            return {
                form: {
                    id: '',
                    email: '',
                    firstName: '',
                    lastName: '',
                    blocked: true,
                },
                error: null,
            }
        },
        computed: {
            user() {
                return this.$store.getters.user;
            },
        },
        mounted(){
            this.getUser(this.$route.params.id)
        },
        methods: {
            getUser(id){

                this.$store.dispatch("getUser", this.$route.params.id)
                    .then((data) => {
                        this.form = this.user
                    });
            },
            update(){
                this.$store.dispatch("updateUser", this.form)
                  .then(() => {
                    this.$router.push({ name: 'userList'})
                })
            }
        }
    }
</script>

<style scoped>

</style>
