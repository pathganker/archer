<template>
	<ul class="article-list list-unstyled clearfix">
		<li class="article-item" v-for="(article,index) in articleList"  :key="index" :class="article.images.length > 0 ? 'have-img' : ''">
			<router-link v-if="article.images.length > 0" :to="{ name: 'article', params: {aid: article.id } }" class="wrap-img">
				<img :src="article.images[0].url"/>
			</router-link>
			<div>
				<p class="list-top">               
				<span class="time">{{ article.publishTime | customTime }}</span>
				</p>
				<h4 class="title">
					<router-link :to="{ name: 'article', params: {aid: article.id } }" class="link-title">
						{{article.title}}
					</router-link>
				</h4>
				<div class="list-footer">
					<span>阅读 {{article.visitCount}}</span>
					<span> · 评论 {{article.commentCount}}</span>        
					<span> · 喜欢 {{article.likeCount}}</span>
				</div>
			</div>
		</li>
		<li v-if="articleList.length < 1" class="no-content">正在大力回车...</li>
	</ul>
</template>

<script>
export default {
  props: ['articleList']
}
</script>