import api from '../../api'
import {showMsg} from '../actions'
import {
  GET_ARTICLE_ORIGIN,
  FAILURE_GET_ARTICLE_ORIGIN,
  UPDATE_ARTICLE,
  GET_ARTICLE_ORIGIN_LOCAL,
  ARTICLE_SUCCESS,
  ADD_ARTICLE,
  DELETE_ARTICLE
} from '../types'
import localstorage from '../../utils/localstorage'
const state = {
  storage:''
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
  addBackendArticle (store,data){
    store.commit(ADD_ARTICLE,{article:data})
    api.addBackendArticle(data).then(response => {
      const json = response.data
      if(200==json.code){
        store.commit(ARTICLE_SUCCESS)
      }else{
        return showMsg(store, json.message || '保存失败')
      }
    }, 
    error =>{
      showMsg(store, error)
    })
  },
  updateBackendArticle (store, data){
    store.commit(UPDATE_ARTICLE, data)
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
  },
  deleteBackendArticle (store, data){
    store.commit(DELETE_ARTICLE,{id: data})
    api.deleteBackednArticle(data).then(response => {
      const json = response.data
      if(200==json.code){
        return showMsg(store, '已删除','success')
      }else{
        return showMsg(store, json.message || '未成功删除')
      }
    },
    error =>{
      showMsg(store, error)
    })
  },
  uploadCover(store, data){
    api.uploadCover(data).then(response =>{
      const json = response.data
      if(200== json.code){
        return showMsg(store, '保存成功','success')
      }else{
        return showMsg(store, json.message || '未成功保存')
      }
    },error=>{
      showMsg(store, error)
    })
  }

}

const mutations = {
  [GET_ARTICLE_ORIGIN](state, data){
    state.storage = data.article
  },
  [FAILURE_GET_ARTICLE_ORIGIN](state){
    state.storage={}
  },
  [GET_ARTICLE_ORIGIN_LOCAL](state,data){
    state.storage = data.article
  },
  [ARTICLE_SUCCESS](state){

  },
}

export default {
  state,
  actions,
  mutations
}