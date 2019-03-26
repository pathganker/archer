<template>
    <div class="manage-container">
        <Layout class="layout-body">
            <Breadcrumb  class="nav-bread">
                <BreadcrumbItem :to="{ path: '/manage/' }"><Icon type="ios-ice-cream"></Icon>&nbsp;&nbsp;管理</BreadcrumbItem>
                <BreadcrumbItem ><Icon :type="curMenu.icon"></Icon>&nbsp;&nbsp;{{curMenu.title}}</BreadcrumbItem>
                <BreadcrumbItem><Icon type="ios-cafe"></Icon>&nbsp;&nbsp;{{curSubmenu.title}}</BreadcrumbItem>
            </Breadcrumb>
                <Layout class="layout-content">
                    <Sider class="sider-menu" :style="{background: '#fff'}">
                        <Menu active-name="1-1" theme="light" width="auto" :open-names="openNames" @on-select="select" @on-open-change="open">
                            <Submenu v-for="(menu,i) in menudata" :key="i" :name="menu.name" >
                                <template slot="title">
                                    <Icon :type="menu.icon"></Icon>
                                    {{menu.title}}
                                </template>
                                <MenuItem v-for="(menunext,j) in menu.submenu" :key="j" :name="menunext.name" :to="{ path: menunext.path }" class="menunext">{{menunext.title}}</MenuItem>
                            </Submenu>
                        </Menu>
                    </Sider>
                    <Content :style="{padding: '10px 10px 5px 10px', minHeight: '280px', background: '#fff'}">
                        <router-view class="enter"></router-view>
                    </Content>
                </Layout>
            </Layout>
        <Footer class="layout-footer-center">2019-9102 &copy; Qingqfeng.com.cn</Footer>
    </div>
</template>
<script>
import {Layout,Breadcrumb,BreadcrumbItem,Content,Sider,Menu,Submenu,MenuItem,Footer,Icon} from 'iview'
export default {
    components:{Layout,Breadcrumb,BreadcrumbItem,Content,Sider,Menu,Submenu,MenuItem,Footer,Icon},
    data(){
        return{
            curMenu: {name:"1", title:"文章管理",icon:"ios-albums"},
            curSubmenu: {name:"1-1",title:"文章列表",path:"/manage/article"},
            openNames: ["1"],
            menudata: [
                {name:"1", title:"文章管理",icon:"ios-albums",submenu:[
                    {name:"1-1",title:"文章列表",path:"/manage/article"},
                    {name:"1-2",title:"标签管理",path:"/manage/tag"},
                    ]
                },
                {name:"2",title:"用户管理",icon:"ios-people",submenu:[
                    {name:"2-1",title:"个人用户",path:"/manage/my"},
                    {name:"2-2",title:"微博/微信",path:"/manage/other"},
                    ]
                },
                {name:"3",title:"统计数据",icon:"ios-analytics", submenu:[
                    {name:"3-1",title:"访问记录",path:"/manage/statics"},
                    ]
                }
            ]
        }
    },
    methods:{
        select(e){
            const index = e.split('-')
            if(index.length == 2 && this.menudata){
                this.curMenu = this.menudata[index[0]-1]
                this.curSubmenu = this.menudata[index[0]-1].submenu[index[1]-1]
            }
        },
        open(e){
            console.log(this.openNames)
        },
    }
}   
</script>
<style>
.manage-container{
    position: absolute;
    width: 100%;
    background:white;
    top: 289px;
    bottom: 0;
    
}
.layout-body{
    position: relative;
    width: 100%;
    height: 100%;
    padding: 0px 0px 70px 0px;
    background: 0 0;
}
.nav-bread{
    padding:  16px 30px;
    background: rgb(134, 171, 252);
    height: 53px;
    color: black;
}
.layout-content{
    width: 100%;
    height: 100%;
    padding: 0px 30px 00px 30px;
    background: 0 0;
}
.sider-menu{
    height: 100%;
    overflow-y: auto;
}   
.layout-footer-center{
    position: absolute;
    width: 100%;
    bottom: 0;
    text-align: center;
    height: 70px;
    background: rgb(134, 171, 252);
}
.menunext{
    color:#515a6e!important;
}
</style>
