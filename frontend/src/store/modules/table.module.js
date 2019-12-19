import apiService from "../../endpoint/api.service";

const apiUrl = "table"

//Initial State
const state = {
  errors: null,
  tables: {},
  table: [],
  isAuthenticated: false,
  isLoading: true,
};



// Getters
const getters = {
  tables(state) {
    return state.tables
  },
  table(state){
    return state.table
  }
};

// Actions
const actions = {
  getAllTables(context){
    context.commit("startLoading");
    return apiService.get(apiUrl)
      .then(({ data }) => {
        context.commit("setTables", data);
        context.commit("endLoading");
      }).catch((error) => {
        throw error
      })
  },
  getTable(context, tableSlug){
    if(state.table.id === tableSlug){
      return;
    }

    context.commit("startLoading");
    return apiService.get(apiUrl, tableSlug)
      .then(({ data }) => {
        //console.log(data);
        context.commit("setTable", data);
        context.commit("endLoading");
      })
      .catch(error => {
        throw error
      });
  },
  createTable(context, payload){
    return apiService.post(apiUrl, payload)
      .then(({data}) => {
        context.commit("setTable", data);
      }).catch((error) => {
        throw error
      })
  },
  updateTable(context, payload){
    return apiService.put(apiUrl, payload.id, payload)
      .then(({data}) => {
        context.commit("setTable", data);
      }).catch((error) => {
        throw error
      })
  },
  deleteTable(context, tableSlug){
    console.log(tableSlug)
    return apiService.delete(apiUrl, tableSlug)
      .then(() => {

      }).catch((error) => {
        throw error
      })
  }
};

// Mutations
const mutations = {
  startLoading(state) {
    state.isLoading = true;
  },
  endLoading(state) {
    state.isLoading = false;
  },
  setErrors(state, errors){
    state.errors = errors
  },
  setTables(state, tables) {
    state.tables = tables;
  },
  setTable(state, table) {
    state.table = table;
  },
};

export default {
  state,
  getters,
  actions,
  mutations
}
