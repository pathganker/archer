<template>
		<div class="navbar-box navbar-skin">
		  <div class="navbar-menu">
					<div class="navtitle">
						<router-link :to="{ path: '/' }"  class="title" title="首页">
							青团子的小窝
						</router-link>
						<p class="sub-title">做一个有梦想爱学习的实力派</p>
					</div>
					<div v-if="auth.token && auth.user" class="navbar-items">
						<a class="navbar-item-expand change-mode"  @click="changeMode()">
		        	<i v-if="styleMode === 'day-mode'" class="fa fa-moon-o"></i>
		        	<i v-else class="fa fa-sun-o"></i>
		        </a>
						<div class="data-avator" @click="dropmenu()">
							<a class="navbar-item-expand expanded-avatar" v-bind:title="auth.user.nickname">
								<img :src="auth.user.avatar || defaultAvatar"/>
							</a>
						</div>
						<ul class="dropdown-menu pull-right" id="droptestMenu" v-clickoutside="'random'">
								<li class="dropdown-menu-item">
									<router-link :to="{ path: '/settings' }">
										<i class="fa fa-cog"></i>&nbsp;&nbsp;&nbsp;设置
									</router-link>
								</li>
								<li class="dropdown-menu-item">					
									<a href="javascript:;" class="expanded-logout" @click="logout()">
											<i class="fa fa-sign-out"></i>&nbsp;&nbsp;&nbsp;登出
									</a>  
								</li>
							</ul>
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
							<li v-if="auth.token && auth.user" class="navbar-item">
								<router-link   :to="{ path: '/creation' }"  title="编辑">
								编辑
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
  computed: {
    ...mapState({
      auth: state => state.auth,
			styleMode: state => state.globalVal.styleMode
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