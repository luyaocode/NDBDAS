<template>
  <el-button @click="testSend">测试发送指令</el-button>
</template>

<script>
  import {sendCtl} from "@/api/test";
  export default {
    name: "Config",
    data() {
      return {};
    },
    created() {
    },
    methods: {
      testSend(){
        console.log("开始发送");
        sendCtl().then(response=>{
          console.log(response);
        });

      }
    }
  }


</script>

<style>

</style>
