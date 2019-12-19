
import Index from '../../components/user/Index'
import List from '../../components/user/List'
import Create from '../../components/user/Create'
import Read from '../../components/user/Read'
import Update from "../../components/user/Update";

export default [
  {
    path: '/user',
    component: Index,
    children: [
      {
        name: 'userList',
        path: '',
        component: List,
      },
      {
        name: 'userCreate',
        path: 'create',
        component: Create,
      },
      {
        name: 'userRead',
        path: ':id',
        component: Read,
      },
      {
        name: 'userUpdate',
        path: ':id',
        component: Update,
      }
    ]
  }
]
