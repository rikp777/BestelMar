<template>
  <div class="card">
    <div class="card-body">
      <div class="row mb-2">
        <div class="col">
          <router-link
            class="btn btn-primary"
            :to="{ name: 'home' }"
          >Back</router-link>
        </div>
      </div>

      <div class="table-responsive">
        <table class="table table-striped table-condensed">
          <thead>
          <tr>
            <th>Email</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Blocked</th>
            <th class="text-center">Edit/Disable</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in users">
            <td>{{user.email}}</td>
            <td>{{user.firstName}}</td>
            <td>{{user.lastName}}</td>
            <td>
              <span v-bind:class="[user.blocked ? 'badge-danger' : 'badge-success']" class="badge badge-pill">{{user.blocked}}</span>
            </td>
            <td class="text-center">
              <router-link
                class="btn btn-warning btn-xs"
                :to="{ name: 'userUpdate', params: { id: user.id }}"
              >
                Update
              </router-link>
              <router-link
                class="btn btn-danger btn-xs"
                :to="{ name: 'userDelete', params: { id: user.id }}"
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
        name: "userList",
        data(){
            return {
                user: {}
            }
        },
        computed: {
            users(){
                return this.$store.getters.users
            }
        },
        mounted(){
            this.getAllUsers();
        },
        methods: {
            getAllUsers(){
                this.$store.dispatch("getAllUsers");
            },
        }
    }
</script>
