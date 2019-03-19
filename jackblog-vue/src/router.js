import Vue from 'vue'
import Router from 'vue-router'
import {LoadingBar} from 'iview'
const Home = () => import('components/Home/index')
const Login = () => import('components/Login/index')
const Settings = () => import('components/Settings/index')
const Article = () => import('components/Article/index')
// const Apps = () => import('components/Apps/index')
const NotFound = () => import('components/NotFound')
const Creation = () => import('components/Creation/index')
const About = () => import('components/About/index')
const Tags = () => import('components/Tags/index')
const Edition = () => import('components/Edition/index')
const Category =() => import('components/Edition/edition')
const Manage =() => import('components/Manage/index')
const ArticelMana =() => import('components/Manage/article')
const TagMana =() => import('components/Manage/tag')
const My =() => import('components/Manage/my')
const Other =() => import('components/Manage/other')
const Statics =() => import('components/Manage/statics')
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
    // {
    //   path: '/apps',
    //   name: 'apps',
    //   component: Apps
    // },
    {
      path: '/edition',
      name: 'edition',
      component: Edition,
    },
    {
      path: '/edition/:eid',
      name: 'category',
      component: Category,
    },
    {
      path: '/tag',
      name: 'tags',
      component: Tags
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
    {
      path: '/about',
      name: 'about',
      component: About,
    },
    {
      path: '/manage',
      name: 'manage',
      component: Manage,
      meta:{
        requiresAuth: true
      },
      children:[
        {
          path: '#/article',
          name: 'articleMana',
          component: ArticelMana,
        },
        {
          path: 'tag',
          name: 'tag',
          component: TagMana,
        },
        {
          path: 'my',
          name: 'my',
          component: My,
        },
        {
          path: 'other',
          name: 'other',
          component: Other,
        },
        {
          path: 'statics',
          name: 'statics',
          component: Statics,
        }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  LoadingBar.start()
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

router.afterEach(route => {
  LoadingBar.finish()
})
export default router