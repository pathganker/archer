<template>
  <div class="creation-content">
    <Split v-model="splitper"  ref="split" min="238" >
      <div slot="left" class="demo-split-pane no-padding">
        <Edition :editionList="editionList" :cured="cured" :curar="curar"></Edition>
      </div>
      <div slot="right" class="demo-split-pane">
        <Markdown ref="mark" :editionList="editionList" :cured="cured" :curar="curar" :draft="draft"></Markdown>
      </div>
     </Split>
     <Savemodal ref='savmodal' ></Savemodal>
  </div>
</template>