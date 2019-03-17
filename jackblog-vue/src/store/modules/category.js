import api from '../../api'
import {
  GET_CATEGORY,
  GET_CATEGORY_ARTICLE,
  GET_CATEGORY_ARTICLE_FAILURE,
} from '../types'
const state ={
  editions:[],
  articles:[],
  name: null,

}
const actions ={
  getCategory({commit}){
    api.getEditionFront().then(response =>{
      const json = response.data
      if(200 == json.code){
        commit(GET_CATEGORY,{editions:json.data})
      }
    })
  },
  getCategoryArticles({commit},id){
    api.getArticleList(id).then(response =>{
      const json = response.data
      if(200 == json.code){
        commit(GET_CATEGORY_ARTICLE,json.data)
      }
    },
    error=>{
      commit(GET_CATEGORY_ARTICLE_FAILURE)
    })
  }
}
const mutations ={
  [GET_CATEGORY](state,data){
    state.editions=data.editions
  },
  [GET_CATEGORY_ARTICLE](state,data){
    state.articles= data.articles
    state.name = data.name
  },
  [GET_CATEGORY_ARTICLE_FAILURE](state){
    state.articles=[]
    state.name=null
  }
}
export default {
  state,
  actions,
  mutations
}