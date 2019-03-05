<template>
  <div class="top-box">
    <Navbar></Navbar>
    <transition>
      <router-view class="enter"></router-view>
    </transition>
    <Toaster></Toaster>
  </div>
</template>

<script>
import store from 'store'
import Navbar from './Navbar'
import Toaster from './Toaster'
export default {
  store,
  components:{ Navbar,Toaster }
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