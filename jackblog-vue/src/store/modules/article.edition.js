import api from '../../api'
import {showMsg} from '../actions'
import {
  EDITION_LIST,
  FAILURE_GET_EDITION,
  CURRENT_ARTICLE,
  CURRENT_EDITION,
  ADD_ARTICLE,
  ADD_EDITION,
  EDITION_FAILURE
} from '../types'
import {saveCookie,getCookie} from '../../utils/cookies'
const state = {
  items:[],
  cured: null,
  curar: null,
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
      if(200==json.data){
        store.commit(ADD_EDITION,data)
      }else{
        store.commit(EDITION_FAILURE)
      }
    },error=>{
      showMsg(store, error.message || '创建失败', 'error')
    })
  },
  updateEdition(store,data){
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
  },
  [ADD_ARTICLE](state, data){
    state.items[state.cured ==null ? getCookie('cured') : state.cured].articles.unshift(data.article)
  },
  [ADD_EDITION](state, data){
    state.items.unshift(data.edition)
  },
  [EDITION_FAILURE](state){

  }
}

export default {
  state,
  actions,
  mutations
}