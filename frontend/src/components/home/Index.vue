<template>
  <div>
    <div class="container" v-if="isAuthenticated">
      <message></message>
    </div>
    <div v-else>
      <login></login>
    </div>
  </div>
</template>

<script>
    import login from "../auth/Login";
    import message from "./Message";
    export default {
        name: 'home-index',
        components: {message, login},
        data() {
            return {
                isAuthenticated: true,
            }
        }
    }
</script>
