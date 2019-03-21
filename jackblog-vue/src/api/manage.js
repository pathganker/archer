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
      params: JSON.stringify(data)
    })
  },
  batPublishArticle: function (data) {
    return fetch({
      url: 'manage/article/batpublish',
      method: 'get',
      params: JSON.stringify(data)
    })
  },
  batRecallArticle: function (data){
    return fetch({
      url: 'manage/article/batrecall',
      method: 'get',
      params: {ids:JSON.stringify(JSON.stringify(data))}
    })  
  }
}