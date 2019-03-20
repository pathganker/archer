import fetch from '../utils/fetch'
export default {
  getArticleManage: function (data) {
    return fetch({
      url: 'manage/article/list',
      method: 'get',
      params: data
    })
  },
}