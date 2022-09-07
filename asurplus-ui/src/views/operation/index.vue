<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" @open="onOpen" @close="onClose" title="Dialog Titile">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="开关" prop="field101" required>
          <el-switch v-model="formData.field101"></el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    inheritAttrs: false,
    components: {},
    props: [],
    data() {
      return {
        formData: {
          field101: false,
        },
        rules: {},
      }
    },
    computed: {},
    watch: {},
    created() {},
    mounted() {},
    methods: {
      onOpen() {},
      onClose() {
        this.$refs['elForm'].resetFields()
      },
      close() {
        this.$emit('update:visible', false)
      },
      handelConfirm() {
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
          this.close()
        })
      },
    }
  }

</script>
<style>
</style>
