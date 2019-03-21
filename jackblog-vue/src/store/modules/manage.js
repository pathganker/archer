import api from '../../api/manage.js'
import {showMsg} from '../actions'
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
  },
  batDeleteArticle(store,data){
    return api.batDeleteArticle(data).then(response =>{
      showMsg(store, '删除成功', 'success')
    },
    error =>{
      showMsg(store, error, 'error')
    })
  },
  batPublishArticle(store,data){
    return api.batPublishArticle(data).then(response =>{
      showMsg(store, '发布成功', 'success')
    },
    error =>{
      showMsg(store, error , 'error')
    })
  },
  batRecallArticle(store,data){
    return api.batRecallArticle(data).then(response =>{
      showMsg(store, '删除成功', 'success')
    },
    error =>{
      showMsg(store, error, 'error')
    })
  },  
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