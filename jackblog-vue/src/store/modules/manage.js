import api from '../../api/manage.js'
import {
  GET_ARTICLE_MANA,
  GET_ARTICLE_MANA_FAILURE
} from '../types'

const state = {
  articles: {
    total: 0,
    items: []
  }
}
const actions ={
  getArticleMana({commit},options){
    return api.getArticleManage(options).then(response =>{
      const json = response.data
      if(200 == json.code){
        commit(GET_ARTICLE_MANA, json.data)
      }else{
        commit(GET_ARTICLE_MANA_FAILURE)
      }
    },error =>{
      console.log(error)
    })
  }
}

const mutations={
  [GET_ARTICLE_MANA](state,data){
    state.articles.items = data.articles
    state.articles.total = data.total
  },
  [GET_ARTICLE_MANA_FAILURE](state){
    state.articles.items = []
    state.articles.total = 0
  }
}

export default{
  state,
  actions,
  mutations
}