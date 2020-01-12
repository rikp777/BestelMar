import cookie from "../../helpers/cookie.service";
import apiService from "../../endpoint/api.service";


//Initial State
const state = {
  errors: null,
  user: [],
  isAuthenticated: false,
  isLoading: true,
};



// Getters
const getters = {
  authUser(state) {
    return state.user
  },
  isAuthenticated(state){
    return state.isAuthenticated;
  },
  authIsLoading(state){
    return state.isLoading;
  },
  getAuthErrors(state){
    return state.errors;
  }
};

// Actions
const actions = {
  login(context, credentials){
    return new Promise(resolve => {
      context.commit("setAuthLoading");
      apiService.post("/auth/login", credentials)
        .then(( response )  => {
          context.commit("setAuthUser", response.data);
          apiService.setHeader();
          context.commit("resetAuthLoading");
          resolve(response);
        })
        .catch(( error ) => {
          console.log(error)
          context.commit("setAuthError", error);
        });
    });
  },
  logout(context){
    context.commit("AuthPurge")
  },
  checkAuth(context){
    //check token state
    if(cookie.getToken()){
      apiService.setHeader();
    }else {
      context.commit("AuthPurge")
    }
  },
};

// Mutations
const mutations = {
  setAuthLoading(state) {
    state.isLoading = true;
  },
  resetAuthLoading(state) {
    state.isLoading = false;
  },
  setAuthError(state, errors){
    state.errors = errors
  },
  setAuthUser(state, payload) {
    console.log(payload)
    state.isAuthenticated = true;
    state.user = payload;
    state.errors = {};
    cookie.saveToken('user', payload.email)
  },
  AuthPurge(state){
    state.isAuthenticated = false;
    state.user = [];
    state.errors = {};
    cookie.destroyToken('user');
  }
};

export default {
  state,
  getters,
  actions,
  mutations
}
