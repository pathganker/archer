import api from '../../api'
import {showMsg} from '../actions'
import {
  COMMENT_LIST,
  SUCCESS_ADD_COMMENT,
  SUCCESS_ADD_REPLY,
  GET_COMMENT_LIST_FAILURE
} from '../types'

const state = {
  isFetching: false,
  items: []
}

const actions = {
  getCommentList({ commit },id){
    api.getFrontCommentList(id).then(response => {
      const json = response.data
      if(200 != json.code){
        return commit(GET_COMMENT_LIST_FAILURE)
      }
      commit(COMMENT_LIST, { commentList: json.data })
    }, 
    error => {
      commit(GET_COMMENT_LIST_FAILURE)
    })
  },
  addComment(store,data){
    api.addNewComment(data).then(response => {
      const json = response.data
      if(200!=json.code){
        return showMsg(store,json.message || '添加评论失败!')
      }
      showMsg(store,'添加评论成功!','success')
      store.commit(SUCCESS_ADD_COMMENT, { comment: data })
    }, 
    error => {
      showMsg(store, '添加评论失败!')
    })
  },
  addReply(store,data){
    api.addNewReply(data).then(response => {
      const json = response.data
      if(200!=json.code){
        return showMsg(store,json.message || '添加回复失败!')
      }
      showMsg(store,'添加回复成功!','success')
      store.commit(SUCCESS_ADD_REPLY, { reply: data })
    }, 
    error => {
      showMsg(store, '添加回复失败!')
    })
  }
}


const mutations = {
  [COMMENT_LIST](state,action){
    state.items = action.commentList
  },
  [SUCCESS_ADD_COMMENT](state,action){
    state.items.push(action.comment)
  },
  [SUCCESS_ADD_REPLY](state,action){
    state.items = state.items.map(item=>{
      if(item.uid === action.reply.commentId){
        item.replys.push(action.reply)
      }
      return item
    })
  },
  [GET_COMMENT_LIST_FAILURE](state){
    state.isFetching = false
    state.items = []
  }
}

export default {
  state,
  actions,
  mutations
}