<template>
  <div class="floor">
    <svg id="图层_2" data-name="图层 2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1920 1080">
      <router-link to="/device/show/01">
      <rect id="A01-01" class="cls-1" x="513.5" y="379.5" width="178" height="122"/>
      </router-link>
      <router-link to="/device/show/02">
      <rect id="A01-02" class="cls-1" x="513.5" y="510.5" width="167" height="108"/>
      </router-link>
    </svg>
  </div>
</template>

<script>
  import SvgPanZoom from "vue-svg-pan-zoom";

  export default {
    name: "No1",
    data() {
      return {
        // 版本号
        version: "1.0.1",
        date: new Date()
      };
    },
    methods: {
      goTarget(href) {
        window.open(href, "_blank");
      },
    },
    components: {SvgPanZoom}

  };

</script>

<style scoped lang="scss">
  .home {
    blockquote {
      padding: 10px 20px;
      margin: 0 0 20px;
      font-size: 17.5px;
      border-left: 5px solid #eee;
    }

    hr {
      margin-top: 20px;
      margin-bottom: 20px;
      border: 0;
      border-top: 1px solid #eee;
    }

    .col-item {
      margin-bottom: 20px;
    }

    ul {
      padding: 0;
      margin: 0;
    }

    font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 13px;
    color: #676a6c;
    overflow-x: hidden;

    ul {
      list-style-type: none;
    }

    h4 {
      margin-top: 0px;
    }

    h2 {
      margin-top: 10px;
      font-size: 26px;
      font-weight: 100;
    }

    p {
      margin-top: 10px;

      b {
        font-weight: 700;
      }
    }

    .update-log {
      ol {
        display: block;
        list-style-type: decimal;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0;
        margin-inline-end: 0;
        padding-inline-start: 40px;
      }
    }

  }
</style>

<style>
  /*.cls-1 {*/
  /*  fill: black;*/
  /*  stroke: blue;*/
  /*  stroke-miterlimit: 10;*/
  /*  stroke-width: 2px;*/
  /*  opacity: 0.5;*/

  /*}*/

  .floor {
    background-image: url('../../../assets/images/no1/f1.png');
    background-size: cover;
    background-repeat: no-repeat;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    /*min-width: 1600px;*/
    /* z-index: -10;*/
    zoom: 1;
    overflow-y: scroll;
  }
</style>


