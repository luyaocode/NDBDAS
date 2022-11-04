<template>
  <div class="signal-building">
    <svg id="图层_2" data-name="图层 2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1920 1080">
    <router-link to="/building/no1/f1">
      <polygon id="f1" class="cls-1" points="536.86 889.22 184.59 739.27 184.59 764.26 536.86 914.22 536.86 889.22"/>
    </router-link>
      <polygon id="f2" class="cls-1" points="536.86 842.46 184.59 692.51 184.59 717.5 536.86 867.46 536.86 842.46"/>
      <polygon id="f3" class="cls-1" points="547.74 801.75 195.47 651.8 195.47 676.79 547.74 826.74 547.74 801.75"/>
      <polygon id="f4" class="cls-1" points="536.86 747.84 184.59 597.89 184.59 622.88 536.86 772.83 536.86 747.84"/>
      <polygon id="f5" class="cls-1" points="536.86 702.32 184.59 552.37 184.59 577.36 536.86 727.31 536.86 702.32"/>
      <polygon id="f6" class="cls-1" points="536.86 654.11 184.59 504.16 184.59 529.15 536.86 679.11 536.86 654.11"/>
      <polygon id="f7" class="cls-1" points="547.74 614.85 195.47 464.89 195.47 489.89 547.74 639.84 547.74 614.85"/>
      <polygon id="f8" class="cls-1" points="547.74 566.64 195.47 416.69 195.47 441.68 547.74 591.63 547.74 566.64"/>
      <polygon id="f9" class="cls-1" points="547.74 515.41 195.47 365.46 195.47 390.45 547.74 540.4 547.74 515.41"/>
      <polygon id="f10" class="cls-1" points="547.74 472.01 195.47 322.06 195.47 347.05 547.74 497 547.74 472.01"/>
      <polygon id="f11" class="cls-1" points="536.86 419 184.59 269.05 184.59 294.05 536.86 444 536.86 419"/>
      <polygon id="f12" class="cls-1" points="547.74 376.16 195.47 226.21 195.47 251.2 547.74 401.15 547.74 376.16"/>
      <polygon id="f13" class="cls-1" points="547.74 328.5 195.47 178.55 195.47 203.54 547.74 353.5 547.74 328.5"/>
      <polygon id="f14" class="cls-1" points="536.86 282.98 184.59 133.03 184.59 158.02 536.86 307.97 536.86 282.98"/>
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

  .signal-building {
    background-image: url('../../assets/images/signalbuilding.jpg');
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


