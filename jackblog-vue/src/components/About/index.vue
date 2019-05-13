<template>
  <div>
    <About :article-detail="about" class="about"></About>
  </div>
</template>

<script>
import About from '../Article/content'
import image from '../../assets/images/Katsuragi.jpg'
export default {
  components:{
    About
  },
  data(){
    return {
      about:{
        title: '关于青团子',
        visitCount: 'ω',
        commentCount: 'ω',
        likeCount: 'ω',
        frontContent: '<p>作为一名互联网初级民工，用户研究的从业者，里面会记录我学习到的研究方法，统计方法以及统计实现方法，以及可视化的实现方法，帮自己梳理再次加工知识。作为一个善变的每天内心活动满满的双子座，会偶尔写一些微小说，表达我最近的感悟。</p>',
        image: image
      },
    }
  }

}
</script>

<style>
.about{
  width: 700px;
  margin: 0 auto 0 auto;
}
</style>
