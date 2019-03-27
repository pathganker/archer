<template>
    <div class="right-nav" v-if="isSign">
        <Button @click="navicon = true" type="primary"><span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span></Button>
        <Drawer title="导航" placement="right" :closable="false" v-model="navicon">
                <Row>
                    <img class="avatar" :src="user.avatar" /><a class="nav-name">{{user.nickname}}</a>
                </Row>
                <Divider />
                <Row class="nav-function">
                    <router-link   :to="{ path: '/settings' }"  title="设置"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                    设置
                    </router-link>
                </Row>
                <Row  class="nav-function">
                    <a href="javascript:;"  @click="logout()"   title="登出"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                    登出
                    </a>
                </Row>
        </Drawer>
    </div>
</template>
<script>
import {Button,Drawer,Divider,Row } from 'iview'
import { mapState,mapActions } from 'vuex'
import DefaulAvatar from '../../assets/images/Katsuragi.jpg'
export default {
    components :{Button,Drawer, Divider,Row },
    computed: {
        ...mapState({
            isSign: state => state.auth.isSigin,
            user: state =>  state.auth.user
        })
    },
    data(){
        return{
            defaulAvatar:DefaulAvatar,
            navicon: false
        }
    },
    methods:{
        ...mapActions([
            'logout'
        ])
    }
}
</script>
<style>
.right-nav{
    position: fixed;
    bottom: 5px;
    right:8%;
}
.avatar{
    height: 80px;
    width: 80px;
    border-radius: 50%;
}
.nav-name{
    margin: 0 10px;
    font-size: 16px;
    text-decoration: none;
    color: black!important;
}
.nav-function{
    margin: 10px 5px;
}
.nav-function a{
    font-size: 16px;
    color: black!important;
    text-decoration: none;
}
</style>

