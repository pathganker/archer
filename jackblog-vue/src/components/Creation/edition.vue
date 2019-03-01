<template>
  <div class="edition" id="editionNav">
    <div class="edition-menu">
      <div class="edition-list">
        <ul class="nav nav-pills nav-stacked" @click="edition()">
          <li ><a class="fa fa-plus">&nbsp;&nbsp;&nbsp;新建文集</a></li>
        </ul>
        <div id="editioninput" class="input-group medium" style="display:none" >
          <input type="text" class="form-control" placeholder="新建文集" aria-describedby="basic-addon1">
          <div class="btn-group">
            <button type="button" class="btn btn-success" @click="confirm()">确认</button>
          </div>
          <div class="btn-group">
            <button type="button" class="btn btn-info" @click="cancle()">取消</button>
          </div>
        </div>
        <ul class="nav nav-pills nav-stacked">
          <li v-for="(edition,index) in editionList" :key="index" @click="editionActive(index)" :class="{active:index==ed_ins}">
            <a>{{edition.title}}</a>
          </li>
          <li v-if="editionList.length <1">正在大力回车...</li>
        </ul>
      </div>
      <div class="horizon-bar"></div>
      <div class="title-list">
        <ul class="nav nav-pills nav-stacked">
          <li ><a class="fa fa-plus">&nbsp;&nbsp;&nbsp;新建文章</a></li>
        </ul>
        <ul class="nav nav-pills nav-stacked">
          <li v-for="(article,index) in articles" :key="index" @click="articleActive(index,article.id)" :class="{active:index==ar_ins}">
            <a>{{article.title}}</a>
          </li>
        </ul>
      </div>          
      </div>
    </div>
  </div>
</template>
<script>
import { mapState,mapActions } from 'vuex'
export default {
  props:['editionList'],
  data(){
    return {
      ed_ins:0,
      ar_ins:0,
      articles: ''
    }
  },
  created(){
    if(this.$parent.editionList[0]){
      this.articles=this.$parent.editionList[0].articles
    }
    
  },
  methods:{
    ...mapActions([
      'getBackendArticle'
    ]),
    editionActive(num){
      this.ed_ins=num
      this.articles=this.$parent.editionList[num].articles
    },
    articleActive(num, id){
      this.ar_ins=num
      this.getBackendArticle(id)
    },
    edition(){
      document.getElementById('editioninput').style.display="block"
    },
    confirm(){
      document.getElementById('editioninput').style.display="none"
    },
    cancle(){
      document.getElementById('editioninput').style.display="none"
    }
  }
  
}
</script>
