import api from '../../api'
import {showMsg} from '../actions'
import {
  GET_ARTICLE_ORIGIN,
  FAILURE_GET_ARTICLE_ORIGIN,
  UPDATE_ARTICLE,
  SAVE_ARTICLE_DRAFT,
  GET_ARTICLE_ORIGIN_LOCAL
} from '../types'
import localstorage from '../../utils/localstorage'
const state = {
  origin:{},
  draft:{}
}
// actions
const actions = {
  getBackendArticle ({ commit }, id){
    if(localstorage.fetch(id)){
      return commit(GET_ARTICLE_ORIGIN_LOCAL,{article: localstorage.fetch(id)})
    }
    api.getBackendArticle(id).then(response => {
      const  json = response.data
      if(200==json.code){
        const article = json.data
        commit(GET_ARTICLE_ORIGIN,{article: article})
      }else{
        commit(FAILURE_GET_ARTICLE_ORIGIN)
      }
    },error=>{
      commit(FAILURE_GET_ARTICLE_ORIGIN)
    })
  },
  saveBackendArticle ({commit},data){
    commit(SAVE_ARTICLE_DRAFT,{data})
    api.saveBackendArticle(data).then(response => {
      const json = response.data
      if(200==json.code){
        commit(SAVE_ARTICLE_DRAFT,{article: data})
      }else{
        return showMsg(json.message || '保存失败')
      }
    }, error =>{
      showMsg('保存失败')
    })
  },
  updateBackendArticle ({commit}, data){
    commit(SAVE_ARTICLE_DRAFT,{data})
    api.commit(SAVE_ARTICLE_DRAFT,{data}).then(response => {
      const json = response.data
      if(200==json.code){
        commit(SAVE_ARTICLE_DRAFT,{article: data})
      }else{
        return showMsg(json.message || '更新失败')
      }
    }, error =>{
      showMsg('更新失败')
    })
  }

}

const mutations = {
  [GET_ARTICLE_ORIGIN](state, data){
    state.origin = data.article
    state.draft = data.article
    localstorage.save(data.article.id, data.article)
  },
  [UPDATE_ARTICLE](state, data){
    state.draft=data.article
    localstorage.save(data.article.id, data.article)
  },
  [SAVE_ARTICLE_DRAFT](state, data){
    state.draft=data.article
    localstorage.save(data.article.id, data.article)
  },
  [FAILURE_GET_ARTICLE_ORIGIN](state){
    state.orgin={}
    state.draft={}
  },
  [GET_ARTICLE_ORIGIN_LOCAL](state,data){
    state.origin = data.article
    state.draft = data.article
  }
}

export default {
  state,
  actions,
  mutations
}