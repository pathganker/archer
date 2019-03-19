<template>
    <div class="manage-container">
        <Layout class="layout-body">
            <Breadcrumb  class="nav-bread">
                <BreadcrumbItem>管理</BreadcrumbItem>
                <BreadcrumbItem>文章管理</BreadcrumbItem>
                <BreadcrumbItem>文章列表</BreadcrumbItem>
            </Breadcrumb>
                <Layout class="layout-content">
                    <Sider class="sider-menu" :style="{background: '#fff'}">
                        <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
                            <Submenu name="1">
                                <template slot="title">
                                    <Icon type="ios-navigate"></Icon>
                                    文章管理
                                </template>
                                <MenuItem name="1-1" :to="{ path: '#/article' }">文章列表</MenuItem>
                                <MenuItem name="1-2" :to="{ path: '#/tag' }">标签管理</MenuItem>
                            </Submenu>
                            <Submenu name="2">
                                <template slot="title">
                                    <Icon type="ios-keypad"></Icon>
                                    用户管理
                                </template>
                                <MenuItem name="2-1">个人用户</MenuItem>
                                <MenuItem name="2-2">微信/微博用户</MenuItem>
                            </Submenu>
                            <Submenu name="3">
                                <template slot="title">
                                    <Icon type="ios-analytics"></Icon>
                                    统计数据
                                </template>
                                <MenuItem name="3-1">访问次数</MenuItem>
                            </Submenu>
                        </Menu>
                    </Sider>
                    <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
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
    components:{Layout,Breadcrumb,BreadcrumbItem,Content,Sider,Menu,Submenu,MenuItem,Footer,Icon}
}   
</script>
<style>
.manage-container{
    position: absolute;
    width: 100%;
    background:white;
    top: 298px;
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
</style>
