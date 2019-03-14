<template>
  <div class="edition" id="editionNav">
      <Split v-model="splitper" mode="vertical" min=238>
        <div slot="top" class="demo-split-pane">
          <div class="edition-list">
            <ul class="nav nav-pills nav-stacked">
              <li class="navbar-item-menu"> 
								<router-link :to="{ path: '/' }" title="首页" class="item-menu"><span class="glyphicon glyphicon-home" aria-hidden="true"></span><br>
								首页
								</router-link>
								<router-link :to="{ path: '/edition' }"  title="分类" class="item-menu"><span class="glyphicon glyphicon-th" aria-hidden="true"></span><br>
								分类
								</router-link>
								<router-link :to="{ path: '/tag' }" title="标签" class="item-menu"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span><br>
								标签
								</router-link>
                <a href="javascript:;" class="item-menu" @click="logout()"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span><br>
								 登出
						  	</a>  
							</li>       
              <li  @click="edition()" ><a class="fa fa-plus">&nbsp;&nbsp;&nbsp;新建文集</a></li>
              <div id="editioninput" class="input-group medium" style="display:none" v-clickoutside="1">
                <div class="panel-body">
                  <input type="text" name="newEdition" v-model="newEdition" v-validate="'required'" class="form-control" placeholder="新建文集" data-vv-as="文集名">
                  <span class="tip-span">{{ errors.first('newEdition') }}</span>
                </div>
                <div class="panel-footer">
                  <button type="button" class="btn btn-success" @click="confirm()">确认</button>
                  <button type="button" class="btn btn-info" @click="cancel()">取消</button>
                </div>
              </div>
            </ul>
            <ul class="nav nav-pills nav-stacked">
              <li v-for="(edition,index) in editionList" :key="index" @click="editionActive(edition,index)" :class="{active:index==cured}" @dblclick="edconfig(index)">
                <a>{{edition.title}}  
                  <span class="glyphicon glyphicon-cog " aria-hidden="true" @click="edconfig(index)" style="display:none;float:right;" :class="{active:index==cured}"></span>
                  <ul class="dropdown-menu"  :id="'ed_drop_menu_'+index" style="display:none;user-select:none;" v-clickoutside="index">
                    <li><a @click="edModify(edition)"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&nbsp;&nbsp;修改名称</a></li>
                    <li><a @click="edDelete(edition)"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;删除文集</a></li>
                  </ul>
                </a>
              </li>
              <li v-if="editionList.length <1">正在大力回车...</li>
            </ul>
          </div>
        </div>
        <div slot="bottom" class="demo-split-pane">
          <div class="title-list">
            <ul class="nav nav-pills nav-stacked">
              <li><a class="fa fa-plus" @click="createblog()">&nbsp;&nbsp;&nbsp;新建文章</a></li>
            </ul>
            <ul v-if="editionList[cured]!=null" class="nav nav-pills nav-stacked">
              <li  v-for="(article,index) in editionList[cured].articles" :key="index" @click="articleActive(index,article.id)" :class="{active:index==curar}" @dblclick="arconfig(index)">
                <a>{{article.title}}
                  <span class="glyphicon glyphicon-cog" aria-hidden="true" style="display:none;float:right" @click="arconfig(index)" :class="{active:index==curar}"></span>
                    <ul class="dropdown-menu"  :id="'ar_drop_menu_'+index" style="user-select:none;" v-clickoutside="index">
                    <li><a v-if="!article.publish" @click="arPublish(article.id,index)"><span class="glyphicon glyphicon-share" aria-hidden="true"></span>&nbsp;&nbsp;发布文章</a>
                        <a v-else @click="arHide(article.id,index)"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span>&nbsp;&nbsp;取消发布</a></li>
                    <li><a @click="arModifyTitle(index)"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;&nbsp;修改标题</a></li>
                    <li><a @click="arMove(index)"><span class="glyphicon glyphicon-triangle-left" aria-hidden="true"></span>&nbsp;&nbsp;移动</a>
                    <ul class="dropdown-menu second" :id="'ar_drop_menu_sec_'+index" v-clickoutside="index">
                      <li v-for="(edition,key) in editionList" :key="key" @click="moveArticle(article.id, edition.id, index)"><a class="ar-title" v-if="key!=cured"><span class="glyphicon glyphicon-book" aria-hidden="true"></span>&nbsp;&nbsp;{{edition.title}}</a></li>
                    </ul>
                    </li>
                    <li>
                      <a  class="upload-button">
                        <input type="file"  class="file-input" ref="picInput" @input="addCover(article.id)" accept="image/gif,image/jpeg,image/jpg,image/png"/>
                        <span class="glyphicon glyphicon-picture" aria-hidden="true"></span>&nbsp;&nbsp;添加/修改封面</a>
                    </li>
                    <li><a @click="arDelete(article.id,index)"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;删除</a></li>
                  </ul>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </Split>

    <Modal v-model="cancelPublish" 
      width="400" 
      title="取消发布"
      @on-ok="hideConfirm"
      @on-cancel="hidecancel">
      <p>其他人将看不到这篇博客</p>
	  </Modal>

    <Modal v-model="showPublish" 
      width="400" 
      title="发布文章"
      @on-ok="publishConfirm"
      @on-cancel="publishCancel">
      <p>其他人将可以看到这篇博客</p>
    </Modal>

    <Modal v-model="showDelete"  width="400" 
      title="删除"
      @on-ok="deleteConfirm"
      @on-cancel="deleteCancel">
      <p>这篇博客将要人间蒸发</p>
    </Modal>
  </div>
</template>
<script>
import { Split, Modal, Upload } from 'iview'
import { mapState,mapActions } from 'vuex'
import store from '../../store'
import {
  CURRENT_ARTICLE,
  CURRENT_EDITION,
  SAVE_ARTICLE_DRAFT,
  ADD_EDITION,
  MOVE_ARTICLE
} from '../../store/types'
import {formatDate, uuid} from '../../utils/stringUtils'
export default {
  props:['editionList','cured','curar'],
  components:{
    Split,Modal,Upload
  },
  methods:{
    ...mapActions([
      'getBackendArticle',
      'logout',
      'uploadCover'
    ]),
    editionActive(edition,num){
      store.commit(SAVE_ARTICLE_DRAFT,{cured:num,curar:0})
      if(num!= this.cured && this.$parent.$parent.isedit ){
        this.$parent.$parent.openSaveModal()
        return
      }
      this.newEdition=edition.title
      this.editionId=edition.id
      store.commit(CURRENT_EDITION,{cured:num})
      store.commit(CURRENT_ARTICLE,{curar:0})
    },
    articleActive(num, id){
      store.commit(SAVE_ARTICLE_DRAFT,{cured:this.cured,curar:num})
      if(num!= this.curar && this.$parent.$parent.isedit){
        this.$parent.$parent.openSaveModal()
        return
      }
      store.commit(CURRENT_ARTICLE,{curar:num})
    },
    newActive(num){
      store.commit(CURRENT_ARTICLE,{curar:num})
    },
    edition(){
      this.editionId=null
      this.newEdition='新建文件夹'+(1+this.editionList.length)
      document.getElementById('editioninput').style.display="block"
    },
    confirm(){
      this.$validator.validateAll().then(valid =>{
        if(valid){
          if(this.editionId!=null){
            this.$parent.$parent.handleUpdateEdition(this.editionId, this.newEdition)
          }else{
            this.$parent.$parent.handleAddEdition(this.newEdition)
          }
          document.getElementById('editioninput').style.display="none"
        }
      })
    },
    cancel(){
      document.getElementById('editioninput').style.display="none"
    },
    createblog(){
      store.commit(CURRENT_ARTICLE, {curar:0})
      if(null ==this.cured){
        if(null !=this.editionList[0]){
          store.commit(CURRENT_EDITION,{cured:0})
          this.$parent.$parent.handleCreateBlog(this.editionList[0].id)
        }else{
          this.$parent.$parent.handleUpdateEdition('新建文件夹').then(this.createblog())
        }
      }else{
        this.$parent.$parent.handleCreateBlog(this.editionList[this.cured].id)
      }
      
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
    edModify(edition){
      this.newEdition=edition.title
      this.editionId=edition.id
      document.getElementById('editioninput').style.display="block"
    },
    edDelete(){

    },
    arModifyTitle(index){
      this.$parent.$parent.isedit = true
      const titleinput = document.getElementById('title-input')
      titleinput.focus()
      titleinput.click()
      titleinput.select()
      document.getElementById('ar_drop_menu_'+index).style.display='none'
    },
    arPublish(id,index){
      document.getElementById('ar_drop_menu_'+index).style.display='none'
      this.showPublish=true
      this.newblog={
        id: id,
        publish: true,
        publishTime: new Date()
      }
    },
    publishConfirm(){
      store.commit(CURRENT_EDITION,{cured:this.cured})
      store.commit(CURRENT_ARTICLE,{curar:this.curar})
      this.$parent.$parent.handleUpdateBlog(this.newblog)
      this.showPublish=false
    },
    publishCancel(){
      this.showPublish=false
    },
    arHide(id,index){
      document.getElementById('ar_drop_menu_'+index).style.display='none'
      this.cancelPublish=true
      this.newblog={
        id: id,
        publish: false,
      }
    },
    hideConfirm(){
      store.commit(CURRENT_EDITION,{cured:this.cured})
      store.commit(CURRENT_ARTICLE,{curar:this.curar})
      this.$parent.$parent.handleUpdateBlog(this.newblog)
      this.cancelPublish=false
    },
    hidecancel(){
      this.cancelPublish=false
    },
    arMove(index){
      document.getElementById('ar_drop_menu_sec_'+index).style.display='block'
    },
    arDelete(id, index){
      this.showDelete=true
      this.deleteId=id
      this.dialog=index
    },
    deleteConfirm(id,index){
      store.commit(CURRENT_EDITION,{cured:this.cured})
      store.commit(CURRENT_ARTICLE,{curar:this.curar})
      document.getElementById('ar_drop_menu_'+this.dialog).style.display='none'
      this.$parent.$parent.hadleDeleteBlog(this.deleteId)
      this.showDelete=false
    },
    deleteCancel(){
      document.getElementById('ar_drop_menu_'+this.dialog).style.display='none'
      this.showDelete=false
    },
    moveArticle(aid,eid,index){
      this.newblog={
        id: aid,
        edition: eid
      }
      store.commit(CURRENT_EDITION,{cured:this.cured})
      store.commit(CURRENT_ARTICLE,{curar:this.curar})
      store.commit(MOVE_ARTICLE,this.newblog)
      this.$parent.$parent.handleUpdateBlog(this.newblog)
      document.getElementById('ar_drop_menu_'+index).style.display='none'
      document.getElementById('ar_drop_menu_sec_'+index).style.display='none'

    },
    addCover(id){
      let file = this.$refs.picInput.files[0]
      console.log(file)
      let data = new FormData()
      data.append('picture', file)
      if(file) {
        this.uploadCover({
          picture: data,
          id: id
        })
      }
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
          if(el.contains(e.target) || el.contains(e.target.nextElementSibling)|| el.contains(e.target.parentElement.nextElementSibling)){
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
      editionId:'',
      newblog: '',
      cancelPublish: false,
      showPublish: false,
      showDelete: false,
      deleteId:'',
      dialog: '',
      splitper: 0.5
    }
  }
}
</script>
<style>
.ar-title{
  display:block;
  white-space:nowrap;
  overflow:hidden;
  text-overflow:ellipsis;
}
.demo-split-pane {
  height: 100%;
  width: 100%;
  overflow-y: auto;
}
.navbar-item-menu{
  text-align: center;
}
.navbar-item-menu .item-menu{
  display: inline-block;
}
.panel-footer{
  background: 0 0;
  border-top: none;
}
.panel-body{
  background: 0 0;
}
.upload-button{
  display: block!important;
  color: black!important;
}
.upload-button :hover{
  display: block!important;
  color: black!important;
}
.file-input{
  opacity: 0;
  position:absolute;
  width:100%;
  left: 0;
}
</style>
