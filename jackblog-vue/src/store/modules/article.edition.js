import api from '../../api'
import {
  EDITION_LIST,
  FAILURE_GET_EDITION,
  CURRENT_ARTICLE,
  CURRENT_EDITION,
  ADD_ARTICLE
} from '../types'
import {saveCookie,getCookie} from '../../utils/cookies'
const state = {
  items:{},
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
  }
}

export default {
  state,
  actions,
  mutations
}