<template>
  <nav class="navbar navbar-expand-lg container navbar-light bg-light rounded-lg mb-2">
    <router-link
      class="navbar-brand"
      :to="{ name: 'home' }"
    >Bestel Mar</router-link>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarBestelMar" >
      <span class="navbar-toggler-icon"></span>
    </button>


    <div class="collapse navbar-collapse" id="navbarBestelMar">
      <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <li class="nav-item" v-if="!authUser.length">
          <router-link
            class="nav-link"
            :to="{ name: 'orderCreate' }"
          >Create Order</router-link>
        </li>
        <li class="nav-item" v-if="authUser.length">
          <router-link
            class="nav-link"
            :to="{ name: 'orderList' }"
          >Orders</router-link>
        </li>
        <li class="nav-item" v-if="authUser.length">
          <router-link
            class="nav-link"
            :to="{ name: 'tableList' }"
          >Tables</router-link>
        </li>
        <li class="nav-item" v-if="authUser.length">
          <router-link
            class="nav-link"
            :to="{ name: 'articleList' }"
          >Articles</router-link>
        </li>
        <li class="nav-item" v-if="authUser.length">
          <router-link
            class="nav-link"
            :to="{ name: 'userList' }"
          >Users</router-link>
        </li>

      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="nav-item dropdown" v-if="authUser.length">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            {{authUser}}
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <router-link
              class="dropdown-item"
              :to="{ name: 'logout' }"
            >Logout</router-link>
          </div>
        </li>
        <li class="nav-item" v-else>
          <router-link
            class="nav-link"
            :to="{ name: 'login' }"
          >Login</router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
    import {mapGetters} from 'vuex';
    export default {
        name: 'app-header',
        computed: {
            authUser() {
                return this.$store.getters.authUser;
            },
        }
    }
</script>
