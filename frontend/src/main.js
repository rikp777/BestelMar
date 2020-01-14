
import Vue from 'vue'
import App from './App'



import router from './router'
import store from './store'


require('bootstrap');
import Multiselect from 'vue-multiselect'
import apiService from "./endpoint/api.service";
import AccessControl from "./helpers/accessControl.service";


// register globally
Vue.component('multiselect', Multiselect)
Vue.mixin({
  methods: {
    notEmptyObject(someObject){
      return Object.keys(someObject).length
    }
  }
})


Vue.config.productionTip = false;
apiService.initialize();
AccessControl.router(store, router);

new Vue({
  el: '#app',
  store,
  router,
  components: {
    App
  },
  template: '<App/>'
})
