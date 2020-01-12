import Create from "../../components/order/Create.vue";
import Index from "../../components/order/Index.vue";
import List from "../../components/order/List.vue";
import History from "../../components/order/History";
import RightEnum from "../RightEnum";

export default [
  {
    path: '/order',
    component: Index,
    children: [
      {
        name: 'orderList',
        path: 'list',
        component: List,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
      {
        name: 'orderCreate',
        path: 'create',
        component: Create,
      },
      {
        name: 'orderHistory',
        path: 'history',
        component: History,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee, RightEnum.Costumer ]
        }
      }
    ]
  }
]
