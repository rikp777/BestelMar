<template>
  <div id="app">
    <main role="main" class="mt-3">
      <AppHeader></AppHeader>
      <section>
        <div class="container">
          <router-view></router-view>
        </div>
      </section>
    </main>
  </div>
</template>
<style lang="scss">
  @import "assets/scss/theme";
</style>
<script>
import AppHeader from "./components/partials/AppHeader"
export default {
  name: 'App',
  components: { AppHeader }
}
</script>



