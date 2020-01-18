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
  tablesActive(state){
    return state.tables.filter(t => t.disabled === false);
  },
  tableById: (state) => (id) => {
    return state.tables.find(t => t.id === id)
  },
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
  tableDelete(context, slug) {
    return apiService.delete(apiUrl, slug);
  },
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
