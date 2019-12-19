import apiService from "../../endpoint/api.service";
import article from "../../router/routes/article.route";

const apiUrl = "article"

//Initial State
const state = {
  errors: null,
  articles: {},
  article: [],
  isAuthenticated: false,
  isLoading: true,
};



// Getters
const getters = {
  articles(state) {
    return state.articles
  },
  article(state){
    return state.article
  }
};

// Actions
const actions = {
  getAllArticles(context){
    context.commit("startLoading");
    return apiService.get(apiUrl)
      .then(({ data }) => {
        context.commit("setArticles", data);
        context.commit("endLoading");
      }).catch((error) => {
        throw error
      })
  },
  getArticle(context, articleSlug){
    if(state.article.id === articleSlug){
      return;
    }

    context.commit("startLoading");
    return apiService.get(apiUrl, articleSlug)
      .then(({ data }) => {
        context.commit("setArticle", data);
        context.commit("endLoading");
      })
      .catch(error => {
        throw error
      });
  },
  createArticle(context, payload){
    return apiService.post(apiUrl, payload)
      .then(({data}) => {
        context.commit("setArticle", data);
      }).catch((error) => {
        throw error
      })
  },
  updateArticle(context, payload){
    return apiService.put(apiUrl, payload.id, payload)
      .then(({data}) => {
        context.commit("setArticle", data);
      }).catch((error) => {
        throw error
      })
  },
  deleteArticle(context, articleSlug){
    console.log(articleSlug)
    return apiService.delete(apiUrl, articleSlug)
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
  setArticles(state, articles) {
    state.articles = articles;
  },
  setArticle(state, article) {
    state.article = article;
  },
};

export default {
  state,
  getters,
  actions,
  mutations
}
