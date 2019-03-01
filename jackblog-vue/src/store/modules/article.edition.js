import api from '../../api'
import {
  EDITION_LIST,
  FAILURE_GET_EDITION
} from '../types'
const state = {
  items:{}
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
  }
}

export default {
  state,
  actions,
  mutations
}