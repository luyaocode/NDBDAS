<template>
  <div class="industrial-zone">
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1920 1080">
      <g id="图层_2" data-name="图层 2">
        <router-link to="/building/no1">
        <polygon class="cls-1"
                 points="674.5 729.5 974.5 566.5 1056.5 634.5 1054.5 727.5 759.5 875.5 676.5 814.5 674.5 729.5"/>
        </router-link>
      </g>
      <g id="图层_3" data-name="图层 3">
        <router-link to="/building/no2">
        <polygon class="cls-1"
                 points="596.5 562.5 824.5 477.5 883.5 529.5 885.5 596.5 659.5 696.5 602.5 646.5 596.5 562.5"/>
        </router-link>
      </g>
      <g id="图层_4" data-name="图层 4">
        <router-link to="/building/no3">
        <polygon class="cls-1"
                 points="437.5 410.5 765.5 303.5 857.5 359.5 866.5 434.5 528.5 555.5 456.5 481.5 437.5 410.5"/>
        </router-link>
      </g>
      <g id="图层_5" data-name="图层 5">
        <router-link to="/building/no4">
        <polygon class="cls-1"
                 points="289.5 277.5 854.5 110.5 948.5 162.5 960.5 231.5 365.5 424.5 309.5 342.5 289.5 277.5"/>
        </router-link>
      </g>
      <g id="图层_6" data-name="图层 6">
        <router-link to="/building/no5">
        <polygon class="cls-1"
                 points="1048.5 216.5 1417.5 414.5 1420.5 473.5 1254.5 555.5 898.5 325.5 906.5 258.5 1048.5 216.5"/>
        </router-link>
      </g>
    </svg>
  </div>
</template>

<script>
  import SvgPanZoom from "vue-svg-pan-zoom";

  export default {
    name: "index",
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
  .cls-1 {
    fill: transparent;
    stroke: none;
    /*stroke-miterlimit: 10;*/
    /*stroke-width: 2px;*/
    /*opacity: 0;*/

  }

  .industrial-zone {
    background-image: url('../assets/images/industrial-zone.jpg');
    background-size: cover;
    background-repeat: no-repeat;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    zoom: 1;
    overflow-y: scroll; /*解决切换页面剧烈抖动的问题*/

  }
</style>


