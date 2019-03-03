import fetch from '../utils/fetch'
import store from '../store'
export default {
  localLogin: function (data) {
    return fetch({
      url: 'login/siginin',
      method: 'post',
      data: data
    })
  },
  getSnsLogins: function () {
    return fetch({

    })
  },
  getMe: function () {
    return fetch({
      url: 'users/info/me',
      method: 'get'
    })
  },
  mdUser: function (data) {
    return fetch({
      
    })
  },
  getTagList:function () {
    return fetch({
      
    })
  },
  getApps:function () {
    return fetch({
      
    })
  },
  //article
  getIndexImage:function () {
    return fetch({
      
    })
  },
  getFrontArticleList:function (options) {
    return fetch({
      url: 'article/getFrontArticleList',
      method: 'get',
      params: options
    })
  },
  getFrontArticleCount:function () {
    return fetch({
      
    })
  },
  getFrontArticle:function (id) {
    return fetch({
      
    })
  },
  toggleLike:function (id) {
    return fetch({
      
    })
  },
  getPrenext:function (id,options) {
    return fetch({
      
    })
  },
  //comment
  getFrontCommentList:function (id) {
    return fetch({
      
    })
  },
  addNewComment:function (data) {
    return fetch({
      
    })
  },
  addNewReply: function (id,data) {
    return fetch({
      
    })
  },
  delComment:function (id) {
    return fetch({
      
    })
  },
  delReply: function (id,data) {
    return fetch({
      
    })
  },
  checkCaptch: function (data) {
    return fetch({
      url: 'auth/vercode/'+ store.getters.captchaKey,
      method: 'get',
      params: {
        captcha: data
      }
    })
  },
  getAccessToken: function(){
    return fetch({
      url: 'auth/jwttoken',
      method: 'post'
    })
  },
  getEditionList: function(){
    return fetch({
      url: 'article/edition',
      method: 'get'
    })
  },
  getBackendArticle: function(data){
    return fetch({
      url: 'article/backend',
      method: 'get',
      params: {
        id: data
      }
    })
  },
  saveBackendArticle: function(data){
    return fetch({
      url: 'article/backend/newblog',
      method: 'post',
      data: data
    })
  },
  updateBackendArticle: function(data){
    return fetch({
      url: 'article/backend/oldblog',
      method: 'post',
      data: data
    })
  },
  addEdition: function(data){
    return fetch({
      url: '',
      method: 'post',
      data: data
    })
  },
  saveEdition: function(data){
    return fetch({
      url: '',
      method: 'post',
      data: data
    })
  }

}