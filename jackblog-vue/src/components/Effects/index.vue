<template>
  <canvas id ="canvas" ></canvas>
</template>
<script>
  function Star(id, x, y, useCache, ctx, config, HEIGHT) {
    this.HEIGHT=HEIGHT
    this.config=config
    this.ctx = ctx
    this.id = id
    this.x = x
    this.y = y
    this.useCacha = useCache
    this.cacheCanvas = document.createElement("canvas")
    this.cacheCtx = this.cacheCanvas.getContext("2d")
    this.r = Math.floor(Math.random() * this.config.star_r) + 1
    this.cacheCtx.width = 6 * this.r
    this.cacheCtx.height = 6 * this.r
    var alpha = ( Math.floor(Math.random() * 10) + 1) / config.star_alpha
    this.color = "rgba(255,255,255," + alpha + ")"
    if (useCache) {
        this.cache()
    }
  }
  Star.prototype = {
    draw : function () {
      if (!this.useCacha) {
        this.ctx.save()
        this.ctx.fillStyle = this.color
        this.ctx.shadowBlur = this.r * 2
        this.ctx.beginPath()
        this.ctx.arc(this.x, this.y, this.r, 0, 2 * Math.PI, false)
        this.ctx.closePath()
        this.ctx.fill()
        this.ctx.restore()
      } else {
          this.ctx.drawImage(this.cacheCanvas, this.x - this.r, this.y - this.r)
      }
    },

    cache : function () {
        this.cacheCtx.save()
        this.cacheCtx.fillStyle = this.color
        this.cacheCtx.shadowColor = "white"
        this.cacheCtx.shadowBlur = this.r * 2
        this.cacheCtx.beginPath()
        this.cacheCtx.arc(this.r * 3, this.r * 3, this.r, 0, 2 * Math.PI)
        this.cacheCtx.closePath()
        this.cacheCtx.fill()
        this.cacheCtx.restore()
    },

    move : function () {
      this.y -= this.config.move_distance;
      if (this.y <= -10) {
        this.y += this.HEIGHT + 10;
      }
      this.draw();
    },
  }

  function Dot(id, x, y, useCache, ctx ,config, dots) {
    this.dots = dots
    this.config = config
    this.ctx= ctx
    this.id = id
    this.x = x
    this.y = y
    this.r = Math.floor(Math.random() * config.dot_r)+1
    this.speed = config.dot_speeds
    this.a = config.dot_alpha
    this.aReduction = config.dot_aReduction
    this.useCache = useCache
    this.dotCanvas = document.createElement("canvas")
    this.dotCtx = this.dotCanvas.getContext("2d")
    this.dotCtx.width = 6 * this.r
    this.dotCtx.height = 6 * this.r
    this.dotCtx.a = 0.5
    this.color = "rgba(255,255,255," + this.a +")"
    this.dotCtx.color = "rgba(255,255,255," + this.dotCtx.a + ")"
    this.linkColor = "rgba(255,255,255," + this.a/4 + ")"
    this.dir = Math.floor(Math.random()*140)+200
    if( useCache){
        this.cache()
    }
  }

  Dot.prototype = {
    draw : function () {
      if(!this.useCache){
        this.ctx.save()
        this.ctx.fillStyle = this.color
        this.ctx.shadowColor = "white"
        this.ctx.shadowBlur = this.r * 2
        this.ctx.beginPath()
        this.ctx.arc(this.x, this.y, this.r, 0, 2 * Math.PI, false)
        this.ctx.closePath()
        this.ctx.fill()
        this.ctx.restore()
      }else{
          this.ctx.drawImage(this.dotCanvas, this.x - this.r * 3, this.y - this.r *3)
      }
    },

    cache : function () {
      this.dotCtx.save()
      this.dotCtx.a  -= this.aReduction
      this.dotCtx.color = "rgba(255,255,255," + this.dotCtx.a + ")"
      this.dotCtx.fillStyle = this.dotCtx.color
      this.dotCtx.shadowColor = "white"
      this.dotCtx.shadowBlur = this.r * 2
      this.dotCtx.beginPath()
      this.dotCtx.arc(this.r * 3, this.r * 3, this.r, 0, 2 * Math.PI, false)
      this.dotCtx.closePath()
      this.dotCtx.fill()
      this.dotCtx.restore()
    },
    link : function () {
      if (this.id == 0) return
      var previousDot1 = getPreviousDot(this.id, 1, this.dots)
      var previousDot2 = getPreviousDot(this.id, 2, this.dots)
      var previousDot3 = getPreviousDot(this.id, 3, this.dots)
      var previousDot4 = getPreviousDot(this.id, 4, this.dots)


      if (!previousDot1) return
      this.ctx.strokeStyle = this.linkColor
      this.ctx.moveTo(previousDot1.x, previousDot1.y)
      this.ctx.beginPath()
      this.ctx.lineTo(this.x, this.y)
      if (previousDot2 != false) this.ctx.lineTo(previousDot2.x, previousDot2.y)
      if (previousDot3 != false) this.ctx.lineTo(previousDot3.x, previousDot3.y)
      if (previousDot4 != false) this.ctx.lineTo(previousDot4.x, previousDot4.y)

      this.ctx.stroke()
      this.ctx.closePath()
    },

    move : function () {
      this.a -= this.aReduction
      if(this.a <= 0 ){
          this.die()
          return
      }
      this.dotCtx.color = "rgba(255,255,255," + this.dotCtx.a + ")"
      this.color = "rgba(255,255,255," + this.a + ")"
      this.linkColor = "rgba(255,255,255," + this.a/4 + ")"
      this.x = this.x + Math.cos(degToRad(this.dir)) * this.speed
      this.y = this.y + Math.sin(degToRad(this.dir)) * this.speed
      this.draw()
      this.link()
    },

    die : function () {
      this.dots[this.id] = null
      delete this.dots[this.id]
    }
  }

  function getPreviousDot(id, stepback, dots){
    if(id == 0 || id - stepback < 0){
      return false
    }
    if(typeof dots[id - stepback] !== "undefined"){
      return dots[id - stepback]
    }else{
      return false
    }
  }

  function degToRad(deg) {
    return deg * (Math.PI / 180)
  }

export default {
    data(){
      return {
        stars:[],
        dots:[],
        canvas:'',
        ctx:'',
        WIDTH:1080,
        HEIGHT: 920,
        mouseMoving: false,
        mouseMoveChecker: '',
        mouseX:'',
        mouseY:'',
        config :{
          star_r : 3,
          star_alpha : 5,
          initStarsPopulation : 150,
          move_distance : 0.25,
          dot_r : 5,
          dot_speeds : 0.5,
          dot_alpha : 0.5,
          dot_aReduction : 0.01,
          dotsMinDist : 5,
          maxDistFromCursor : 50,
        }
      }
    },
    methods:{
      animate(){
        if(this.ctx){
          this.ctx.clearRect(0, 0, this.WIDTH, this.HEIGHT);
        }
        for (var i in this.stars) {
            this.stars[i].move();
        }
        for (var i in this.dots) {
            this.dots[i].move();
        }
        requestAnimationFrame(this.animate)
      },
      drawIfMouseMoving(){
        if (!this.mouseMoving) return;
        if (this.dots.length == 0) {
          this.dots[0] = new Dot(0, this.mouseX, this.mouseY, true, this.ctx, this.config, this.dots);
          this.dots[0].draw();
          return;
        }

        var previousDot = this.getPreviousDot(this.dots.length, 1);
        var prevX = previousDot.x;
        var prevY = previousDot.y;

        var diffX = Math.abs(prevX - this.mouseX);
        var diffY = Math.abs(prevY - this.mouseY);

        if (diffX < this.config.dotsMinDist || diffY < this.config.dotsMinDist) return;

        var xVariation = Math.random() > .5 ? -1 : 1;
        xVariation = xVariation*Math.floor(Math.random() * this.config.maxDistFromCursor)+1;
        var yVariation = Math.random() > .5 ? -1 : 1;
        yVariation = yVariation*Math.floor(Math.random() * this.config.maxDistFromCursor)+1;
        this.dots.push(new Dot(this.dots.length, this.mouseX+xVariation, this.mouseY+yVariation,true,this.ctx, this.config,this.dots));
        this.dots[this.dots.length-1].draw();
        this.dots[this.dots.length-1].link();
      },
      drawMove(e){
        this.mouseMoving = true
        this.mouseX = e.clientX
        this.mouseY = e.clientY
        this.drawIfMouseMoving()
        clearInterval(this.mouseMoveChecker)
        this.mouseMoveChecker = setInterval(function () {
            this.mouseMoving = false
        },1000)
      },
      getPreviousDot(id, stepback) {
        if(id == 0 || id - stepback < 0){
          return false
        }
        if(typeof this.dots[id - stepback] !== "undefined"){
          return this.dots[id - stepback]
        }else{
          return false
        }
      },
      handleWindowWidth(){
        this.WIDTH = document.documentElement.clientWidth;
        this.HEIGHT = document.documentElement.clientHeight;
        this.canvas.setAttribute("width", this.WIDTH);
        this.canvas.setAttribute("height", this.HEIGHT);
        for (var i = 0; i < this.config.initStarsPopulation; i++) {
          this.stars[i] = new Star(i, Math.floor(Math.random()*this.WIDTH), Math.floor(Math.random()*this.HEIGHT),true, this.ctx, this.config, this.HEIGHT)
        }
      }
    },
    mounted(){
      window.onresize = () => {
         return (() => {
           this.handleWindowWidth()
         })()
       }
      this.canvas = document.getElementById('canvas')
      this.ctx = this.canvas.getContext('2d')
      this.WIDTH = document.documentElement.clientWidth;
      this.HEIGHT = document.documentElement.clientHeight;
      this.canvas.setAttribute("width", this.WIDTH);
      this.canvas.setAttribute("height", this.HEIGHT);
      this.ctx.strokeStyle = "white"
      this.ctx.shadowColor = "white"
      for (var i = 0; i < this.config.initStarsPopulation; i++) {
        this.stars[i] = new Star(i, Math.floor(Math.random()*this.WIDTH), Math.floor(Math.random()*this.HEIGHT),true, this.ctx, this.config, this.HEIGHT)
      }
      this.ctx.shadowBlur = 0
      this.animate()
    },
}
</script>
<style>
#canvas {
  position: fixed;
  width: 100%;
  height: 100%;
}
</style>
