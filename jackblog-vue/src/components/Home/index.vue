<template>
<div>
    <div class="container-fluid main-box">
      <div class="row">
        <!-- <Sidebar :index-img="indexImg"></Sidebar> -->
        <div class="col-sm-12  main-content">
          <Tags :tag-list="tagList" :options="options" :is-fetching="isFetching"></Tags>
          <Articles :article-list="articleList"></Articles>
          <Loadmore v-if="options.page<pageCount" :options="options" :is-more="isMore" :is-fetching="isFetching"></Loadmore>
        </div>
      </div>
    </div>
    <Footerbar></Footerbar>
    </div>
</template>
<script>
import Sidebar from './sidebar.vue'
import Tags from './tags.vue'
import Articles from './articles.vue'
import Loadmore from './loadmore.vue'
import Footerbar from './footer.vue'
import { mapState,mapActions } from 'vuex'

export default {
  components:{ Sidebar,Footerbar,Tags,Articles,Loadmore },
  computed: {
    ...mapState({
      indexImg: ({globalVal}) => globalVal.indexImg,
      tagList: ({tagList}) => tagList.items,
      options: ({options}) => options.item,
      articleList: ({articleList}) => articleList.items,
      isMore: ({articleList}) => articleList.isMore,
      isFetching: ({articleList}) => articleList.isFetching,
      pageCount: ({articleList,options}) => options.item && articleList.totalCount%options.item.pageSize!=0?parseInt(articleList.totalCount/options.item.pageSize)+1:parseInt(articleList.totalCount/options.item.pageSize)
    })
  },  
  created(){
    // if(this.indexImg === ''){
    //   this.getIndexImage()
    // }
    // if(this.tagList.length < 1){
    //   this.getTagList()
    // }
    this.getArticleList({options:this.options})
    this.getArticleTotal({options:this.options})
  },
  methods:{
    ...mapActions([
      'getIndexImage',
      'getTagList',
      'changeOptions',
      'getArticleList',
      'getArticleTotal'
    ]),    
    handleChange(options,isAdd=false){
      this.changeOptions(options)
      this.getArticleList({options:this.options,isAdd:isAdd})
    }
  }
}
</script>