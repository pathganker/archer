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
  </div>
</template>
<script>
import store from 'store'
import Navbar from './Navbar'
import Toaster from './Toaster'
import CanvasStar from './Effects'
export default {
  store,
  components:{ Navbar,Toaster, CanvasStar},
  methods:{
    handleMove(e){
      this.$refs.star.drawMove(e)
    },
    hideNavbar(){
      this.$refs.nav.hideNav()
    }
  },
  watch:{
    $route(to, from){
      if(from.path == '/creation'){
        this.$refs.nav.showNav()
      }
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