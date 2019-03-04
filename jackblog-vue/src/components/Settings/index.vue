<template>
  <div class="settings-box">
    <div class="setting-nav">
      <ul class="nav nav-pills nav-stacked">
        
        <li v-for="(setting, index) in settings" :key="index" @click="setActive(index)" :class="{active:index==navIndex}">
          <a>
            <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
            {{setting.title}}</a></li>
      </ul>
    </div>
    <div class="settings-container">
      <div class="settings-content" v-if="navIndex==0">
        <form class="form-horizontal" @submit.prevent="mdUser()" novalidate >
          <div class="form-group">
            <a class="col-sm-4 setting-avatar" v-bind:title="nickname">
              <div id="canvascontainer" ref='can'></div>
              <!-- <img :src="newAvatar?newAvatar:defaultAvatar"/> -->
            </a>
            <a class="col-sm-2 btn setting-btn">
              <input class="file-input" type="file" name="newAvatar" @change="changeAvatar($event)" accept="image/gif,image/jpeg,image/jpg,image/png"/>
              <span class="file-input-span">修改头像</span>
            </a>
          </div>
          <div class="form-group">
            <label class="col-sm-4 control-label">昵称</label>
            <div class="col-sm-8">
              <input type="text" name="nickname" v-model="newNickname" v-validate="'required|nickname|min:4|max:30'" class="form-control" placeholder="2-15字符，中英文、数字和下划线" />
            </div>
          </div>
          <hr />
          <div class="form-group">
            <label class="col-sm-4 control-label">邮箱</label>
            <div class="col-sm-8">
              <input type="text" name="email" v-model="email" v-validate="'required|nickname|min:4|max:30'" class="form-control" placeholder="" />
            </div>
          </div>
          <div class="form-group">
          </div>
          <div class="form-group">
          </div>
          <div class="form-group">
            <div class="col-sm-4">
              <button type="submit" class="btn btn-block btn-lg btn-primary">保 存</button>
            </div>
          </div>
        </form>
      </div>
    </div>
    <div class="settings-content" v-if="navIndex==1">
      <ul><li>待开发</li></ul>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import { Validator } from 'vee-validate'
import defaultAvatar from '../../assets/images/avatar.png'
export default {
  data() {
    return {
      mdNickname: null,
      navIndex: 0,
      settings:[
        {title: '基本设置'},
        {title: '其他设置'}
        ],
      email:'',
      newAvatar:'',
      file:''
    }
  },
  computed: {
    ...mapState({
      nickname: ({auth}) => auth.user && auth.user.nickname || '',
      auth: state => state.auth,
    }),
    newNickname: {
      get () {
        return this.mdNickname === null?this.nickname:this.mdNickname
      },
      set (value) {
        this.mdNickname = value || ''
      }
    },
    defaultAvatar() {
      return defaultAvatar
    }	
  },
  created(){
    if(!this.nickname){
      this.getUserInfo()
    }
  },
  methods: {
    ...mapActions([
      'updateUser',
      'getUserInfo'
    ]),  
    mdUser() {
      if(this.newNickname){
        this.updateUser({ nickname: this.newNickname })
      }
    },
    setActive(num){
      this.navIndex=num
    },
    changeAvatar(e){
      let file = e.target.files[0]
      if(file){
        this.file = file
        console.log(file)
        let reader = new FileReader()
        reader.onload = (data) => {
          let res = data.target || data.srcElement
          this.newAvatar = res.result
        }
        reader.readAsDataURL(file)
      }
    },

  },
  mounted(){
    container = document.createElement( 'div' )
    this.$refs.can.appendChild( container )
    camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 1, 10000 )
    camera.position.z = 1000
    scene = new THREE.Scene()
    particles = new Array()
    var PI2 = Math.PI * 2
    var material = new THREE.ParticleCanvasMaterial({
      color: 0x0078de,
      program: function ( context ) {
        context.beginPath()
        context.arc( 0, 0, 1, 0, PI2, true )
        context.fill()
      }
    })
    var i = 0
    for ( var ix = 0; ix < AMOUNTX; ix ++ ) {
      for ( var iy = 0; iy < AMOUNTY; iy ++ ) {
        particle = particles[ i ++ ] = new THREE.Particle( material )
        particle.position.x = ix * SEPARATION - ( ( AMOUNTX * SEPARATION ) / 2 )
        particle.position.z = iy * SEPARATION - ( ( AMOUNTY * SEPARATION ) / 2 )
        scene.add( particle )
      }
    }
    renderer = new THREE.CanvasRenderer();
    renderer.setSize( window.innerWidth, window.innerHeight );
    container.appendChild( renderer.domElement );
    document.addEventListener( 'mousemove', onDocumentMouseMove, false );
    window.addEventListener( 'resize', onWindowResize, false );
    animate()    
  }
}


var SEPARATION = 100, AMOUNTX = 50, AMOUNTY = 50;

var container;
var camera, scene, renderer;

var particles, particle, count = 0;

var mouseX = 0, mouseY = 0;

var windowHalfX = window.innerWidth / 2;
var windowHalfY = window.innerHeight / 2;


// animate();

function init() {

  

}

function onWindowResize() {

  windowHalfX = window.innerWidth / 2;
  windowHalfY = window.innerHeight / 2;

  camera.aspect = window.innerWidth / window.innerHeight;
  camera.updateProjectionMatrix();

  renderer.setSize( window.innerWidth, window.innerHeight );

}

//

function onDocumentMouseMove( event ) {

  mouseX = event.clientX - windowHalfX;
  mouseY = event.clientY - windowHalfY;

}

function onDocumentTouchStart( event ) {

  if ( event.touches.length === 1 ) {

    event.preventDefault();

    mouseX = event.touches[ 0 ].pageX - windowHalfX;
    mouseY = event.touches[ 0 ].pageY - windowHalfY;

  }

}

function onDocumentTouchMove( event ) {

  if ( event.touches.length === 1 ) {

    event.preventDefault();

    mouseX = event.touches[ 0 ].pageX - windowHalfX;
    mouseY = event.touches[ 0 ].pageY - windowHalfY;

  }

}

//

function animate() {

  requestAnimationFrame( animate );

  render();


}

function render() {

  camera.position.x += ( mouseX - camera.position.x ) * .05;
  camera.position.y += ( - mouseY - camera.position.y ) * .05;
  camera.lookAt( scene.position );

  var i = 0;

  for ( var ix = 0; ix < AMOUNTX; ix ++ ) {

    for ( var iy = 0; iy < AMOUNTY; iy ++ ) {

      particle = particles[ i++ ];
      particle.position.y = ( Math.sin( ( ix + count ) * 0.3 ) * 50 ) + ( Math.sin( ( iy + count ) * 0.5 ) * 50 );
      particle.scale.x = particle.scale.y = ( Math.sin( ( ix + count ) * 0.3 ) + 1 ) * 2 + ( Math.sin( ( iy + count ) * 0.5 ) + 1 ) * 2;

    }

  }

  renderer.render( scene, camera );

  count += 0.1;

}
</script>
<style>
.setting-avatar img{
  height: 120px;
  width: 120px;
  border-radius: 50%;
}
.form-horizontal .form-group {
    margin-right: 15px; 
    margin-left: 15px; 
}
.form-horizontal .control-label{
  text-align: left
}
.btn.setting-btn{
  min-width: 80px;
  border-radius: 20px;
  outline: none;
  margin-top: 35px;
  background-color: white;
  border-color: rgb(5, 193, 240);
  color: rgb(5, 193, 240);
  height: 40px;
  margin-left: 15px;
}
.btn.setting-btn:focus{
  outline: none;
}
.file-input{
  position: absolute;
  z-index: 1000;
  width: 100%;
  height: 100%;
  opacity: 0;
  left: 0;
  top: 0;

}
.file-input-span{
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 100;
  top: 0;
  left: 0;
  margin-top: 10px;
}
</style>
