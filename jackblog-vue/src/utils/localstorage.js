const STORAGE_KEY =(process.env.NODE_ENV === 'production')
  ? 'www.qingqfeng.com.cn:'
  : 'localhost:3000:'

export default {
  fetch: function(id){
    return window.JSON.parse(window.localStorage.getItem(STORAGE_KEY+id))
  },

  save: function(id,items){
    window.localStorage.setItem(STORAGE_KEY + id, window.JSON.stringify(items))
  }
}