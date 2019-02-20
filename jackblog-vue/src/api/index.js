import fetch from '../utils/fetch'
export default {
  localLogin: function (data) {
    return fetch({
      
    })
  },
  getSnsLogins: function () {
    return fetch({

    })
  },
  getMe: function () {
    return fetch({
      
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
      param: options
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
  }
}