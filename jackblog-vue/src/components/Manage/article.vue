<template>
    <div class="table-container">
        <Table :data="articles" :columns="tableColumns1" stripe class="article-table" 
        :height="tableHeight"  @on-sort-change="changeSort" :loading="loading" @on-selection-change="selectChange"></Table>
        <div class="table-page">
            <div style="float: left;">
                <Button type="success" @click="batPublish" :loading="publoading"> 批量发布</Button>
                <Button type="primary" @click="batRecall" :loading="recloading"> 批量取消发布</Button>
                <Button type="warning" @click="batDelete"> 批量删除</Button>
            </div>
            <div style="float: right;">
                <Page :total="total" :current="1" @on-change="changePage"></Page>
            </div>
        </div>
    <div class="image-preview" v-show="previewModel" @click="hide"> 
      <img :src="previewData" />
    </div>
    <Modal v-model="showDelete"  width="400" 
        title="删除"
        @on-ok="deleteConfirm"
        @on-cancel="deleteCancel">
        <p>这篇博客将要人间蒸发</p>
    </Modal>
    <Modal v-model="batDeleteModal"  width="400" 
        title="删除"
        :loading="delloading"
        @on-ok="batDeleteConfirm"
        @on-cancel="batDeleteCancel">
        <p>这些博客将要粉身碎骨</p>
    </Modal>
    </div>
</template>
<script>
import { Table, Page , Tag, Poptip, Button, Modal} from 'iview'
import { mapState,mapActions } from 'vuex'
import image from '../../assets/images/Katsuragi.jpg'
export default {
    components:{ Table, Page, Tag, Poptip,Button, Modal},
      computed: {
        ...mapState({
            articles: ({manage}) => manage.articles.items,
            total: ({manage}) => manage.articles.total,
            tableHeight: ({globalVal}) => globalVal.height && (globalVal.height-289-70-60-48)*0.9
        })
      },
    data () {
        return {
            previewModel: false,
            previewData: '',
            showDelete: false,
            batDeleteModal: false,
            deleteId: null,
            selectId:[],
            publoading: false,
            delloading: false,
            recloading: false,
            tableColumns1: [
                {
                    type: 'selection',
                    key:'id',
                    width: 60,
                    align: 'center'
                },
                {
                    title: '标题',
                    key: 'title',
                    render: (h,params)=>{
                        const row = params.row
                        return h('router-link',{
                            props:{
                                to: { name: 'article', params: {aid: row.id } },
                            },
                            attrs: {
                                style: "color:black"
                            }
                        },row.title)
                    }
                },
                {
                    title: '状态',
                    key: 'publish',
                    render: (h, params) => {
                        const row = params.row
                        const color = row.publish === true ? 'success' :  'primary'
                        const text = row.publish === true ? '已公开'  :  '未公开'
                        return h(Tag, {
                            props: {
                                type: 'dot',
                                color: color
                            }
                        }, text);
                    }
                },
                {
                    title: '封面',
                    key: 'image',
                    render: (h, params) =>{
                        const row = params.row
                        return h('img',{
                            attrs :{
                              src: row.preview
                            },
                            style: {
                                width: '40px',
                                height: '40px',
                                cursor:'pointer'
                            },
                            on: {
                                click: ()=> { 
                                    this.show(row.image)
                                }
                            }
                        })
                    }
                },
                {
                    title: '浏览次数',
                    key: 'visitCount',
                    sortable: 'custom',
                },
                {
                    title: '评论',
                    key: 'commentCount',
                    sortable: 'custom',
                },
                {
                    title: '喜欢',
                    key: 'likeCount',
                    sortable: 'custom',
                },
                {
                    title: '创建时间',
                    key: 'createTime',
                    sortable: 'custom',
                    render: (h, params) => {
                        const row = params.row
                        return h('div', this.formatDate(row.createTime));
                    }
                },
                {
                    title: '发布时间',
                    key: 'publishTime',
                    sortable: 'custom',
                    render: (h, params) => {
                        const row = params.row
                        return h('div', this.formatDate(row.publishTime));
                    }
                },
                {
                    title: '操作',
                    key: 'action',
                    width: 150,
                    align: 'center',
                    render: (h, params) => {
                        return h('div', [
                            h(Button, {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: () => {
                                        this.$router.push("/creation"+"/"+params.row.id)
                                    }
                                }
                            }, '编辑'),
                            h(Button, {
                                props: {
                                    type: 'error',
                                    size: 'small'
                                },
                                on: {
                                    click: () => {
                                        this.remove(params.row.id)
                                    }
                                }
                            }, '删除')
                        ])
                    }
                }
            ],
            query: {
                page: 0,
                pageSize: 10,
                tag:null,
                sortName: null,
                sortType: null,
            },
            loading: false
        }
    },
    created(){
        this.getData()
    },
    methods: {
        ...mapActions([
            'getArticleMana',
            'deleteBackendArticle',
            'batDeleteArticle',
            'batPublishArticle',
            'batRecallArticle'
            ]),  
        formatDate (date) {
            const newdate = new Date(date)
            if(null == date || typeof(newdate) == "undefined"){
                return
            }
            const y = newdate.getFullYear();
            let m = newdate.getMonth() + 1;
            m = m < 10 ? '0' + m : m;
            let d = newdate.getDate();
            d = d < 10 ? ('0' + d) : d;
            return y + '-' + m + '-' + d;
        },
        changePage (e) {
            // The simulated data is changed directly here, and the actual usage scenario should fetch the data from the server
            this.query.page=e
            this.getArticleMana(this.query)
            this.getData()
        },
        changeSort(e){
            this.query.sortName = e.key
            this.query.sortType = e.order
            this.getData()
        },
        getData(){
            this.loading = true
            this.getArticleMana(this.query).then(()=>{
                if(this.articles.length <1){
                    if(this.query.page >1){
                        --this.query.page
                        this.getData()
                    }
                }else{
                    this.loading=false
                }
            })
        },
        hide(){
            this.previewModel= false
        },
        show(data){
            this.previewData = data
            this.previewModel = true
        },
        remove(id){
            this.showDelete=true
            this.deleteId=id
        },
        deleteConfirm(){
            this.loading = true
            this.deleteBackendArticle(this.deleteId).then(()=>{
                this.showDelete=false
                this.getData()
            })
        },
        deleteCancel(){
            this.showDelete=false
        },
        batDelete(){
            if(this.selectId.length<1){
                return
            }
            this.batDeleteModal=true
        },
        batDeleteConfirm(){
            this.delloading=true
            setTimeout(()=>{
            this.batDeleteArticle(this.selectId).then(()=>{
                this.delloading=false
                this.batDeleteModal=false
            })
            },1000) 
        },
        batDeleteCancel(){
            this.batDeleteModal=false
        },
        batPublish(){
            if(this.selectId.length<1){
                return
            }
            this.publoading=true
            setTimeout(()=>{
            this.batPublishArticle(this.selectId).then(()=>{
                this.publoading=false
            })
            },1000) 
        },
        batRecall(){
            if(this.selectId.length<1){
                return
            }
            this.recloading=true
            setTimeout(()=>{
                this.batRecallArticle(this.selectId).then(()=>{
                this.recloading=false
            })
            },1000) 
        },
        selectChange(e){
            let i
            let data = new Object()
            this.selectId =[]
            for(i in e){
                this.selectId.push(e[i].id)
                data[i]=e[i].id
            }
            console.log(JSON.stringify(this.selectId ))
            console.log(data)
           
        }
    }
}
</script>
<style>
.table-container{
    /* height: 90%;
    overflow-y: auto; */
    height: 100%;
    overflow-y: auto;
}
.article-table{
    height: 90%;
}
.table-page{
    height: 10%;
    padding: 10px 0 ;
}
.image-preview{
    position:fixed;
    top:0;
    bottom:0;
    right:0;
    left:0;
    background:rgba(0,0,0,0.5);
    z-index:4001;
    display: flex;
    text-align: center;
}
.image-preview img{
    /* display: inline; */
    margin: auto;
}
</style>

