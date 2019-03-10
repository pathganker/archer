import api from '../../api'
import { PRENEXT_ARTICLE } from '../types'

const state = {
  next:{},
  prev:{}
}

const actions = {
  getPrenext({ commit,rootState }, id){
    return api.getPrenext(id,rootState.options.item).then(response => {
      const json = response.data
      if(200 == json.code){
        commit(PRENEXT_ARTICLE, { prenextArticle: json.data })
      }
    })
  }
}

const mutations = {
  [PRENEXT_ARTICLE](state,action){
    state.prev = action.prenextArticle[0] || {}
    state.next = action.prenextArticle[1] || {}
  }
}

export default {
  state,
  actions,
  mutations
}