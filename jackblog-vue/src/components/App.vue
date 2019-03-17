<template>
  <div class="top-box" >
    <CanvasStar ref="star"></CanvasStar>
    <div class="second-box" @mousemove="handleMove">
      <Navbar ref="nav"></Navbar>
      <transition>
         <router-view class="enter"></router-view>
      </transition>
    </div>
    <Toaster></Toaster>
    <Modal 
      v-model="showSaveModal" class-name="vertical-center-modal"
      width="400"
      ok-text="离开"
      cancel-text="继续编辑"
      @on-ok="confirm"
      @on-cancel="cancel"
    >
      <p>编辑内容还未保存</p>
	  </Modal>
  </div>
</template>
<script>
import {
  CHANGE_SPLITER,
} from '../store/types'
import store from 'store'
import Navbar from './Navbar'
import Toaster from './Toaster'
import CanvasStar from './Effects'
import {Modal} from 'iview'
import { getCookie,saveCookie,removeCookie} from '../utils/cookies'
export default {
  components:{ Navbar,Toaster, CanvasStar, Modal },
  methods:{
    handleMove(e){
      this.$refs.star.drawMove(e)
    },
    hideNavbar(){
      this.$refs.nav.hideNav()
    },
    confirm(){
      removeCookie('isedit')
      this.showSaveModal = false
      this.$router.push(this.togo)
    },
    cancel(){
      this.showSaveModal = false
    },
    openSaveModal(){
      this.showSaveModal = true
    }
  },
  watch:{
    $route(to, from ){
      if(from.path == '/creation'){
        this.togo = to.path
        if(getCookie('isedit')){
          this.openSaveModal()
          this.$router.push(from.path)
          return
        }
        this.$refs.nav.showNav()
      }
    }
  },
  mounted(){
    window.onresize = () => {
      return (() => {
        this.$refs.star.handleWindowWidth()
        const splitper = 255/document.documentElement.clientWidth
        store.commit(CHANGE_SPLITER,{splitper:splitper})
      })()
    }
  },
  data(){
    return {
      showSaveModal: false,
      togo: ''
    }
  }
}
</script>
<style>
.v-enter{
  opacity:0;
  visibility: hidden;
  width: 0;
}
.v-enter-to{
  opacity:1;
  visibility: visible;
  width: 100%;
}
.v-enter-active{
  transition: all 0.2s ease;
}
.v-leave{
opacity:1;
visibility: hidden;
width: 100%;
}
.v-leave-to{
opacity:0;
width: 0;
visibility: hidden;
}
.v-leave-active{
transition: all 0.6s ease;
}
</style>