import api from '../../api'
import {
  ARTICLE_DETAIL,
  TOGGLE_LIKE
} from '../types'

const state = {
  item:{}
}
// actions
const actions = {
  getArticleDetail ({ commit },id){
    api.getFrontArticle(id).then(response => {
      const json = response.data
      if(200 == json.code){
        let isLike = json.data.isLike
        const article = json.data.article
        commit(ARTICLE_DETAIL, {
          articleDetail: {...article,isLike:isLike}
        })
      }
    })
  },
  toggleLike({ commit }, id){
    api.toggleLike(id).then(response => {
      const json = response.data
      if(200 == json.code){
        commit(TOGGLE_LIKE, { 
          likeCount: json.data.likeCount,
          isLike: json.data.isLike 
        })
      }
    })
  },
}

const mutations = {
  [ARTICLE_DETAIL](state,action){
    state.item = {...state.item, ...action.articleDetail }
  },
  [TOGGLE_LIKE](state,action){
    state.item = {...state.item, isLike:action.isLike, likeCount: action.likeCount}
  }
}

export default {
  state,
  actions,
  mutations
}