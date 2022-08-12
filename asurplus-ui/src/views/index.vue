<template>
  <div style="background: #1ab394">
    <span>大楼示意图</span>
<!--    svg-pan-zoom控件-->
  <SvgPanZoom
    style="width: 500px; height: 500px; border:1px solid black;"
    :zoomEnabled="true"
    :controlIconsEnabled="true"
    :fit="false"
    :center="true"
  >
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="-250 -250 1200 1200" style="width:500px;height:500px;background: #f1fa8c">

      <g id="图层_1">
        <!--        几种实现页面跳转的方式：
                    1、a标签                              （×）
                    2、window.open()                     （×）
                    3、路由跳转router-link标签包裹图形-->
        <router-link to="/show/table">
          <polygon id="f1" class="cls-1" points="226.57 435.55 1.39 336.29 226.57 237.03 439.9 336.29 226.57 435.55"/>
        </router-link>

      </g>

      <g id="图层_2">
        <polygon id="f2" class="cls-1" points="228.41 377.6 3.23 278.34 228.41 179.08 441.74 278.34 228.41 377.6"/>
      </g>

      <g id="图层_3">
        <polygon id="f3" class="cls-1" points="226.42 317.42 1.24 218.16 226.42 118.9 439.75 218.16 226.42 317.42"/>
      </g>

      <g id="图层_4">
        <polygon id="f4" class="cls-1" points="228.41 254.42 3.23 155.16 228.41 55.91 441.74 155.16 228.41 254.42"/>
      </g>

      <g id="图层_5">
        <polygon id="f5" class="cls-1" points="228.41 199.07 3.23 99.81 228.41 0.55 441.74 99.81 228.41 199.07"/>
      </g>

    </svg>
  </SvgPanZoom>
    <div>

    </div>
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
    components: {  SvgPanZoom }

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
    fill: aqua;
    stroke: #000000;
    stroke-miterlimit: 10;
    /*width:1px;*/
    /*height: 1px;*/
    max-height: 50px;
    max-width: 50px;


  }
</style>


