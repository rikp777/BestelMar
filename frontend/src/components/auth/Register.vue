<template>
  <div class="login row justify-content-center">
    <div class="col-md-4">
      <div class="card">
        <div class="card-header">Register</div>
        <div class="card-body">
          <div v-if="error.length" class="alert alert-danger alert-dismissible fade show" id="alertError" role="alert">
            <strong>Error!</strong>

          </div>
          <form v-on:submit.prevent="onSubmit()" autocomplete="off">
            <div class="form-group row">
              <label for="first_name">First Name:</label>
              <input
                name="first_name"
                id="first_name"
                type="text"
                v-model="form.first_name"
                class="form-control"
                placeholder="First name">
            </div>
            <div class="form-group row">
            <label for="last_name">Last Name:</label>
            <input
              name="last_name"
              id="last_name"
              type="text"
              v-model="form.last_name"
              class="form-control"
              placeholder="Last name">
          </div>


            <div class="form-group row">
              <label for="email">Email:</label>
              <input
                name="email"
                id="email"
                type="email"
                v-model="form.email"
                class="form-control"
                placeholder="Email Address">
            </div>
            <div class="form-group row">
              <label for="password">Password:</label>
              <input
                name="password"
                id="password"
                type="password"
                v-model="form.password"
                class="form-control"
                placeholder="Password">
            </div>
            <div class="form-group row">
              <input type="submit" value="Login">

            </div>

<!--            {{errors}}-->
<!--            <div class="form-group row" v-if="errors">-->
<!--              <ul class="error-messages">-->
<!--                <li v-for="(v, k) in errors" :key="k">{{ k }} {{ v | error }}</li>-->
<!--              </ul>-->
<!--            </div>-->
          </form>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
    import { mapState } from 'vuex';
    export default {
        name: "auth-register",
        data(){
            return {
                form: {
                    firstName: 'bo',
                    lastName: 'bo',
                    email: 'bo@gmail.com',
                    password: 'bo',
                },
            };
        },
        methods: {
            onSubmit(){
                // throw(this.$store);
                this.$store
                    .dispatch("createUser", this.form)
                    .then(() => this.$router.push({ name: "login"}));



            }
        },
        computed: {
            error(){
                return this.$store.getters.getUserErrors
            }
        }
    }
</script>
