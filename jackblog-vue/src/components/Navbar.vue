<template>
		<div class="navbar-box navbar-skin">
		  <div class="navbar-menu">
					<div class="navbar-shrink">
						<div v-if="auth.token && auth.user" class="pull-right">
							<a href="javascript:;" class="shrink-avatar" @click="dropdown()">
								<img :src="auth.user.avatar || defaultAvatar"></img>
							</a>							
							<ul class="dropdown-menu pull-right" id="dropdownMenu">
								<li>
									<router-link :to="{ path: '/settings' }">
										<i class="fa fa-cog"></i> 
									</router-link>
								</li>
								<li role="separator" class="divider"></li>
								<li>					
									<a href="javascript:;" class="shrink-logout" @click="logout()">
											<i class="fa fa-sign-out"></i> 
									</a>  
								</li>
							</ul>
						</div>
						<div v-else class="pull-left">
							<router-link :to="{ path: '/login' }" class="shrink-login" title="登录">
								<i class="fa fa-sign-in"> </i>
							</router-link>
						</div>
						<br>
						<div class="pull-left">
							<a  class="shrink-login change-mode" href="javascript:;" @click="changeMode()">
								<i v-if="styleMode === 'day-mode'" class="fa fa-moon-o"></i>
								<i v-else class="fa fa-sun-o"></i>
							</a>
						</div>
					</div>
      		<router-link :to="{ path: '/' }" class="navtitle " :class="{'active':$route.name !== 'apps'}" title="首页">
						青团子
					</router-link>
      		<!-- <router-link :to="{ path: '/apps',activeClass: 'active'}" class="navbar-item mobile" active-class="active" title="移动应用">
						<i class="fa fa-mobile"></i>
					</router-link> -->
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
	      	<div v-else class="navbar-items">
						<a class="navbar-item-expand change-mode" href="javascript:;" @click="changeMode()">
		        	<i v-if="styleMode === 'day-mode'" class="fa fa-moon-o"></i>
		        	<i v-else class="fa fa-sun-o"></i>
		        </a>
						<router-link :to="{ path: '/login',activeClass:'active' }" class="navbar-item-expand" title="登录">
							<i class="fa fa-sign-in"></i>
						</router-link>   
	      	</div>
					<div class="navbar-items">
						<router-link :to="{ path: '/' }" class="navbar-item" title="首页">
						首页
						</router-link>
						<router-link :to="{ path: '/edition' }" class="navbar-item " title="设置">
						分类
						</router-link>
						<router-link  v-if="auth.token && auth.user" :to="{ path: '/creation' }" class="navbar-item" title="创作">
						创作
						</router-link>
					</div>
		  </div> 
		  <!-- <div class="navbar-expanded">
		      <div class="navbar-menu">
			      <a class="expanded-var angular2" href="//angular2.jackhu.top" title="angular2.x版">
			      </a>
			      <a class="expanded-var vue" href="//vue.jackhu.top" title="Vue版">
			      </a>
			      <a class="expanded-var react" href="//react.jackhu.top" title="React版">
			      </a>
			      <a class="expanded-var angular1" href="//angular.jackhu.top" title="Angular1.x版">
			      </a>
		        <a class="navbar-item change-mode" href="javascript:;" @click="changeMode()">
		        	<i v-if="styleMode === 'day-mode'" class="fa fa-moon-o"></i>
		        	<i v-else class="fa fa-sun-o"></i>
		        </a>
		      </div>
		  </div> -->
		</div>
</template>

<script>
import defaultAvatar from '../assets/images/avatar.png'
import { mapState,mapActions } from 'vuex'

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
    if(this.auth.token){
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
					console.log(e)
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