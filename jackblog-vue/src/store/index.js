import Vue from 'vue'
import Vuex from 'vuex'
import createLogger from 'vuex/dist/logger'
import apps from './modules/apps'
import articleList from './modules/article.list'
import prenextArticle from './modules/article.prenext'
import articleDetail from './modules/article.detail'
import editionList from './modules/article.edition'
import backendArticle from './modules/create.article'
import auth from './modules/auth'
import commentList from './modules/comment.list'
import globalVal from './modules/global.val'
import options from './modules/options'
import logins from './modules/logins'
import tagList from './modules/tag.list'
import showmsg from './modules/showmsg'
import * as actions from './actions'
import getters from './getters'
import category from './modules/category'

const debug = process.env.NODE_ENV !== 'production'
Vue.use(Vuex)

export default new Vuex.Store({
  actions,
  getters,
  modules: {
    apps,
    editionList,
    articleList,
    backendArticle,
    prenextArticle,
    articleDetail,
    auth,
    commentList,
    globalVal,
    options,
    logins,
    tagList,
    showmsg,
    category
  },
  strict: debug,
  plugins: debug ? [createLogger()] : []
})
