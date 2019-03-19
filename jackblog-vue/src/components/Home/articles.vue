<template>
	<div class="article-list ">
		<div class="article-list-item" v-for="(article,index) in articleList"  :key="index">
			<Card shadow class="portal-article">
				<header class="title" slot="title">
					<router-link :to="{ name: 'article', params: {aid: article.id } }" class="link-title">
						{{article.title}}
					</router-link>
					<div class="list-info">
						<span>阅读 {{article.visitCount}}</span>
						<span> · 评论 {{article.commentCount}}</span>        
						<span> · 喜欢 {{article.likeCount}}</span>
						<span class="time"> · {{ article.publishTime | customTime }}</span>
					</div>
				</header>
				<div class="list-body" style="margin:10px 0px">
					<div class="list-image" >
						<router-link v-if="article.image !=null" :to="{ name: 'article', params: {aid: article.id } }" class="wrap-img" >
							<img :src="article.image"  style="margin:10px 0;padding:3px;border: 1px solid #ddd;"/>
						</router-link>
					</div>
					<div class="profile" style="margin:40px 0px;">
						<p>{{article.profile}}</p>
					</div>
				</div>
				<div class="list-footer" >
					<Button type="default" :to="{ name: 'article', params: {aid: article.id } }">阅读全文</Button>
				</div>
        	</Card>
		</div>
		<li v-if="articleList.length < 1" class="no-content">正在大力回车...</li>
	</div>
</template>

<script>
import { Card, Button } from 'iview'
export default {
  props: ['articleList'],
  components:{ Card, Button},
}
</script>
<style>
.article-list{
	position: relative;
	text-align: center;
	margin: 0;
}
 .article-list-item{
	display: block;
	position: relative;
	margin-left: auto; 
	margin-right: auto;
	min-width: 500px;
	max-width: 936px;
	border-bottom: none;
	padding:50px 50px;
}
.title{
	margin-top: 20px;
	
}
.profile p{
	font-family:Lato,'Microsoft YaHei',sans-serif;
	padding:0 20px;
	text-align: left;
}
.link-title{
	font-family: 'Hiragino Sans GB';
	font-size: 28px;
}
.list-info{
	font-family: 'Microsoft JhengHei';
	font-size: 14px;
	color: rgb(155, 155, 155);
	margin: 15px 0 5px 0;
}
.list-image img{
	width: 90%;
	max-width: 880px;
	min-width: 364px;
}
.list-footer{
	margin: 20px 0 30px 0 px;
}
.list-footer a{
	color: #2d8cf0;
}
.ivu-card{
	
}
.portal-article{
	position: relative;
	box-shadow: 0px 0px 6px 4px #939697;
}
.ivu-card-head {
	border-bottom:none;
}

</style>
