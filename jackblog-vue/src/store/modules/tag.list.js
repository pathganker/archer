import api from '../../api'
import {
  GET_TAG_LIST_SUCCESS,
  GET_TAG_LIST_FAILURE
} from '../types'

const state = {
  items:[]
}

const actions = {
  getTagList({ commit }){
    api.getTagList().then(response => {
      const json= response.data
      if(200 != json.data){
        return commit(GET_TAG_LIST_FAILURE)
      }
      commit(GET_TAG_LIST_SUCCESS, { tagList: json.data })
    }, 
    error => {
      commit(GET_TAG_LIST_FAILURE)
    })
  }
}

const mutations = {
  [GET_TAG_LIST_FAILURE](state){
    state.items = []
  },
  [GET_TAG_LIST_SUCCESS](state,action){
    state.items = action.tagList
  }
}

export default {
  state,
  actions,
  mutations
}