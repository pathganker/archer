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
  mdUser: function (user) {
    return fetch({
      url: 'users/info/new',
      method: 'post',
      data: user
    })
  },
  getTagList:function () {
    return fetch({
      url: 'article/tag',
      method: 'get'
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
      url: 'article/front/list',
      method: 'get',
      params: options
    })
  },
  getFrontArticleCount:function (options) {
    return fetch({
      url: 'article/front/count',
      method: 'get',
      params: options
    })
  },
  getFrontArticle:function (id) {
    return fetch({
      url: 'article/front/detail',
      method: 'get',
      params: {
        id: id
      }
      
    })
  },
  toggleLike:function (id) {
    return fetch({
      url: 'article/front/togglelike',
      method: 'get',
      params: {
        id:id
      }
    })
  },
  getPrenext:function (id,options) {
    return fetch({
      url: 'article/front/next/'+ id,
      method: 'get',
      params: options
    })
  },
  //comment
  getFrontCommentList:function (id) {
    return fetch({
      url: 'comment',
      method: 'get',
      params: {
        id : id
      }
    })
  },
  addNewComment:function (data) {
    return fetch({
      url: 'comment/new',
      method: 'post',
      data: data
    })
  },
  addNewReply: function (data) {
    return fetch({
      url: 'comment/reply',
      method: 'post',
      data: data
    })
  },
  delComment:function (id) {
    return fetch({
      url: 'comment/less',
      method: 'delete',
      params: {id:id}
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
  addBackendArticle: function(data){
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
  deleteBackednArticle: function(data){
    return fetch({
      url: 'article/backend/blogless',
      method: 'delete',
      params: {id:data}
    })
  },
  addEdition: function(data){
    return fetch({
      url: 'article/backend/newedition',
      method: 'post',
      data: data
    })
  },
  updateEdition: function(data){
    return fetch({
      url: 'article/backend/oldedition',
      method: 'post',
      data: data
    })
  },
  deleteEdition: function(data){
    return fetch({
      url: 'article/backend/edition/less',
      method: 'delete',
      params:{id:data}
    })
  },
  isLogin: function(){
    return fetch({
      url: 'login/check',
      method: 'get'
    })
  },
  uploadCover: function(data){
    return fetch({
      url: 'article/backend/upload/cover/'+data.id,
      method: 'post',
      data: data.picture
    })
  },
  getEditionFront: function(){
    return fetch({
      url: 'category/front',
      method: 'get'
    })
  },
  getArticleList: function(id){
    return fetch({
      url: 'category/edition',
      method: 'get',
      params: {id:id}
    })
  },
  getOauthToken: function(data){
    return fetch({
      url: 'auth/oauth2/callback',
      method: 'get',
      params:{
        provider:data.provider,
        code:data.code
      }
    })
  },
  uploadAvatar: function(data){
    return fetch({
      url: 'users/upload/avatar/'+data.id,
      method: 'post',
      data: data.picture
    })
  },
  uploadContentPic: function(data){
    return fetch({
      url: 'article/backend/upload/content/'+data.id,
      method: 'post',
      data: data.picture
    })
  }
}