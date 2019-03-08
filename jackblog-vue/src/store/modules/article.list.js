import api from '../../api'
import {
  ARTICLE_LIST,
  ADD_ARTICLE_LIST,
  REQUEST_ARTICLE_LIST,
  GET_ARTICLE_LIST_FAILURE,
  ARTICLE_TOTAL
} from '../types'

const state = {
  isFetching: false,
  isMore: true,
  items: [],
  totalCount: 0
}

const actions = {
  getArticleList({ commit }, {options, isAdd=false}){
    commit(REQUEST_ARTICLE_LIST)
    api.getFrontArticleList(options).then(response => {
      const json = response.data
      if(200 != json.code){
        return commit(GET_ARTICLE_LIST_FAILURE)
      }
      const isMore = !(json.data.length < options.pageSize)
      isAdd
        ? commit(ADD_ARTICLE_LIST,{
          articleList: json.data,
          isMore:isMore
        })
        : commit(ARTICLE_LIST,{
          articleList: json.data,
          isMore:isMore
        })
    }, 
    error => {
      commit(GET_ARTICLE_LIST_FAILURE)
    })
  },
  getArticleTotal({commit},{options}) {
    api.getFrontArticleCount(options).then(response => {
      const json =response.data
      if(200!=json.code){
        return commit(GET_ARTICLE_LIST_FAILURE)
      }
      commit(ARTICLE_TOTAL,{totalCount: json.data})
    },
    error => {
      commit(GET_ARTICLE_LIST_FAILURE)
    })
  }
}

const mutations = {
  [REQUEST_ARTICLE_LIST](state){
    state.isFetching = true
  },
  [ARTICLE_LIST](state,action){
    state.isFetching = false
    state.isMore = action.isMore
    state.items = action.articleList
  },
  [GET_ARTICLE_LIST_FAILURE](state){
    state.isFetching = false
  },
  [ADD_ARTICLE_LIST](state,action){
    state.isFetching = false
    state.isMore = action.isMore
    state.items = [...state.items, ...action.articleList]
  },
  [ARTICLE_TOTAL](state, data){
    state.totalCount= data.totalCount
  }
}

export default {
  state,
  actions,
  mutations
}