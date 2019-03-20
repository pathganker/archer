<template>
    <div class="table-container">
        <Table :data="articles" :columns="tableColumns1" stripe class="article-table" 
        :height="tableHeight"  @on-sort-change="changeSort" :loading="loading"></Table>
        <div class="table-page">
            <div style="float: right;">
                <Page :total="total" :current="1" @on-change="changePage"></Page>
            </div>
        </div>
    </div>
</template>
<script>
import { Table, Page , Tag, Poptip, Button} from 'iview'
import { mapState,mapActions } from 'vuex'
import image from '../../assets/images/Katsuragi.jpg'
export default {
    components:{ Table, Page, Tag, Poptip},
      computed: {
        ...mapState({
            articles: ({manage}) => manage.articles.items,
            total: ({manage}) => manage.articles.total,
            tableHeight: ({globalVal}) => globalVal.height && (globalVal.height-289-70-60-48)*0.9
        })
      },
    data () {
        return {
            tableColumns1: [
                {
                    type: 'selection',
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
                        return h('img',{
                            attrs :{
                              src: image
                            },
                            style: {
                                width: '40px',
                                height: '40px',
                            }
                        })
                    }
                    // render: (h, params) => {
                    //     return h(Poptip, {
                    //         props: {
                    //             trigger: 'hover',
                    //             title: params.row.portrayal.length + 'portrayals',
                    //             placement: 'bottom'
                    //         }
                    //     }, [
                    //         h(Tag, params.row.portrayal.length),
                    //         h('div', {
                    //             slot: 'content'
                    //         }, [
                    //             h('ul', this.tableData1[params.index].portrayal.map(item => {
                    //                 return h('li', {
                    //                     style: {
                    //                         textAlign: 'center',
                    //                         padding: '4px'
                    //                     }
                    //                 }, item)
                    //             }))
                    //         ])
                    //     ]);
                    // }
                },
                {
                    title: '浏览次数',
                    key: 'visitCount',
                    sortable: 'custom',
                    // render: (h, params) => {
                    //     return h(Poptip, {
                    //         props: {
                    //             trigger: 'hover',
                    //             title: params.row.people.length + 'customers',
                    //             placement: 'bottom'
                    //         }
                    //     }, [
                    //         h(Tag, params.row.people.length),
                    //         h('div', {
                    //             slot: 'content'
                    //         }, [
                    //             h('ul', this.tableData1[params.index].people.map(item => {
                    //                 return h('li', {
                    //                     style: {
                    //                         textAlign: 'center',
                    //                         padding: '4px'
                    //                     }
                    //                 }, item.n + '：' + item.c + 'People')
                    //             }))
                    //         ])
                    //     ]);
                    // }
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
            'deleteBackendArticle'
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
                this.loading=false
            })
        },

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
</style>

