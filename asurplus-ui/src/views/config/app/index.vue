<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="APP名称" prop="appName">
        <el-input
          v-model="queryParams.appName"
          placeholder="请输入APP名称"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="版本名称" prop="versionName">
        <el-input
          v-model="queryParams.versionName"
          placeholder="请输入版本名称"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="版本号" prop="versionCode">
        <el-input
          v-model="queryParams.versionCode"
          placeholder="请输入版本号"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="强制更新" prop="isForce">
        <el-select v-model="queryParams.isForce" placeholder="强制更新" clearable size="small">
          <el-option
            v-for="dict in isForceOptions"
            :key="dict.id"
            :label="dict.name"
            :value="dict.code"
          />
        </el-select>
      </el-form-item>
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
          v-hasPermi="['config:app:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['config:app:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['config:app:del']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['config:app:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="appList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="版本编号" align="center" prop="id" />
      <el-table-column label="APP名称" align="center" prop="appName" />
      <el-table-column label="版本名称" align="center" prop="versionName" />
      <el-table-column label="版本号" align="center" prop="versionCode" />
      <el-table-column label="版本描述" align="center" prop="versionInfo" />
      <el-table-column label="强制更新" align="center" prop="isForce">
        <template slot-scope="scope">
          <dict-tag :options="isForceOptions" :value="scope.row.isForce + ''"/>
        </template>
      </el-table-column>
      <el-table-column label="IOS下载" align="center" prop="ios" />
      <el-table-column label="Android下载" align="center" prop="android" />
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
            v-hasPermi="['config:app:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['config:app:del']"
          >删除</el-button>
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

    <!-- 添加或修改app版本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="APP名称" prop="appName">
          <el-input v-model="form.appName" placeholder="请输入APP名称" />
        </el-form-item>
        <el-form-item label="版本名称" prop="versionName">
          <el-input v-model="form.versionName" placeholder="请输入版本名称" />
        </el-form-item>
        <el-form-item label="版本号" prop="versionCode">
          <el-input v-model="form.versionCode" placeholder="请输入版本号" />
        </el-form-item>
        <el-form-item label="版本描述" prop="versionInfo">
          <el-input type="textarea" v-model="form.versionInfo" placeholder="请输入版本描述" />
        </el-form-item>
        <el-form-item label="强制更新" prop="isForce">
          <el-radio-group v-model="form.isForce">
            <el-radio
              v-for="dict in isForceOptions"
              :key="dict.id"
              :label="parseInt(dict.code)"
            >{{dict.name}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="IOS应用商店链接" prop="ios">
          <el-input v-model="form.ios" placeholder="请输入IOS应用商店链接" />
        </el-form-item>
        <el-form-item label="Android下载链接" prop="android">
          <el-input v-model="form.android" placeholder="请输入Android下载链接" />
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
import { listApp, getApp, delApp, addApp, updateApp, exportApp } from "@/api/config/app";
import {exportExcel} from "@/utils/zipdownload";

export default {
  name: "App",
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
      // app版本信息表格数据
      appList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 强制更新数据字典
      isForceOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        appName: undefined,
        versionName: undefined,
        versionCode: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        appName: [
          { required: true, message: "APP名称不能为空", trigger: "blur" }
        ],
        versionName: [
          { required: true, message: "版本名称不能为空", trigger: "blur" }
        ],
        versionCode: [
          { required: true, message: "版本号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("is_force").then(response => {
      this.isForceOptions = response.data;
    });
  },
  methods: {
    /** 查询app版本信息列表 */
    getList() {
      this.loading = true;
      listApp(this.queryParams).then(response => {
        this.appList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        appName: undefined,
        versionName: undefined,
        versionCode: undefined,
        versionInfo: undefined,
        isForce: 0,
        ios: undefined,
        android: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加App版本信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApp(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改App版本信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateApp(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addApp(this.form).then(response => {
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
      this.$confirm('是否确认删除App版本信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delApp(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.$confirm('是否确认导出所有app版本信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
        this.exportLoading = true;
        // 开始导出
        exportExcel("/config/sys-app/export", this.queryParams);
        this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>

<style>

</style>
