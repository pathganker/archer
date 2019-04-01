<template>
	<div class="login-sns">
	  <ul>
      <li  v-for="(item,index) in logins" :key="index" @click.stop.prevent="snsLogin(item)">
        <a v-bind:class="item.name" href="#"><i v-bind:class="'fa fa-' + item.name"></i></a>
      </li>
	  </ul>
	</div>
</template>

<script>
import {getCookie,isLogin} from '../../utils/cookies'

export default {
  props: {
    logins:{
      type: Array
    }
  },
  methods: {
    snsLogin(provider){
      console.log(provider)
      let search = provider.authorizationUri + '?client_id=' + provider.clientId + '&redirect_uri=' + provider.redirectUri
      window.location.href = search
    }
  }
}
</script>
