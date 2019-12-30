import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios"
import cookie from "../helpers/cookie.service";

const apiService  = {
  initialize() {
    Vue.use(VueAxios, axios);
    Vue.axios.defaults.baseURL = "http://localhost:8085";
    Vue.axios.defaults.timeout = 1000;
    Vue.axios.defaults.headers.common = {
      // 'Access-Control-Allow-Origin': '*',
      // 'Access-Control-Allow-Methods': 'GET, POST, OPTIONS, PUT, PATCH, DELETE',
      // 'Access-Control-Allow-Headers': 'Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,X-Access-Token,XKey,Authorization',
    }
  },

  setHeader() {
    console.log("auth")
    Vue.axios.defaults.headers.common["Authorization"] = `${cookie.getToken('user')}`
  },

  query(resource, params) {
    return Vue.axios
      .get(`${resource}/`, params)
      .catch(error => {
        throw `[SuperFit] ApiService ${resource} \n ${error}`;
      });
  },

  get(resource, slug = "") {
    // throw(`${resource}/${slug}`);
    return Vue.axios
      .get(`${resource}/${slug}`)
      .catch(error => {
        throw `[SuperFit] ApiService ${resource} \n ${error}`;
      });
  },

  post(resource, params) {
    return Vue.axios
      .post(`${resource}`, params)
      .catch(error => {
        throw `[SuperFit] ApiService ${resource} \n ${error}`;
      });
  },

  put(resource, slug = "", params) {
    return Vue.axios
      .put(`${resource}/${slug}`, params)
      .catch(error => {
        throw `[SuperFit] ApiService ${resource} \n ${error}`;
      });
  },

  delete(resource, slug) {
    return Vue.axios
      .delete(`${resource}/${slug}`)
      .catch(error => {
        throw `[SuperFit] ApiService ${resource}/${slug} \n ${error.response.data.message}`;
      });
  }
};

export default apiService;
