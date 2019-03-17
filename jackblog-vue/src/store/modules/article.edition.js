import api from '../../api'
import {showMsg} from '../actions'
import {
  EDITION_LIST,
  FAILURE_GET_EDITION,
  CURRENT_ARTICLE,
  CURRENT_EDITION,
  ADD_ARTICLE,
  ADD_EDITION,
  EDITION_FAILURE,
  UPDATE_ARTICLE,
  DELETE_ARTICLE,
  DELETE_EDITION,
  MOVE_ARTICLE,
  SAVE_EDITION,
} from '../types'
import {saveCookie,getCookie} from '../../utils/cookies'
const state = {
  items:[],
  cured: null,
  curar: null,
  draft: null,
}
// actions
const actions = {
  getEditionList ({ commit }){
    api.getEditionList().then(response => {
      const  json = response.data
      if(200==json.code){
        const editions = json.data
        commit(EDITION_LIST,{editions: editions})
      }else{
        commit(FAILURE_GET_EDITION)
      }
    },error=>{
      commit(FAILURE_GET_EDITION)
    })
  },
  addEdition(store,data){
    store.commit(ADD_EDITION,data)
    api.addEdition(data).then(response =>{
      const json = response.data
      if(200==json.code){
        showMsg(store, '创建成功', 'success')
      }else{
        showMsg(store, json.message || '创建失败', 'error')
      }
    },error=>{
      showMsg(store, error.message || '创建失败', 'error')
    })
  },
  updateEdition(store,data){
    store.commit(SAVE_EDITION,data)
    api.updateEdition(data).then(response => {
      const  json = response.data
      if(200==json.code){
        showMsg(store, '保存成功', 'success')
      }else{
        showMsg(store, json.message || '保存失败', 'error')
      }
    },error=>{
      showMsg(store, error.message || '保存失败', 'error')
    })
  },
  deleteEdition(store,data){
    store.commit(DELETE_EDITION,{id: data})
    api.deleteEdition(data).then(response =>{
      const json = response.data
      if(200==json.code){
        showMsg(store, '保存成功', 'success')
      }else{
        showMsg(store, json.message || '保存失败', 'error')
      }
    },
    error =>{
      showMsg(store, error.message || '保存失败', 'error')
    })
  },
  deleteEditionWhole(store, data){

  }
}

const mutations = {
  [EDITION_LIST](state, data){
    state.items = data.editions
  },
  [FAILURE_GET_EDITION](state){
    state.items=[]
  },
  [CURRENT_EDITION](state, data){
    state.cured = data.cured
    saveCookie('cured', data.cured)
  },
  [CURRENT_ARTICLE](state, data){
    state.curar = data.curar
    saveCookie('curar', data.curar)
    saveCookie('arid', data.arid)
  },
  [ADD_ARTICLE](state, data){
    state.items[state.cured ==null ? getCookie('cured') : state.cured].articles.unshift(data.article)
  },
  [ADD_EDITION](state, data){
    state.items.unshift(data)
  },
  [EDITION_FAILURE](state){

  },
  [UPDATE_ARTICLE](state, data){
    state.items[state.cured].articles.map(item =>{
      if(data.id == item.id){
        if(data.title){
          item.title = data.title
        }
        if(data.backendContent){
          item.backendContent = data.backendContent
        }
        if(data.publish){
          item.publish = data.publish
        }
        if(data.publishTime){
          item.publishTime = data.publishTime
        }
      }
      return item
    })
  },
  [DELETE_ARTICLE](state,data){
    let items = state.items[state.cured].articles
    state.items[state.cured].articles.splice(items.findIndex(item =>
      item.id == data.id),1)
    state.curar--
  },
  [MOVE_ARTICLE](state,data){
    let ars = state.items[state.cured].articles
    let article = ars.map(item =>{
      if(item.id === data.id){
        item.edition = data.edition
      }
      return item
    })
    state.items[state.cured].articles.splice(ars.findIndex(item =>
      item.id == data.id),1)
    state.items.map(item =>{
      if(item.id == data.edition){
        item.articles.unshift(article[state.curar])
      }
    })
    state.curar==0?0:state.curar--
  },
  [SAVE_EDITION](state,data){
    state.items.map(item =>{
      if(item.id === data.id){
        item.title = data.title
        item.modifyTime = data.modifyTime
      }
      return item
    })
  },
  [DELETE_EDITION](state,data){
    state.items.splice(state.items.findIndex(item => item.id == data.id),1)
  }
}

export default {
  state,
  actions,
  mutations
}