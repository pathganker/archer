<template>
    <div class="article-list-container">
        <div class="article-list-sort">
            <div class="title-sort">
                <h4> {{title}}</h4>
            </div>
            <div v-for="(article,index) in articles" :key="index" class="article-sort">
                <Card :bordered="false" style="border-radius:0;">
                   <li class="article-sort-header"> 
                        <router-link :to="{  name: 'article', params: {aid: article.id } }" class="link-title-article">{{article.title}}
                        </router-link>
                   </li>
                </Card>
            </div>
        </div>
    </div>
</template>
<script>
import {Card} from 'iview'
import { mapState,mapActions } from 'vuex'
export default {
    components:{Card},
    computed:{
        ...mapState({
        articles: ({category}) => category.articles,
        title: ({category}) => category.name,
        })
    },
    methods:{
        ...mapActions([
        'getCategoryArticles'
        ]),
        initData(){
            const eid = this.$route.params.eid
            this.getCategoryArticles(eid)
        }
    },
    watch: {
        '$route': 'initData'
    },
    created () {
        this.initData()
    },
}
</script>
<style>

.article-list-container{
    width: 40%;
    margin: 0 auto;
}
.article-list-sort{
    margin-left:50px;
    border-left:2px solid #ccc;
}
.title-sort{
    margin: 30px 0;
}
.title-sort h4{
    margin-left: 20px;
    color: #ccc;
}
.article-sort{
    margin: 30px 0 30px 2px;
    padding: 30px 0;
}
.article-sort-header{
    margin: 20px 0;
    border-bottom: 1px dashed #ccc;
    font-size: 16px;
}
.link-title-article{
    color: black!important;
    font-size: 18px;
}
</style>

