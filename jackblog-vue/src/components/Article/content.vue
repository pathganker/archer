<template>
  <div class="article-container">
    <h1 class="title">{{ articleDetail.title }}</h1>
    <div class="counts">
      <span class="views-count">阅读{{articleDetail.visitCount}}</span>
      <span class="comments-count">评论{{articleDetail.commentCount}}</span>
      <span class="likes-count">喜欢{{articleDetail.likeCount}}</span>
    </div> 
    <div class="markdown-content" v-html="articleDetail.frontContent"></div>
  </div>
</template>
<script>
export default {
  props:['articleDetail']
}
</script>