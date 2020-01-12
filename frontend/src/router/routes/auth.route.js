import Index from "../../components/auth/Index.vue";
import Login from "../../components/auth/Login.vue";
import Logout from "../../components/auth/Logout.vue";
import Register from "../../components/auth/Register";
import RightEnum from "../RightEnum";

export default [
  {
    path: '/auth',
    component: Index,
    children: [
      {
        name: 'login',
        path: 'login',
        component: Login,
      },
      {
        name: 'logout',
        path: 'logout',
        component: Logout,
        meta: {
          requiresAuth: true,
        }
      },
      {
        name: 'register',
        path: 'register',
        component: Register,
      },
    ]
  }
]
