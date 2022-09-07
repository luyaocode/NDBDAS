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
