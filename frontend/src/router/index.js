import Vue from 'vue'
import Router from 'vue-router'

import user from './routes/user.route'
import auth from './routes/auth.route'
import home from './routes/home.route'
import order from './routes/order.route'
import article from './routes/article.route'
import table from './routes/table.route'


Vue.use(Router)

const router = new Router({
  routes: [
    ...user,
    ...auth,
    ...home,
    ...order,
    ...article,
    ...table
  ],
  mode: 'history'
});

export default router
