<!--这个组件显示的是和value相等的名称，传入的是对象数组。
如属性status值为0，描述为正常，显示正常-->
<template>
  <div>
    <template v-for="(item, index) in options">
      <template v-if="values.includes(item.code)">
        <span
          v-if="item.listClass == 'default' || item.listClass == ''"
          :key="item.code"
          :index="index"
          :class="item.cssClass"
          >{{ item.name }}</span
        >
        <el-tag
          v-else
          :key="item.code"
          :index="index"
          :type="item.listClass == 'primary' ? '' : item.listClass"
          :class="item.cssClass"
        >
          {{ item.name }}
        </el-tag>
      </template>
    </template>
  </div>
</template>

<script>
export default {
  name: "DictTag",
  props: {
    options: {
      type: Array,
      default: null,
    },
    value: [String, Array, Number],
  },
  computed: {
    values() {
      if (this.value) {
        return Array.isArray(this.value) ? this.value : [this.value];
      } else {
        return [];
      }
    },
  },
};
</script>
<style scoped>
.el-tag + .el-tag {
  margin-left: 10px;
}
</style>
