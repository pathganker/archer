import fetch from '../utils/fetch'
export default {
  localLogin: function (data) {
    console.log(data)
    return fetch({
      headers: {'Content-Type':'application/x-www-form-urlencoded'},
      url: 'users/login',
      method: 'post',
      data: {
        'username': 1,
        'password': 2
      }
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
      url: 'auth/vercode',
      method: 'get',
      params: {
        captcha: data
      }
    })
  }
}