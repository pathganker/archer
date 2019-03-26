<template>
  <div v-show="isShowTop"  >
    <div class="gotop"  @click.prevent="gotop">
      <i class="fa fa-arrow-up"></i>
    </div>
    <div class="gobottom" @click.prevent="gobottom">
      <i class="fa fa-arrow-down"></i>
    </div>
  </div>
</template>
<script>
export default {
  data(){
    return {
      isShowTop:false
    }
  },
  mounted(){
    const scroll = document.getElementsByClassName("second-box")
    scroll[0].addEventListener('scroll', this.handleScroll)
  },
  methods:{
    handleScroll(){
      const scroll = document.getElementsByClassName("second-box")
      if (scroll[0].scrollTop > 286) {
        this.isShowTop = true
      } else {
        this.isShowTop = false
      }
    },
    gotop(){
      const scroll = document.getElementsByClassName("second-box")
      scroll[0].scrollTo(0,0)
    },
    gobottom(){
      const scroll = document.getElementsByClassName("second-box")
      scroll[0].scrollTo(0,scroll[0].scrollHeight)
    }
  }
}
</script>
<style>
.gobottom{
  position: fixed;
  right: 2%;
  bottom: 5px;
  width: 40px;
  height: 40px;
  background: rgba(0,0,0,.5);
  text-align: center;
  cursor: pointer;
  -moz-transition: all .1s;
  -o-transition: all .1s;
  -webkit-transition: all .1s;
  transition: all .1s;
}
.gobottom i{
  font-size: 2em;
  color: #fff;
  margin-top: 13px;
}
.gotop{
  bottom: 45px;
}
</style>
