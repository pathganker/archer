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
  CHANGE_EDIT_STATUS
} from '../types'
const state = {
  items:[],
  isedit: false,
  arid: null,
  edid: null,
}
// actions
const actions = {
  getEditionList ({ commit }){
    return  api.getEditionList().then(response => {
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
    state.edid = data.edid
  },
  [CURRENT_ARTICLE](state, data){
    state.arid = data.arid
  },
  [ADD_ARTICLE](state, data){
    state.items.map(item => {
      if(item.id == data.edition){
        item.articles.unshift(data)
      }
      return item
    })
  },
  [ADD_EDITION](state, data){
    state.items.unshift(data)
  },
  [EDITION_FAILURE](state){

  },
  [UPDATE_ARTICLE](state, data){
    state.items.map(ed =>{
      ed.articles.map(item =>{
        if(data.id == item.id){
          if(data.title!=null){
            item.title = data.title
          }
          if(data.backendContent!=null){
            item.backendContent = data.backendContent
          }
          if(data.publish!=null){
            item.publish = data.publish
          }
          if(data.publishTime!=null){
            item.publishTime = data.publishTime
          }
        }
        return item
      })
      return ed
    })
  },
  [DELETE_ARTICLE](state,data){
    state.items.map(item =>{
      const index = item.articles.findIndex(item =>
        item.id == data.id)
      if(index!=-1){
        item.articles.splice(index,1)
        state.arid=null
      }
      return item
    })
  },
  [MOVE_ARTICLE](state,data){
    let article = null
    state.items.map(item =>{
      const index = item.articles.findIndex(ar =>
        ar.id == data.id)
      if(index!=-1){
        article =item.articles[index]
        item.articles.splice(index,1)
      }
      return item
    })
    state.items.map(item =>{
      if(item.id == data.edition){
        item.articles.push(article)
      }
      return item
    })
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
  },
  [CHANGE_EDIT_STATUS](state,data){
    state.isedit=data
  },
}

export default {
  state,
  actions,
  mutations
}