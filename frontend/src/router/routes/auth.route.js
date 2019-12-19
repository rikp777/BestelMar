import Index from "../../components/auth/Index.vue";
import Login from "../../components/auth/Login.vue";
import Logout from "../../components/auth/Logout.vue";

export default [
  {
    path: '/auth',
    component: Index,
    children: [
      {
        name: 'login',
        path: '',
        component: Login,
      },
      {
        name: 'logout',
        path: '',
        component: Logout,
      },
    ]
  }
]
