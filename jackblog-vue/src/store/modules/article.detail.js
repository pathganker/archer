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
  getArticleDetail ({ commit },id,user){
    api.getFrontArticle(id).then(response => {
      const json = response.data
      if(200 == json.code){
        let isLike = false
        const article = response.data.data
        if(user){
          user.likes.map(item=>{
            if(item.toString() === article._id){
              isLike = true
            }
          })
        }
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
          like_count: json.data.count,
          isLike: json.data.isLike 
        })
      }
    })
  }
}

const mutations = {
  [ARTICLE_DETAIL](state,action){
    state.item = {...state.item, ...action.articleDetail }
  },
  [TOGGLE_LIKE](state,action){
    state.item = {...state.item, isLike:action.isLike, like_count: action.like_count}
  }
}

export default {
  state,
  actions,
  mutations
}