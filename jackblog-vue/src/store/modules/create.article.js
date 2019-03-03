import api from '../../api'
import {showMsg} from '../actions'
import {
  GET_ARTICLE_ORIGIN,
  FAILURE_GET_ARTICLE_ORIGIN,
  UPDATE_ARTICLE,
  SAVE_ARTICLE_DRAFT,
  GET_ARTICLE_ORIGIN_LOCAL,
  ARTICLE_SUCCESS
} from '../types'
import localstorage from '../../utils/localstorage'
const state = {
  draft: {},
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
  saveBackendArticle (store,data){
    store.commit(SAVE_ARTICLE_DRAFT,{article: data})
    api.saveBackendArticle(data).then(response => {
      const json = response.data
      if(200==json.code){
        store.commit(ARTICLE_SUCCESS)
      }else{
        return showMsg(store, json.message || '保存失败')
      }
    }, error =>{
      showMsg(store, error)
    })
  },
  updateBackendArticle (store, data){
    store.commit(UPDATE_ARTICLE,{article: data})
    api.updateBackendArticle(data).then(response => {
      const json = response.data
      if(200==json.code){
        store.commit(ARTICLE_SUCCESS)
        return showMsg(store, '保存成功','success')
      }else{
        return showMsg(store, json.message || '保存失败')
      }
    }, error =>{
      showMsg(store, error)
    })
  }

}

const mutations = {
  [GET_ARTICLE_ORIGIN](state, data){
    state.draft = data.article
  },
  [UPDATE_ARTICLE](state, data){
    state.draft=data.article
    localstorage.save(data.article.id, data.article)
  },
  [SAVE_ARTICLE_DRAFT](state, data){
    state.draft = data.article
    localstorage.save(state.draft.id, data.article)
  },
  [FAILURE_GET_ARTICLE_ORIGIN](state){
    state.draft={}
  },
  [GET_ARTICLE_ORIGIN_LOCAL](state,data){
    state.draft = data.article
  },
  [ARTICLE_SUCCESS](state){

  }
}

export default {
  state,
  actions,
  mutations
}