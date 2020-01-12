import Create from "../../components/table/Create.vue";
import List from "../../components/table/List.vue";
import Index from "../../components/table/Index.vue";
import Update from "../../components/table/Update.vue";
import Delete from "../../components/table/delete.vue";
import RightEnum from "../RightEnum";

export default [
  {
    path: '/table',
    component: Index,
    children: [
      {
        name: 'tableList',
        path: '',
        component: List,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
      {
        name: 'tableCreate',
        path: 'create',
        component: Create,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
      {
        name: 'tableUpdate',
        path: 'update/:id',
        component: Update,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
      {
        name: 'tableDelete',
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
