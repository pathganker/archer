import {
  CHANGE_OPTIONS
} from '../types'

const state = {item: {page: 1, pageSize: 10, tag: '', sortName:null, sortType:'desc'}}

const actions = {
  changeOptions({ commit },options){
    commit(CHANGE_OPTIONS, { options: options })
  }
}

const mutations = {
  [CHANGE_OPTIONS](state, action) {
    state.item = {...state.item, ...action.options}
  }
}

export default {
  state,
  actions,
  mutations
}