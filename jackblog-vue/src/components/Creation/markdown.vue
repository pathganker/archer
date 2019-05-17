<template>
  <div class="markdown-container" id="markdownContent">
    <div class="edit-body" v-if="article!=null">
      <input id="title-input" class="intro-head " name="title" type="text" :value="article.title" @input="title = $event.target.value" autocomplete="false" />
      <mavon-editor  ref="md" :value="article.backendContent == null ? '': article.backendContent" @input="edit" @save="save()" 
        :toolbars="toolbars" :externalLink="externalLink" @imgAdd="$imgAdd" :ishljs="true"/>
    </div>
    <div v-else>
            <mavon-editor  ref="md" :value="''" @input="edit" @save="save" :editable="editable"
        :toolbars="toolbars" :externalLink="externalLink" @imgAdd="$imgAdd" :ishljs="true"/>
    </div>
  </div>
</template>

<script>
import  { mavonEditor } from 'mavon-editor'
import { mapState,mapActions } from 'vuex'
import 'mavon-editor/dist/css/index.css'
import 'mavon-editor/dist/markdown/github-markdown.min.css'
import 'mavon-editor/dist/highlightjs/styles/vs2015.min.css'
import 'mavon-editor/dist/highlightjs/languages/r.min.js'
export default {
  props:['article','isedit'],
  components: { mavonEditor },
  methods: {
    ...mapActions([
      'uploadContentPic'
    ]),
    save(){
      let backendContent = this.content
      let frontContent = this.$refs.md.markdownIt.render(backendContent)
      console.log(this.article.id)
      let blog ={
        id: this.article.id,
        title: this.title == null ? this.article.title : this.title,
        edition: this.article.edition,
        backendContent: backendContent,
        frontContent: frontContent,
        modifyTime: new Date(),
      }
      this.$parent.$parent.handleUpdateBlog(blog)
    },
    edit(e){
      this.content = e
      if(this.content!='' && this.content != this.article.backendContent){
        if(!this.isedit){
          this.$parent.$parent.editActive()
        }
      }
      if(this.content == this.article.backendContent){
        if(this.isedit){
          this.$parent.$parent.editCancel()
        }
      }
    },
    $imgAdd(pos, $file){
      var params = new FormData()
      params.append('picture', $file)
      this.uploadContentPic({
        picture: params,
        id: this.article.id
      }).then((url) => {
        setTimeout(
        this.$refs.md.$img2Url(pos, url),100)
      })
    }
  },
  data() {
    return {
      // externalLink: {
      //   markdown_css: function() {
      //       // 这是你的markdown css文件路径
      //       return '/markdown/github-markdown.min.css';
      //   },
      //   hljs_js: function() {
      //       // 这是你的hljs文件路径
      //       return '/highlightjs/highlight.min.js';
      //   },
      //   hljs_css: function(css) {
      //       // 这是你的代码高亮配色文件路径
      //       return '/highlightjs/styles/' + css + '.min.css';
      //   },
      //   hljs_lang: function(lang) {
      //       // 这是你的代码高亮语言解析路径
      //       return '/highlightjs/languages/' + lang + '.min.js';
      //   },
      //   katex_css: function() {
      //       // 这是你的katex配色方案路径路径
      //       return '/katex/katex.min.css';
      //   },
      //   katex_js: function() {
      //       // 这是你的katex.js路径
      //       return '/katex/katex.min.js';
      //   },
      // },
      externalLink: false,
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: false, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: true, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: false, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: false, // 单双栏模式
        preview: true, // 预览
        externalLink: true
      },
      content:'',
      title: null,
      editable: false,
    }
  }
}
</script>

<style scoped>
.intro-head{
  text-align: left;
  font-size: 20px;
  height: 40px;
  width: 100%;
  border: none;
  outline: none;
  padding: 5px 0px 5px 20px;
  display: inline-block;
  background-color: #C7EDCC;
  }
.v-note-wrapper {
  background-color: #C7EDCC!important;
}
.markdown-body pre{
  letter-spacing: 1.5px!important;
  line-height: 1.7!important;
}
</style>


