<!--设备管理-页面-->
<template>
  <!--  使用全局样式-->
  <div class="app-container">
    <!--    <el-row>
              即布局的一行（el-row）的宽度分为24等份，通过span属性来确定每一个列(el-col)的宽度，占了24份中的几份。
              无论一个el-row中有几个el-col，其span的总值必须等于24.
              :gutter-指定各分栏之间的间隔,默认是0
              :xs表示在小分辨率浏览器上一分栏所占的份数,:xs="24"表示在小分辨率设备上一分栏占整个屏幕
              -->
    <!--    1 增删改查-->
    <el-row :gutter="20">
      <!--1.1 通过物理位置搜索-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <!--      <el-tree>

                        -->
          <el-tree
            :data="locOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
            highlight-current
            node-key="id"
            style="color: #2D2E36 ; font-family: KaiTi,monospace; font-size: large; font-weight: bolder"
          />
        </div>
      </el-col>
      <!--1.2 通过条件搜索-->
      <el-col :span="20" :xs="24">
        <!--        el-col和el-row不能绑定数据,使用el-form绑定数据
                    使用el-form表单保存查询参数
                    <el-form>
                      :model-单向绑定,用于保存表单的数据对象
                      ref指定表单对象名称
                      :inline指定是否同行
                    -->
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
          <!--          <el-form-item>
                          label设置页面显示内容
                          prop指定:rules中的属性-->
          <!--          <el-form-item label="设备编号" prop="devId">-->
          <!--            <el-input-->
          <!--              v-model="queryParams.devId"-->
          <!--              placeholder="请输入设备编号"-->
          <!--              clearable-->
          <!--              size="small"-->
          <!--              style="width: 240px"-->
          <!--              @keyup.enter.native="handleQuery"-->
          <!--            />-->
          <!--          </el-form-item>-->
          <el-form-item label="设备状态" prop="status">
            <!--          设备状态下拉框
              <el-select>
                v-model-父子组件双向绑定
                :model==v-bind:model-父传子，单向绑定
                clearable-可手动擦除
              -->
            <el-select
              v-model="queryParams.status"
              placeholder="请选择设备状态"
              clearable
              size="small"
              style="width: 240px"
            >
              <!--            <el-option>
                                key-主键；
                                label-下拉框显示的值；
                                value-传给<el-select>绑定的model的值，也就是令queryParams.status=dict.code-->
              <el-option
                v-for="dict in statusOptions"
                :key="dict.id"
                :label="dict.name"
                :value="dict.code"
              />
            </el-select>
          </el-form-item>

          <!--          添加时间条件
          type为daterange时，v-model绑定的是个数组。-->
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

          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <!--        增删 导入 导出按钮-->
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <!--            <el-button>
                                primary-蓝色按钮
                                plain-普通形状按钮
                                v-hasPermi-权限管理
                                :disabled-是否禁用，true表示禁用，false表示可用
                                -->
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['system:user:add']"
            >新增设备
            </el-button>
          </el-col>
          <!--          <el-col :span="1.5">-->
          <!--            <el-button-->
          <!--              type="success"-->
          <!--              plain-->
          <!--              icon="el-icon-edit"-->
          <!--              size="mini"-->
          <!--              :disabled="single"-->
          <!--              @click="handleUpdate"-->
          <!--              v-hasPermi="['system:user:edit']"-->
          <!--            >修改-->
          <!--            </el-button>-->
          <!--          </el-col>-->
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['system:user:del']"
            >批量删除
            </el-button>
          </el-col>

          <el-col :span="1.5">
            <el-button
              type="info"
              plain
              icon="el-icon-upload2"
              size="mini"
              @click="handleImport"
              v-hasPermi="['system:user:import']"
            >导入
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
              v-hasPermi="['system:user:export']"
            >导出
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="info"
              plain
              icon="el-icon-delete"
              size="mini"
              @click="handleRecycleBin"
              v-hasPermi="['system:user:del']"
            >回收站
            </el-button>
          </el-col>
          <!--          显示隐藏搜索框的小组件-->
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" />
        </el-row>

        <!--        设备信息显示区域
                      @selection-change配合type="selection"可以获取框选信息，存储在全局变量selection中

                      -->
        <el-table v-loading="loading" :data="devList" @selection-change="handleSelectionChange"
                  :default-sort="defaultSort" @sort-change="handleSortChange" @row-click="handleOpenDevEPara">
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column label="设备编号" align="center" prop="devId">
          </el-table-column>
          <el-table-column label="设备名称" align="center" prop="devName"/>
          <el-table-column label="设备状态" align="center" prop="status">
            <!--            <template slot-scope="scope">-->
            <!--              <el-switch-->
            <!--                v-model="scope.row.status + ''"-->
            <!--                active-value="0"-->
            <!--                inactive-value="1"-->
            <!--                @change="handleStatusChange(scope.row)"-->
            <!--              ></el-switch>-->
            <!--            </template>-->
            <!--            使用插槽来使el-table根据不同属性值显示不同内容
                            使用span的style属性单独给字体加颜色-->
            <template slot-scope="scope">
              <span v-if="scope.row.status=== 0"
                    style="color: #2BAE85;background-color: antiquewhite ; font-family: KaiTi,monospace; font-size: large; font-weight: bolder">在线</span>
              <!--#1BA784 铜绿 -->
              <span v-if="scope.row.status === 1"
                    style="color: #ED556A; background-color: antiquewhite; font-family: KaiTi,monospace; font-size: large; font-weight: bolder">离线</span>
              <!--#ED556A 山茶花红 -->
            </template>
          </el-table-column>
          <el-table-column label="设备创建时间" align="center" prop="createTime" width="160" sortable="custom"
                           :sort-orders="['descending', 'ascending']">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime)}}</span>
            </template>
          </el-table-column>
          <el-table-column label="设备修改时间" align="center" prop="updateTime" width="160" sortable="custom"
                           :sort-orders="['descending', 'ascending']">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.updateTime)}}</span>
            </template>
          </el-table-column>
          <el-table-column label="备注" align="center" prop="remark"/>
          <el-table-column
            label="操作"
            align="center"
            width="160"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click.stop="handleUpdate(scope.row)"
                v-hasPermi="['system:user:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click.stop="handleDelete(scope.row)"
                v-hasPermi="['system:user:del']"
              >删除
              </el-button>
              <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)">
                <span class="el-dropdown-link">
                  <i class="el-icon-d-arrow-right el-icon--right" @click.stop>更多</i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="handleOpenDevEPara" icon="el-icon-data-line"
                  >设备详情
                  </el-dropdown-item>
                  <el-dropdown-item command="handleOpenDevConf" icon="el-icon-set-up"
                  >设备配置
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </el-table>

        <!--        底栏分页显示框-->
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>
    </el-row>

    <!-- 2 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--        设备编号不可修改，不可为空-->
        <el-form-item v-if="form.id === undefined" label="设备编号" prop="devId">
          <el-input v-model="form.devId" placeholder="请输入设备编号" maxlength="30"/>
        </el-form-item>
        <el-form-item label="设备名称" prop="devName">
          <el-input v-model="form.devName" placeholder="请输入设备名称" maxlength="20"/>
        </el-form-item>
        <el-form-item label="所在位置" prop="deptId">
          <treeselect v-model="form.locId" :options="locOptions" :show-count="true" placeholder="请选择所属楼层"/>
        </el-form-item>
        <el-form-item v-if="form.id === undefined" label="运行状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.id"
              :label="dict.code"
            >{{dict.name}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入设备详情" type="textarea"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 3 用户导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <!--      <el-upload>
            :action表示给后端的url
            示例：/device/management/importData?updateSupport=false,不更新
            -->
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"/>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport"/>
          是否更新已经存在的设备信息<br>
          <el-link type="info" style="font-size:15px;color: #2c3e50" @click="importTemplate">设备导入模板</el-link>
          <br>
          <el-link type="info" style="font-size:15px;color: #2c3e50" @click="handleExportLocInfo">楼层信息表</el-link>

        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">
          提示：仅允许导入“xls”或“xlsx”格式文件！<br>
        </div>

      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 4、回收站对话框 -->
    <el-dialog :title="title" :visible.sync="openRecycleBin" width="996px" append-to-body>
      <el-table v-loading="loadingBin" :data="binList" @selection-change="handleBinSelectionChange"
                :default-sort="defaultSort" @sort-change="handleSortChange">
        <el-table-column type="selection" width="50" align="center"/>
        <el-table-column label="设备编号" align="center" prop="devId"/>
        <el-table-column label="设备名称" align="center" prop="devName"/>
        <el-table-column label="设备状态" align="center" prop="status">
          <template slot-scope="scope">
              <span v-if="scope.row.status=== 0"
                    style="color: #2BAE85;background-color: antiquewhite ; font-family: KaiTi,monospace; font-size: large; font-weight: bolder">在线</span>
            <!--#1BA784 铜绿 -->
            <span v-if="scope.row.status === 1"
                  style="color: #ED556A; background-color: antiquewhite; font-family: KaiTi,monospace; font-size: large; font-weight: bolder">离线</span>
            <!--#ED556A 山茶花红 -->
          </template>
        </el-table-column>
        <el-table-column label="设备创建时间" align="center" prop="createTime" width="160" sortable="custom"
                         :sort-orders="['descending', 'ascending']">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="设备删除时间" align="center" prop="updateTime" width="160" sortable="custom"
                         :sort-orders="['descending', 'ascending']">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.updateTime)}}</span>
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center" prop="remark"/>
      </el-table>
      <pagination
        v-show="binTotal>0"
        :total="binTotal"
        :page.sync="binQueryParams.pageNum"
        :limit.sync="binQueryParams.pageSize"
        @pagination="getList"
      />
      <div slot="footer" class="dialog-footer">
        <el-button type="danger" @click="handleCompleteDelete" :disabled="binMultiple">彻底删除</el-button>
        <el-button type="primary" @click="handleRestore" :disabled="binMultiple">恢 复</el-button>
        <el-button @click="cancelBin">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  // import { listUser, getUser, delUser, addUser, updateUser, resetUserPwd, changeUserStatus, importTemplate } from "@/api/system/user";
  import {listDev, addDev, getDev, updateDev, delDev, listBin, rmvDev, restoreDev} from "@/api/dev/manage";
  import {treeSelect} from "@/api/dev/loc";
  import {exportExcel} from "@/utils/zipdownload";
  import {getToken} from "@/utils/auth";

  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";

  export default {
    name: "Dev",
    components: {Treeselect},
    data() {
      return {
        // 遮罩层
        loading: true,
        //遮罩层2
        loadingBin: true,
        // 导出遮罩层
        exportLoading: false,
        // 选中记录编号数组（记录编号id，非设备编号devId）
        ids: [],
        //回收站选中记录编号数组
        binIds: [],
        // 单个可用，零个或多个禁用
        // single: true,
        // 单个或多个可用，零个禁用。
        multiple: true,
        //回收站用的
        binMultiple: true,
        // 显示搜索条件
        showSearch: true,
        // 设备总条数
        total: 0,
        // 设备表格数据
        devList: undefined,
        // 已删除设备表格数据
        binList: undefined,
        //回收站总条数
        binTotal: 0,
        // 楼层树选项
        locOptions: undefined,
        // 状态数据字典。实际接收到的是后端查询到的List，将list各个元素放到前端数组里面
        statusOptions: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        //是否显示回收站弹出层
        openRecycleBin: false,
        // 默认密码
        initPassword: undefined,
        // 日期范围
        dateRange: [],
        // 默认排序
        defaultSort: {prop: 'createTime', order: 'descending'},
        // 表单参数，用于新增设备和修改设备的弹窗
        form: {},
        // roleIds: [],
        //楼层树的默认要用到的属性：子结点和标签（标签在impl类里用setExtra添加过了，也就是当前结点的name）
        defaultProps: {
          children: "children",
          label: "label"
        },
        // 设备导入参数
        upload: {
          // 是否显示弹出层（设备导入）
          open: false,
          // 弹出层标题（设备导入）
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的设备数据
          //是：true,否：false
          updateSupport: false,
          // 设置上传的请求头部
          headers: {Authorization: "Bearer " + getToken()},
          // 上传的地址
          url: process.env.VUE_APP_BASE_API + "/device/management/importData"
        },
        // *查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          //设备编号
          devId: undefined,
          //设备状态
          status: undefined,
          //设备所处楼层编号
          locId: undefined,
        },
        //
        // *回收站的查询参数
        binQueryParams: {
          pageNum: 1,
          pageSize: 10,
          //设备编号
          devId: undefined,
          //设备状态
          status: undefined,
          //设备所处楼层编号
          locId: undefined,
        },
        // 表单校验规则
        rules: {
          devId: [
            {required: true, message: "设备编号不能为空", trigger: "blur"},
          ],
        }
      };
    },
    watch: {
      // 根据名称筛选部门树
      deptName(val) {
        this.$refs.tree.filter(val);
      }
    },
    created() {
      this.getList();
      this.getBinList();
      this.getTreeSelect();
      this.getDicts("status").then(response => {
        this.statusOptions = response.data;
        // console.log("[response]:"+this.statusOptions[0].name);
      });
    },
    methods: {
      /** 查询设备列表 */
      getList() {
        this.loading = true;
        //addDateRange需要三个参数queryParams和dateRange，第三个参数没有传进去的话就是undefined,表示应当有而实际没有
        // 结果返回的是第一个参数
        //测试：
        // let js=this.addDateRange(this.queryParams, this.dateRange);
        // console.log("经过addDateRange函数之后的查询参数变成了:"+js.params.beginTime);
        listDev(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            // console.log("response=="+response);
            this.devList = response.rows;
            this.total = response.total;
            // console.log(response.total);
            this.loading = false;
          }
        );
        this.getBinList();
      },
      /**查询已删除的设备列表**/
      getBinList() {
        this.loadingBin = true;
        listBin(this.binQueryParams).then(response => {
          this.binList = response.rows;
          this.binTotal = response.total;
          this.loadingBin = false;
        });
      },
      /** 查询部门下拉树结构 */
      getTreeSelect() {
        treeSelect().then(response => {
          // console.log('设备楼层信息树'+response);
          this.locOptions = response.data;
        });
      },
      // 筛选节点
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      // 节点单击事件
      handleNodeClick(data) {
        this.queryParams.locId = data.id;
        // console.log('locId=='+this.queryParams.locId);
        this.getList();
      },
      // 用户状态修改
      handleStatusChange(row) {
        let text = row.status === 0 ? "停用" : "启用";
        this.$confirm('确认要"' + text + '""' + row.name + '"用户吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return changeUserStatus(row.id, row.status === 0 ? 1 : 0);
        }).then(() => {
          row.status = row.status === 0 ? 1 : 0;
          this.msgSuccess(text + "成功");
        }).catch(function () {

        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      //回收站取消框选
      cancelBin() {
        this.openRecycleBin = false;
        this.binIds = [];
      },
      // 表单重置。表单form保存了设备信息，需要提交到后端，存入数据库
      //resetForm函数是一个全局函数，this.$refs[refName].resetFields();
      // this.$refs[refName]表示标签model绑定为"refName"的表单元素，然后调用el-ui的resetFields函数，
      //对表单元素的属性进行重置。下面还有一个this.resetForm("queryForm")表示重置的是查询条件表单
      reset() {
        this.form = {
          id: undefined,//数据编号，对应设备信息表的主键id。当它未定义时，说明是添加设备信息，当它非空时说明是修改设备信息
          devId: undefined,//设备编号
          locId: undefined,//设备挂载的楼层编号
          devName: undefined,//设备名称
          status: '0',//设备状态，默认可用
          delFlag: '0',//设备删除标记，默认没有删除
          remark: undefined,//设备详细信息描述
        };
        this.resetForm("form");
      },
      /** 进行搜索 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置搜索条件 */
      resetQuery() {
        this.queryParams.devId = undefined;
        this.queryParams.status = undefined;
        this.queryParams.locId = undefined;
        this.dateRange = [];
        this.$nextTick(() => {
          this.$refs.tree.setCurrentKey(null);
        });
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 排序触发事件 */
      handleSortChange(column, prop, order) {
        this.queryParams.orderByColumn = column.prop;
        this.queryParams.isAsc = column.order;
        this.getList();
      },
      // 多选框选中数据，根据框选操作实时变化
      handleSelectionChange(selection) {
        //获取所有选中项的id属性值：selection.map()
        //复习：=>箭头函数，x=>y表示函数function x(){return y}
        this.ids = selection.map(item => item.id);
        /**要求：
         * 单选时可以修改，：disabled="single"="false"
         * 不选或多选不能修改，：disabled="single"="true"
         * @type {boolean}
         */
        this.multiple = !selection.length;//单选或多选时，multiple为false，表示可以删除。不选时，multiple为true，表示不能删除。
      },

      // 回收站多选框选中数据
      handleBinSelectionChange(selection) {
        this.binIds = selection.map(item => item.id);
        this.binMultiple = !selection.length;//单选或多选时，multiple为false，表示可以删除。不选时，multiple为true，表示不能删除。
      },

      // 更多操作触发
      //case1:设备配置
      //case2:设备详情
      handleCommand(command, row) {
        switch (command) {
          case "handleOpenDevEPara":
            this.handleOpenDevEPara(row);
            break;
          case "handleOpenDevConf":
            this.handleOpenDevConf(row);
            break;
          default:
            break;
        }
      },

      /**路由跳转到'设备实时电参量'**/
      handleOpenDevEPara(row) {
        this.$router.push({path: '/device/show/' + row.devId});
      },

      /**路由跳转到'设备配置'**/
      handleOpenDevConf(row) {
        this.$router.push({path: '/device/conf/' + row.devId});
      },

      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.getTreeSelect();
        // console.log("执行了getTreeSelect函数");
        this.open = true;
        this.title = "添加设备";
      },

      /** 修改按钮操作：包含两种方式，一种是上面的、一种是右边的 */
      handleUpdate(row) {
        this.reset();
        this.getTreeSelect();
        const id = row.id;
        getDev(id).then(response => {
          this.form = response.data;//使用form对象接收后端返回的devInfo对象
          // console.log("返回的status=="+this.form.status);
          this.open = true;
          this.title = "修改设备";
        });
      },

      /**触发回收站弹窗**/
      handleRecycleBin() {
        this.getBinList();
        this.openRecycleBin = true;
        this.title = "回收站";
      },
      /**彻底删除回收站的设备**/
      handleCompleteDelete() {
        const binIds = this.binIds;
        this.$confirm('是否彻底删除编号为"' + binIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return rmvDev(binIds);
        }).then(() => {
          this.msgSuccess("清除成功");
          this.getBinList();
        });

      },
      /**恢复已经删除的设备**/
      handleRestore() {
        const binIds = this.binIds;
        if (binIds.length > 0) {
          restoreDev(binIds).then(response => {
            this.msgSuccess("恢复成功");
            this.getBinList();
            this.getList();
          }).catch(() => {
          });
        }
      },

      /** 提交按钮 */
      submitForm: function () {
        this.$refs["form"].validate(valid => {
          if (valid) {
            //修改
            if (this.form.id !== undefined) {
              updateDev(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              //添加
              addDev(this.form).then(response => {
                // this.sout(response.msg + response.code + response.data);
                //msgSuccess是一个全局方法，负责小弹窗显示
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
        //如果框选删除，ids就是this.ids，如果是点选删除，ids就是row.id。
        // 要求点选删除的优先级比框选删除的高（要求点选删除的时候，无视框选选项），所以row.id放在||前面
        const ids = row.id || this.ids;
        //删除警示弹窗（必需）
        this.$confirm('是否确认删除编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delDev(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有设备数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          // 开始导出
          exportExcel("/device/management/export", this.keepPageNum(this.queryParams));
          this.exportLoading = false;
        }).catch(() => {
        });
      },
      /**保持当前pageNum不变，使用浅拷贝**/
      keepPageNum(a) {
        let queryParams = Object.assign({}, a);
        queryParams.pageNum = 1;
        return queryParams;
      },
      /**导出楼层信息表**/
      handleExportLocInfo() {
        // 开始导出楼层信息
        exportExcel("/device/location/export", undefined);
      },

      /** 导入按钮操作 */
      handleImport() {
        this.upload.title = "设备导入";
        this.upload.open = true;
      },
      /** 下载模板操作 */
      importTemplate() {
        // importTemplate().then(response => {
        //   this.download(response.msg);
        // });
        //  这种模板类的，不需要数据库的静态资源一般由前端直接在静态资源文件夹中下载下来
        var a = document.createElement("a"); //创建一个<a></a>标签
        a.href = "/static/device-info-template.xls"; // 给a标签的href属性值加上地址，注意，这里是绝对路径，不用加 点.
        a.download = "设备导入模板-测试.xls"; //设置下载文件文件名，这里加上.xlsx指定文件类型，pdf文件就指定.fpd即可
        a.style.display = "none"; // 障眼法藏起来a标签
        document.body.appendChild(a); // 将a标签追加到文档对象中
        a.click(); // 模拟点击了a标签，会触发a标签的href的读取，浏览器就会自动下载了
        a.remove(); // 一次性的，用完就删除a标签
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
        this.$alert(response.msg, "导入结果", {dangerouslyUseHTMLString: true});
        this.getList();
      },
      // 提交上传文件
      submitFileForm() {
        console.log("updateSupport=="+this.upload.updateSupport);
        this.$refs.upload.submit();
      },

      //打印函数（仅供测试）
      sout(str) {
        console.log(str);
      },


    }
  };
</script>
<!--给el-tree设置标签-->
<style scoped>
  /*  点击时的样式 */
  .el-tree ::v-deep.el-tree-node:focus > .el-tree-node__content {
    background-color: #edf3fc !important;
    border-radius: 8px;
  }

  /* tree 的高度和宽度重新定义 */
  ::v-deep.el-tree .el-tree-node > .el-tree-node__content {
    /*width: 200px;*/
    width: auto;
    height: 30px;
  }

  /*  鼠标hover改变背景颜色 */
  .el-tree ::v-deep.el-tree-node > .el-tree-node__content:hover {
    background-color: #edf3fc !important;
    border-radius: 8px;
  }

  /*  颜色高亮 */
  ::v-deep.el-tree--highlight-current
  .el-tree-node.is-current
  > .el-tree-node__content {
    background-color: #C4D7D6 !important;
    border-radius: 8px;
  }
</style>

