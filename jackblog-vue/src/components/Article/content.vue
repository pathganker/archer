<template>
  <div class="article-container">
      <Card shadow>
				<header class="content-title" slot="title">
          <h1 class="title">{{ articleDetail.title }}</h1>
          <div class="counts">
            <span class="views-count">阅读{{articleDetail.visitCount}}</span>
            <span class="comments-count">评论{{articleDetail.commentCount}}</span>
            <span class="likes-count">喜欢{{articleDetail.likeCount}}</span>
          </div> 
				</header>
				<div class="list-body" style="margin:10px 0px">
					<div class="list-image" style="display:none">
						<img src="../../assets/images/nopicky.jpg"  style="margin:10px 0;padding:3px;border: 1px solid #ddd;"/>
					</div>
          <div class="markdown-content" v-html="articleDetail.frontContent"></div>
				</div>
      </Card>
  </div>
</template>
<script>
import { Card } from 'iview'
export default {
  props:['articleDetail'],
  components:{Card}
}
</script>
<style>
.content-title{
  text-align: center;
}
.article-box{
  padding: 0;
  margin: 0 auto 0 auto;
  width:50%;
  
}
</style>
