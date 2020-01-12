import apiService from "../endpoint/api.service";

const AccessControl = {
  router(store, router) {
    router.beforeEach((to, from, next) => {
      const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
      const requiresRoles = to.meta.requiresRoles;


      let authUser = '';
      if (store.getters.authUser !== undefined) {
        authUser = store.getters.authUser;
        store.dispatch("checkAuth");
        console.log('user=' + authUser + ' to=' + to.name + ' path=' + to.path + ' auth=' + requiresAuth + ' requiredRoles=' + requiresRoles)
      }


      // if(this.hasRight(requiresRoles, authUser)){
      //   next();
      // }else{
      //   console.log('unauthorized')
      //   next('/auth/login')
      // }



      if (requiresAuth && authUser.id === undefined) {
        console.log('not logged in');
        next('/auth/login');
      } else if (to.path === '/auth/login' && authUser.id) {
        console.log('already logged in');
        next('/');
      } else if (requiresRoles && authUser) {
        if (this.hasRight(requiresRoles, authUser)) {
          console.log('authorized');
          apiService.setHeader();
          next();
        } else {
          console.log('unauthorized');
          next('/')
        }
      }else{
        apiService.setHeader();
        next()
      }
    });
    // store.axios.interceptors.response.use(null, (error) =>{
    //   if(error.response.status === 401){
    //     store.dispatch('logout');
    //     router.push('/');
    //   }
    //   if (error.response.status === 403){
    //     router.push('/');
    //   }
    //
    //   return Promise.reject(error);
    // });
  },
  hasRight(requiresRoles, authUser) {
    let check = false;
    authUser.rights.forEach((item) => {
      if (requiresRoles.hasOwnProperty(item.id)) {
        check = true
      }
    });
    return check
  },
};
export default AccessControl;
