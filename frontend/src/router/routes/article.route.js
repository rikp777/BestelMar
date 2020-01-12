import Create from "../../components/article/Create.vue";
import List from "../../components/article/List.vue";
import Index from "../../components/article/Index.vue";
import Update from "../../components/article/Update.vue";
import Delete from "../../components/article/delete.vue";
import RightEnum from "../RightEnum";

export default [
  {
    path: '/article',
    component: Index,
    children: [
      {
        name: 'articleList',
        path: '',
        component: List,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
      {
        name: 'articleCreate',
        path: 'create',
        component: Create,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
      {
        name: 'articleUpdate',
        path: 'update/:id',
        component: Update,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
      {
        name: 'articleDelete',
        path: 'delete/:id',
        component: Delete,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
    ]
  }
]
