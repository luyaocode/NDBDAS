<template>
  <div class="app-container">
    <!--    :model  是v-bind:model单向绑定
            v-model 双向绑定
            ref     用于注册dom供引用
            v-show  是否显示
            -->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="网关名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入网关名称"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="网关ip" prop="ip">
        <el-input
          v-model="queryParams.ip"
          placeholder="请输入网关ip"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系统内置" prop="type">
        <el-select v-model="queryParams.type" placeholder="系统内置" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.id"
            :label="dict.name"
            :value="dict.code"
          />
        </el-select>
      </el-form-item>
      <!--      时间搜索框-->
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        />
      </el-form-item>
      <!--      -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:config:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:config:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:config:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['system:config:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="gatewayList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="网关主键" align="center" prop="id"/>
      <el-table-column label="网关名称" align="center" prop="name" :show-overflow-tooltip="true"/>
      <el-table-column label="网关ip地址" align="center" prop="ip" :show-overflow-tooltip="true"/>
      <el-table-column label="网关端口号" align="center" prop="port"/>
      <el-table-column label="系统内置" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="typeOptions" :value="scope.row.type + ''"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:config:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:config:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 二级弹窗-添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="网关名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入新网关名称"/>
        </el-form-item>
        <el-form-item label="网关ip地址" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入新ip地址"/>
        </el-form-item>
        <el-form-item label="网关端口号" prop="port">
          <el-input v-model="form.port" placeholder="请输入新端口号"/>
        </el-form-item>
        <el-form-item label="系统内置" prop="type">
          <el-radio-group v-model="form.type">
            <el-radio
              v-for="dict in typeOptions"
              :key="dict.id"
              :label="parseInt(dict.code)"
            >{{dict.name}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {
    listConfig,
    getConfig,
    delConfig,
    addConfig,
    updateConfig,
    exportConfig,
    refreshCache
  } from "@/api/config/gateway";
  import {exportExcel} from "@/utils/zipdownload";

  export default {
    name: "Config",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 导出遮罩层
        exportLoading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // *参数表格数据
        gatewayList: [],
        // 弹出层标题
        title: "",

        // 是否显示弹出层,默认不弹出
        open: false,
        // 类型数据字典
        typeOptions: [],
        // 日期范围
        dateRange: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          //网关名称
          name: undefined,
          //网关ip
          ip: undefined,
          type: undefined,
          paramKey: undefined

        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          name: [
            {required: true, message: "网关名称不能为空", trigger: "blur"}
          ],
          ip: [
            {required: true, message: "网关ip不能为空", trigger: "blur"}
          ],
          port: [
            {required: true, message: "网关端口号不能为空", trigger: "blur"}
          ]
        }
      };
    },
    created() {
      console.log("开始获取list");
      this.getList();
      this.getDicts("is_builtIn").then(response => {
        this.typeOptions = response.data;
      });
    },
    methods: {
      /** 查询参数列表 */
      getList() {
        this.loading = true;

        listConfig(this.addDateRange(this.queryParams, this.dateRange)).then(
          response => {
            //
            // console.log("log[0]" + response);
            this.gatewayList = response.rows;
            this.total = response.total;
            // console.log("log[1]" + this.gatewayList);
            // console.log("log[2]" + this.total);
            this.loading = false;
          }
        );
      },
      // 参数系统内置字典翻译
      typeFormat(row, column) {
        return this.selectDictLabel(this.typeOptions, row.type);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
          name: undefined,
          ip: undefined,
          type: 0,
          remark: undefined,
          // paramKey: undefined,
          // paramValue: undefined

        };
        this.resetForm("form");//resetForm()来自main.js定义的全局变量，来自asurplus.js
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.dateRange = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },

      /** 新增按钮操作 */
      handleAdd() {
        //1、重置表单
        this.reset();
        //2、弹窗打开
        this.open = true;
        //3、弹窗标题
        this.title = "增加网关";
      },

      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length != 1
        this.multiple = !selection.length
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getConfig(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改参数";
        });
      },
      /** 提交按钮 */
      submitForm: function () {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateConfig(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addConfig(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$confirm('是否确认删除参数编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delConfig(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.$confirm('是否确认导出所有参数数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          // 开始导出
          exportExcel("/config/sys-param/export", this.queryParams);
          this.exportLoading = false;
        }).catch(() => {
        });
      },
      /** 刷新缓存按钮操作 */
      handleRefreshCache() {
        refreshCache().then(() => {
          this.msgSuccess("刷新成功");
        });
      }
    }
  };
</script>
