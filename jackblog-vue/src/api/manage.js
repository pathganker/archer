import fetch from '../utils/fetch'
export default {
  getArticleManage: function (data) {
    return fetch({
      url: 'manage/article/list',
      method: 'get',
      params: data
    })
  },
  batDeleteArticle: function (data) {
    return fetch({
      url: 'manage/article/batdelete',
      method: 'delete',
      params: {ids:data+''}
    })
  },
  batPublishArticle: function (data) {
    return fetch({
      url: 'manage/article/batpublish',
      method: 'get',
      params: {ids:data+''}
    })
  },
  batRecallArticle: function (data){
    return fetch({
      url: 'manage/article/batrecall',
      method: 'get',
      params: {ids:data+''}
    })  
  }
}