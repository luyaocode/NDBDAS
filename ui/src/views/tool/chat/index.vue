<template>
  <div>
    <span v-for="item in message">{{item.date}}<br>{{item.msg}}<br></span>
  </div>

</template>

<script>
  import {requestData} from "@/api/tool/chat";
  export default {
    name: "Chat",
    data() {
      return {
        message: []
      }
    },

    created() {
      this.alarm = window.setInterval(() => {
        setTimeout(() => {
          this.getData();
        }, 500)
      }, 60000);
    },
    methods: {
      getData() {
        requestData().then(response => {
          console.log(response);
          this.message.push(response);
        })
      }
    }
  }
</script>


<style>

</style>



