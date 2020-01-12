
import Index from '../../components/user/Index'
import List from '../../components/user/List'
import Create from '../../components/user/Create'
import Read from '../../components/user/Read'
import Update from "../../components/user/Update";
import Delete from "../../components/user/Delete";
import RightEnum from "../RightEnum";

export default [
  {
    path: '/user',
    component: Index,
    children: [
      {
        name: 'userList',
        path: '',
        component: List,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
      {
        name: 'userCreate',
        path: 'create',
        component: Create,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
      {
        name: 'userRead',
        path: ':id',
        component: Read,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
      {
        name: 'userUpdate',
        path: ':id',
        component: Update,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      },
      {
        name: 'userDelete',
        path: ':id',
        component: Delete,
        meta: {
          requiresAuth: true,
          requiresRoles: [ RightEnum.Admin, RightEnum.Employee ]
        }
      }
    ]
  }
]
