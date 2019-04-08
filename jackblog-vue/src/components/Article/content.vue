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
					<div class="list-image" >
						<img :src="articleDetail.image"  style="margin:10px auto;padding:3px;border: 1px solid #ddd;"/>
					</div>
          <div class="markdown-body detail-content" v-html="articleDetail.frontContent"></div>
				</div>
      </Card>
  </div>
</template>
<script>
import { Card } from 'iview'
import 'mavon-editor/dist/css/index.css'
import 'mavon-editor/dist/markdown/github-markdown.min.css'
import 'mavon-editor/dist/highlightjs/styles/vs2015.min.css'
import 'mavon-editor/dist/highlightjs/languages/r.min.js'
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
.list-image {
  text-align: center
}
.list-image img{
  width: 80%;
  max-width: 777px;
  min-width: 364px;
  display: inline-block;
}
.detail-content{
  width: 80%;
  margin: 20px auto;
}
@media (max-width: 600px){
  .markdown-body{
  width: 100%;
  margin: 20px 0;
  padding: 0 5px;
}
}
</style>
