<template>
  <nav class="navbar navbar-expand-lg container navbar-light bg-light rounded-lg mb-2">
    <router-link
      class="navbar-brand"
      :to="{ name: 'home' }"
    >Bestel Mar
    </router-link>


    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarBestelMar">
      <span class="navbar-toggler-icon"></span>
    </button>


    <div class="collapse navbar-collapse" id="navbarBestelMar">
      <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

        <li class="nav-item" v-if="authUser == null">
          <router-link
            class="nav-link"
            :to="{ name: 'orderCreate' }"
          >Create Order
          </router-link>
        </li>
        <li class="nav-item" v-if="(hasRight('admin') || hasRight('employee'))">
          <router-link
            class="nav-link"
            :to="{ name: 'orderList' }"
          >Orders
          </router-link>
        </li>
        <li class="nav-item" v-if="(hasRight('admin') || hasRight('employee'))">
          <router-link
            class="nav-link"
            :to="{ name: 'tableList' }"
          >Tables
          </router-link>
        </li>
        <li class="nav-item" v-if="(hasRight('admin') || hasRight('employee'))">
          <router-link
            class="nav-link"
            :to="{ name: 'articleList' }"
          >Articles
          </router-link>
        </li>
        <li class="nav-item" v-if="(hasRight('admin'))">
          <router-link
            class="nav-link"
            :to="{ name: 'userList' }"
          >Users
          </router-link>
        </li>

      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="nav-item dropdown" v-if="authUser != null">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
             aria-haspopup="true" aria-expanded="false">
            {{authUser.email}}
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <router-link
              class="dropdown-item"
              :to="{ name: 'logout' }"
            >Logout
            </router-link>
          </div>
        </li>
        <div class="row" v-else>
          <li class="nav-item col">
            <router-link
              class="nav-link col"
              :to="{ name: 'login' }"
            >Login
            </router-link>

          </li>
          <li class="nav-item border-left mr-1">
            <router-link
              class="nav-link ml-2"
              :to="{ name: 'register' }"
            >Register
            </router-link>

          </li>
        </div>
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
        },
        methods: {
          hasRight(rightName){
            //console.log(this.authUser)
            let hasRight = false;
            if(this.authUser){
              if(this.authUser.rights){
                this.authUser.rights.forEach((right)=>{
                  if(right.name === rightName){
                    hasRight = true;
                  }
                });
              }
            }
            return hasRight;
          }
        }


    }
</script>
