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
  }
};

// Actions
const actions = {
  login(context, credentials){
    return new Promise(resolve => {
      context.commit("setAuthLoading");
      apiService.post("/auth/login", credentials)
        .then(( response )  => {
          context.commit("setAuthUser", credentials.email);
          apiService.setHeader();
          context.commit("resetAuthLoading");
          resolve(response);
        })
        .catch(( error ) => {
          context.commit("setAuthError", error.data);
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
    state.isAuthenticated = true;
    state.user = payload;
    state.errors = {};
    cookie.saveToken(payload)
  },
  AuthPurge(state){
    state.isAuthenticated = false;
    state.user = [];
    state.errors = {};
    cookie.destroyToken();
  }
};

export default {
  state,
  getters,
  actions,
  mutations
}
