import Vue from 'vue'
import Router from 'vue-router'
const Home = () => import('components/Home/index')
const Login = () => import('components/Login/index')
const Settings = () => import('components/Settings/index')
const Article = () => import('components/Article/index')
const Apps = () => import('components/Apps/index')
const NotFound = () => import('components/NotFound')
const Creation = () => import('components/Creation/index')
import {isLogin} from './utils/cookies'
import store from './store'
Vue.use(Router)

const router = new Router({
  mode: 'history',
  // scrollBehavior: true,  
  linkActiveClass:'',
  linkExactActiveClass:'',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta:{
        requiresNotAuth: true,
        goTop: true
      }      
    },
    {
      path: '/settings',
      name: 'settings',
      component: Settings,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '/article/:aid',
      name: 'article',
      component: Article,
      meta: {
        goTop: true
      }      
    },
    {
      path: '/apps',
      name: 'apps',
      component: Apps
    },
    {
      path: '*',
      component: NotFound
    },
    {
      path: '/creation',
      name: 'creation',
      component: Creation,
      meta:{
        requiresAuth: true
      } 
    },
    // {
    //   path: '/about',
    //   name: 'about',
    //   component: About,
    // }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.goTop)) {
    window.scroll(0, 0) 
  }

  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!isLogin() || !store.getters.isSigin) {
      return next({path: '/login'})
    }
  }
  if (to.matched.some(record => record.meta.requiresNotAuth)) {
    // if (isLogin()) {
    //   return next({path: '/'})
    // }
  }
  next()
})
export default router