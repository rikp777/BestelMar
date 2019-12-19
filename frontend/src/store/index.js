import Vue from 'vue';
import Vuex from "vuex";
import VuexPersist from 'vuex-persistedstate';
import Cookies from 'js-cookie'

import auth from './modules/auth.module.js'
import user from './modules/user.module.js'
import order from './modules/order.module.js'
import article from './modules/article.module.js'
import table from './modules/table.module.js'


Vue.use(Vuex);
const production = false;
export default new Vuex.Store({
  plugins: [
    VuexPersist({
      storage: {
        getItem: key => Cookies.get(key),
        setItem: (key, value) => Cookies.set(key, value, {secure: production }),
        removeItem: key => Cookies.remove(key)
      }
    })
  ],
  modules: {
    auth,
    user,
    order,
    article,
    table
  },
});
