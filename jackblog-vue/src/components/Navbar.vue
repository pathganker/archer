<template>
		<div v-show="navshow" class="navbar-box navbar-skin">
		  <div class="navbar-menu">
					<div class="navtitle">
						<router-link :to="{ path: '/' }"  class="title" title="首页">
							青团子的小窝
						</router-link>
						<p class="sub-title">做一个有梦想爱学习的实力派</p>
					</div>
					<div class="navbar">
						<ul class="nav navbar-items">
							<li class="navbar-item"> 
								<router-link :to="{ path: '/' }" title="首页"><span class="glyphicon glyphicon-home" aria-hidden="true"></span><br>
								首页
								</router-link>
							</li >
							<li class="navbar-item">
								<router-link :to="{ path: '/edition' }"  title="分类"><span class="glyphicon glyphicon-th" aria-hidden="true"></span><br>
								分类
								</router-link>
							</li>
							<li class="navbar-item">
								<router-link :to="{ path: '/tag' }" title="标签"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span><br>
								标签
								</router-link>
							</li>
							<li class="navbar-item">
								<router-link :to="{ path: '/about' }"  title="关于"><span class="glyphicon glyphicon-user" aria-hidden="true"></span><br>
								关于
								</router-link>
							</li>
							<li v-if="auth.token && admin" class="navbar-item">
								<router-link   :to="{ path: '/creation' }"  title="编辑"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><br>
								编辑
								</router-link>
							</li>
							<li v-if="auth.token && admin" class="navbar-item">
								<router-link   :to="{ path: '/manage' }"  title="管理"><span class="glyphicon glyphicon-ice-lolly-tasted" aria-hidden="true"></span><br>
								管理
								</router-link>
							</li>
						</ul>
					</div>
		  </div> 
		</div>
</template>

<script>
import defaultAvatar from '../assets/images/avatar.png'
import { mapState,mapActions } from 'vuex'
import {isLogin} from '../utils/cookies'
export default {
	data(){
		return {
			navshow: true
		}
	},
  computed: {
    ...mapState({
			auth: state => state.auth,
			styleMode: state => state.globalVal.styleMode,
			admin: state => state.auth.isAdmin
    }),
    defaultAvatar() {
      return defaultAvatar
    }		
  }, 
  created (){
		document.body.className = this.styleMode
    if(isLogin()){
      this.getUserInfo()
		}
  },
  methods: {
    ...mapActions([
      'changeStyleMode',
      'logout',
			'getUserInfo',
			'getAccessToken'
    ]),		
    changeMode(){
      this.changeStyleMode()
      document.body.className = this.styleMode
    },
		dropdown(){
			const dropdownMenu = document.getElementById('dropdownMenu')
			const isShow = dropdownMenu.style.display!=='block'?'block':'none'
			dropdownMenu.style.display = isShow
		},
		dropmenu(){
			const dropdownMenu = document.getElementById('droptestMenu')
			dropdownMenu.style.display = 'block'
		},
		hideNav(){
			this.navshow=false
		},
		showNav(){
			this.navshow=true
		}

	},
	directives:{
		//菜单外点击事件
		clickoutside:{
			bind:function(el,binding,vnode){
				function  hidemenu(el){
					el.style.display="none" 
				}
				function documentHandler(e){
					if(el.contains(e.target) || el.contains(e.target.parentElement.parentElement.nextElementSibling)){
						return true
					}else if (el.style.display!='none'){
						hidemenu(el)
					}
				}
				el._vueClickOutside_ = documentHandler
				document.addEventListener('click',documentHandler);
			},
			unbind:function(el,binding){
				document.removeEventListener('click',el._vueClickOutside_);
				delete el._vueClickOutside_;
			}
		}
	}
}
</script>