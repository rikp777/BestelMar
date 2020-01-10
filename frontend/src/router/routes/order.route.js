import Create from "../../components/order/Create.vue";
import Index from "../../components/order/Index.vue";
import List from "../../components/order/List.vue";
import History from "../../components/order/History";

export default [
  {
    path: '/order',
    component: Index,
    children: [
      {
        name: 'orderList',
        path: 'list',
        component: List,
      },
      {
        name: 'orderCreate',
        path: 'create',
        component: Create,
      },
      {
        name: 'orderHistory',
        path: 'history',
        component: History
      }
    ]
  }
]
