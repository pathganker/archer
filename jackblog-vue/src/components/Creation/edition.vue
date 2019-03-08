<template>
  <div class="edition" id="editionNav">
    <div class="edition-menu">
      <div class="edition-list">
        <ul class="nav nav-pills nav-stacked" @click="edition()">
          <li ><a class="fa fa-plus">&nbsp;&nbsp;&nbsp;新建文集</a></li>
        </ul>
        <div id="editioninput" class="input-group medium" style="display:none" >
          <input type="text" name="newEdition" v-model="newEdition" class="form-control" placeholder="新建文集" aria-describedby="basic-addon1">
          <div class="btn-group">
            <button type="button" class="btn btn-success" @click="confirm()">确认</button>
          </div>
          <div class="btn-group">
            <button type="button" class="btn btn-info" @click="cancle()">取消</button>
          </div>
        </div>
        <ul class="nav nav-pills nav-stacked">
          <li v-for="(edition,index) in editionList" :key="index" @click="editionActive(index)" :class="{active:index==cured}" @dblclick="edconfig(index)">
            <a>{{edition.title}}  
              <span class="glyphicon glyphicon-cog " aria-hidden="true" @click="edconfig(index)" style="display:none;float:right;" :class="{active:index==cured}"></span>
              <ul class="dropdown-menu"  :id="'ed_drop_menu_'+index" style="display:none;user-select:none;" v-clickoutside="index">
                <li><a @click="edModify(edition)">修改</a></li>
                <li><a @click="edDelete(edition)">删除</a></li>
              </ul>
            </a>
          </li>
          <li v-if="editionList.length <1">正在大力回车...</li>
        </ul>
      </div>
      <div class="horizon-bar"></div>
      <div class="title-list">
        <ul class="nav nav-pills nav-stacked">
          <li><a class="fa fa-plus" @click="createblog()">&nbsp;&nbsp;&nbsp;新建文章</a></li>
        </ul>
        <ul v-if="editionList[cured]!=null" class="nav nav-pills nav-stacked">
          <li  v-for="(article,index) in editionList[cured].articles" :key="index" @click="articleActive(index,article.id)" :class="{active:index==curar}" @dblclick="arconfig(index)">
            <a>{{article.title}}
              <span class="glyphicon glyphicon-cog" aria-hidden="true" style="display:none;float:right" @click="arconfig(index)" :class="{active:index==curar}"></span>
                <ul class="dropdown-menu"  :id="'ar_drop_menu_'+index" style="display:none;user-select:none;" v-clickoutside="index">
                <li><a @click="arPublish(article.id)">发布文章</a></li>
                <li><a @click="arModifyTitle()">修改标题</a></li>
                <li><a @click="arMove(article)">移动</a></li>
                <li><a @click="arDelete(article)">删除</a></li>
              </ul>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState,mapActions } from 'vuex'
import store from '../../store'
import {
  CURRENT_ARTICLE,
  CURRENT_EDITION,
  ADD_ARTICLE,
  SAVE_ARTICLE_DRAFT,
  ADD_EDITION
} from '../../store/types'
import {formatDate, uuid} from '../../utils/stringUtils'
export default {
  props:['editionList','cured','curar'],
  methods:{
    ...mapActions([
      'getBackendArticle',
      'addBackendArticle',
      'addEdition',
      'updateEdition',
      'updateBackendArticle'
    ]),
    editionActive(num){
      store.commit(CURRENT_EDITION,{cured:num})
      store.commit(CURRENT_ARTICLE,{curar:0})
    },
    articleActive(num, id){
      store.commit(CURRENT_ARTICLE,{curar:num})

    },
    newActive(num){
      store.commit(CURRENT_ARTICLE,{curar:num})
    },
    edition(){
      document.getElementById('editioninput').style.display="block"
    },
    confirm(){
      this.$parent.handleAddEdition(this.newEdition)
      document.getElementById('editioninput').style.display="none"
    },
    cancle(){
      document.getElementById('editioninput').style.display="none"
    },
    createblog(){
      store.commit(CURRENT_ARTICLE, {curar:0})
      this.$parent.handleCreateBlog(this.articleList[this.cured].id)
    },
    edconfig(num){
      const dropmenu = document.getElementById('ed_drop_menu_'+num)
      const isshow = (dropmenu.style == null || dropmenu.style.display=="none") ? "block":"none"
      dropmenu.style.display=isshow
    },
    arconfig(num){
      const dropmenu = document.getElementById('ar_drop_menu_'+num)
      const isshow = (dropmenu.style == null || dropmenu.style.display=="none") ? "block":"none"
      dropmenu.style.display=isshow 
    },
    edModify(){

    },
    edDelete(){

    },
    arModifyTitle(){
      const titleinput = document.getElementById('title-input')
      titleinput.focus()
      titleinput.click()
      titleinput.select()
    },
    arPublish(id){
      this.$parent.openConfirmModal()
      this.$parent.newblog={
        id: id,
        publish: true,
        publishTime: new Date()
      }
    },
    arMove(){

    },
    arDelete(){

    }
  },
  directives:{
    //菜单外点击事件
    clickoutside:{
      bind:function(el,binding,vnode){
        function  hidemenu(el){
          el.style.display="none" 
        }
        function documentHandler(e){
          if(el.contains(e.target) || el.contains(e.target.nextElementSibling)){
            return true
          }else if (el.style.display!='none'){
            hidemenu(el)
          }
        }
        el._vueClickOutside_ = documentHandler
        document.addEventListener('click',documentHandler);
      },
      unbind:function(el,binding){
          document.removeEventListener('click',el._vueClickOutside_);
          delete el._vueClickOutside_;
      }
    }
  },
  data(){
    return {
      newEdition:'',
    }
  }
}
</script>
