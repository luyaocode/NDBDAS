<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">
<!--      输入框-->
      <el-form-item label="设备编号" prop="devId">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入设备编号"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        /><!--点击回车键触发handleQuery函数 -->
      </el-form-item>

<!--      搜索按钮-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <!--数据显示    -->
    <el-table v-loading="loading" :data="eParaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" />
      <el-table-column label="记录编号" prop="id" />
      <el-table-column label="设备编号" prop="devId" />
      <el-table-column label="设备名称" prop="devName" />
      <el-table-column label="时间" prop="createTime" />
      <el-table-column label="频率" prop="freq" />
      <el-table-column label="总功率" prop="totalPow" />
      <el-table-column label="A相电压" prop="voltA" />
      <el-table-column label="B相电压" prop="voltB" />
      <el-table-column label="C相电压" prop="voltC" />
      <el-table-column label="A相电流" prop="currA" />
      <el-table-column label="B相电流" prop="currB" />
      <el-table-column label="C相电流" prop="currC" />
      <el-table-column label="A相有功" prop="actiPowA" />
      <el-table-column label="B相有功" prop="actiPowB" />
      <el-table-column label="C相有功" prop="actiPowC" />
      <el-table-column label="A相无功" prop="wattPowA" />
      <el-table-column label="B相无功" prop="wattPowB" />
      <el-table-column label="C相无功" prop="wattPowC" />
      <el-table-column label="A相视在" prop="apprPowA" />
      <el-table-column label="B相视在" prop="apprPowB" />
      <el-table-column label="C相视在" prop="apprPowC" />
      <el-table-column label="A相电压相角" prop="voltPhaA" />
      <el-table-column label="B相电压相角" prop="voltPhaB" />
      <el-table-column label="C相电压相角" prop="voltPhaC" />
      <el-table-column label="A相电流相角" prop="currPhaA" />
      <el-table-column label="B相电流相角" prop="currPhaB" />
      <el-table-column label="C相电流相角" prop="currPhaC" />


    </el-table>

    <!--   分页组件 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
  import {listEPara} from "@/api/device/dev";
  export default {
    name: "DeviceStatus",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 状态数据字典
        statusOptions: [],
        // 日期范围
        dateRange: [],
        // 角色表格数据
        eParaList: [],
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: undefined,
          sign: undefined,
          status: undefined
        },
      }
    },
    created(){
      this.getList();
      // this.getDicts("status").then(response => {
      //   this.statusOptions = response.data;
      // });
    },
    methods:{
      /** 查询角色列表 */
      getList() {
        //开始加载
        this.loading = true;
        console.log("开始加载");
        /**
         * addDateRange():传入查询参数和日期范围，输出查询参数
         */
        /**
         * a.then(response=>{
         *   b()
         * })
         * b函数要用到a函数的返回值response
         */
        /**
         *  listRole在"@/api/system/role"下的js文件中是这样定义的
         export function listRole(query) {
          return request({
            url: baseUrl + 'list',
            method: 'get',
            params: query
          })
        }
         */
        listEPara().then(
          response => {
            console.log(response);
            this.eParaList = response;
            // this.total = response.total;
            this.loading = false;
            console.log(this.eParaList);
          }
        );
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },

      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
    }

  }
</script>


<style>

</style>


