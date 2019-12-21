//import { apiService }  from "../../endpoint/api.service";
import apiService from "../../endpoint/api.service";

var apiUrl = "user";


// Initial State
const state = {
  users: {},
  user: [],
  isLoading: true,
};

// Getters
const getters = {
  users(state) {
    return state.users;
  },
  user(state) {
    return state.user
  },
  userIsLoading(state) {
    return state.isLoading
  },
};

// Actions
const actions = {
  getAllUsers(context){
    context.commit("startLoading");
    return apiService.get(apiUrl)
      .then(({ data }) => {
        context.commit("setUsers", data);
        context.commit("endLoading");
      }).catch((error) => {
        throw error
      })
  },
  getUser(context, userSlug){
    if(state.user.id === userSlug){
      return;
    }

    context.commit("startLoading");
    return apiService.get(apiUrl, userSlug)
      .then(({ data }) => {
        context.commit("setUser", data);
        context.commit("endLoading");
      })
      .catch(error => {
        throw error
      });
  },

  userDelete(context, slug) {
    return apiService.delete(apiUrl, slug);
  },

  updateUser(context, payload) {
    // console.log()/
    return apiService.put(apiUrl, payload.id, payload)
      .then(({ data }) => {
        // console.log(data.data);
        context.commit("setUser", data);
      })
      .catch( (error) => {
        throw error
      });
  },

};

// Mutations
export const mutations = {
  startLoading(state) {
    state.isLoading = true;
  },
  endLoading(state) {
    state.isLoading = false;
  },
  setUsers(state, users){
    state.users = users;
  },
  setUser(state, user){
    state.user = user
  }
};


export default {
  state,
  getters,
  actions,
  mutations
}
