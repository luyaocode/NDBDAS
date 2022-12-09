<template>
  <div class="app-container">
    <!--    搜索功能-->
    <!--    <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">-->
    <!--      输入框-->
    <!--      <el-form-item label="设备编号" prop="devId">-->
    <!--        <el-input-->
    <!--          v-model="queryParams.name"-->
    <!--          placeholder="请输入设备编号"-->
    <!--          clearable-->
    <!--          size="small"-->
    <!--          style="width: 240px"-->
    <!--          @keyup.enter.native="handleQuery"-->
    <!--        />&lt;!&ndash;点击回车键触发handleQuery函数 &ndash;&gt;-->
    <!--      </el-form-item>-->

    <!--      &lt;!&ndash;      搜索按钮&ndash;&gt;-->
    <!--      <el-form-item>-->
    <!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
    <!--      </el-form-item>-->

    <!--      操作控制按钮-->
    <!--      <el-form-item>-->
    <!--        <el-button type="primary" size="mini" @click="handleCommand">控制按钮</el-button>-->
    <!--      </el-form-item>-->

    <!--      &lt;!&ndash;      刷新数据按钮&ndash;&gt;-->
    <!--      <el-form-item>-->
    <!--        <el-button type="primary" size="mini" @click="handleRefresh">刷新按钮</el-button>-->
    <!--      </el-form-item>-->
    <!--    </el-form>-->

    <!--历史数据显示    -->
    <!--    <el-table v-loading="loading" :data="eParaList" @selection-change="handleSelectionChange">-->
    <!--      <el-table-column type="selection"/>-->
    <!--      <el-table-column label="记录编号" prop="id"/>-->
    <!--      <el-table-column label="设备编号" prop="devId"/>-->
    <!--      <el-table-column label="设备名称" prop="devaName"/>-->
    <!--      <el-table-column label="时间" prop="createTime"/>-->
    <!--      <el-table-column label="频率" prop="freq"/>-->
    <!--      <el-table-column label="总功率" prop="totalPow"/>-->
    <!--      <el-table-column label="A相电压" prop="voltA"/>-->
    <!--      <el-table-column label="B相电压" prop="voltB"/>-->
    <!--      <el-table-column label="C相电压" prop="voltC"/>-->
    <!--      <el-table-column label="A相电流" prop="currA"/>-->
    <!--      <el-table-column label="B相电流" prop="currB"/>-->
    <!--      <el-table-column label="C相电流" prop="currC"/>-->
    <!--      <el-table-column label="A相有功" prop="actiPowA"/>-->
    <!--      <el-table-column label="B相有功" prop="actiPowB"/>-->
    <!--      <el-table-column label="C相有功" prop="actiPowC"/>-->
    <!--      <el-table-column label="A相无功" prop="wattPowA"/>-->
    <!--      <el-table-column label="B相无功" prop="wattPowB"/>-->
    <!--      <el-table-column label="C相无功" prop="wattPowC"/>-->
    <!--      <el-table-column label="A相视在" prop="apprPowA"/>-->
    <!--      <el-table-column label="B相视在" prop="apprPowB"/>-->
    <!--      <el-table-column label="C相视在" prop="apprPowC"/>-->
    <!--      <el-table-column label="A相电压相角" prop="voltPhaA"/>-->
    <!--      <el-table-column label="B相电压相角" prop="voltPhaB"/>-->
    <!--      <el-table-column label="C相电压相角" prop="voltPhaC"/>-->
    <!--      <el-table-column label="A相电流相角" prop="currPhaA"/>-->
    <!--      <el-table-column label="B相电流相角" prop="currPhaB"/>-->
    <!--      <el-table-column label="C相电流相角" prop="currPhaC"/>-->

    <!--    </el-table>-->

    <!--    数据显示-当前电参量-->
    <el-card v-loading="loading" :data="eParas">
      <el-col :xs="24" :sm="24" :md="6" :lg="6" :xl="6" style="margin-bottom: 10px">
<!--        <el-button type="primary" size="mini" @click="handleRefresh">刷新</el-button>-->
        <div class="footer">编号：{{eParas.id}}</div>
        <div class="footer">设备编号：{{eParas.devId}}</div>
        <div class="footer">时间：{{eParas.createTime}}</div>
        <div class="footer">频率：{{eParas.freq}}Hz</div>
        <div class="footer">总有功功率：{{eParas.actiPowTotal}}</div>
        <div class="footer">总无功功率：{{eParas.wattPowTotal}}</div>
        <div class="footer">总视在功率：{{eParas.apprPowTotal}}</div>

      </el-col>
      <el-col :xs="24" :sm="24" :md="6" :lg="6" :xl="6" style="margin-bottom: 10px">
        <div class="title">A相</div>
        <div class="footer">电压: {{eParas.voltA}}</div>
        <div class="footer">电流: {{eParas.currA}}</div>
        <div class="footer">有功功率： {{eParas.actiPowA}}</div>
        <div class="footer">无功功率： {{eParas.wattPowA}}</div>
        <div class="footer">视在功率： {{eParas.apprPowA}}</div>
        <div class="footer">功率因数：{{eParas.powFacA}}</div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="6" :lg="6" :xl="6" style="margin-bottom: 10px">
        <div class="title">B相</div>
        <div class="footer">电压: {{eParas.voltB}}</div>
        <div class="footer">电流: {{eParas.currB}}</div>
        <div class="footer">有功功率： {{eParas.actiPowB}}</div>
        <div class="footer">无功功率： {{eParas.wattPowB}}</div>
        <div class="footer">视在功率： {{eParas.apprPowB}}</div>
        <div class="footer">功率因数：{{eParas.powFacB}}</div>
      </el-col>

      <el-col :xs="24" :sm="24" :md="6" :lg="6" :xl="6" style="margin-bottom: 10px">
        <div class="title">C相</div>
        <div class="footer">电压: {{eParas.voltC}}</div>
        <div class="footer">电流: {{eParas.currC}}</div>
        <div class="footer">有功功率： {{eParas.actiPowC}}</div>
        <div class="footer">无功功率： {{eParas.wattPowC}}</div>
        <div class="footer">视在功率： {{eParas.apprPowC}}</div>
        <div class="footer">功率因数：{{eParas.powFacC}}</div>
      </el-col>
    </el-card>

<!--    <el-button type="primary" size="mini" @click="handleCommand">控制按钮测试</el-button>-->
    <!--    折线图显示-->
    <div>
      <el-row :gutter="6">
        <!--          左侧：电压监控列-->
        <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" style="margin-bottom: 10px">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold;color: #666;font-size: 15px">电压监控</span>
            </div>
            <div>
              <v-chart :options="voltInfo"/>
            </div>
          </el-card>
        </el-col>
        <!--          右侧：电流监控列-->
        <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" style="margin-bottom: 10px">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold;color: #666;font-size: 15px">电流监控</span>
            </div>
            <div>
              <v-chart :options="currInfo"/>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!--       分页组件-->
    <!--    <pagination-->
    <!--      v-show="total>0"-->
    <!--      :total="total"-->
    <!--      :page.sync="queryParams.pageNum"-->
    <!--      :limit.sync="queryParams.pageSize"-->
    <!--      @pagination="ListEPara"-->
    <!--    />-->
  </div>
</template>

<script>
  import {getEParas} from "@/api/device/dev";
  import {ListEParas} from "@/api/device/dev";
  import {readHoldingRegister} from "@/api/device/dev";

  //画折线图
  import ECharts from 'vue-echarts'
  import 'echarts/lib/chart/line'

  //测试
  import {testAddEPara} from "@/api/device/dev";

  export default {
    name: "DeviceEParaInfo",

    components: {
      'v-chart': ECharts
    },
    data() {
      return {
        //获取/device/show/:id中的id，也就是devId
        devId:this.$route.params.id,
        //定时器
        alarm: null,
        //控制指令
        command: [],
        //后端信号返回值, 默认false
        signal: false,

        // 遮罩层,false表示未在加载状态，true表示在加载状态，会有遮罩层
        loading: false,
        // 状态数据字典
        statusOptions: [],
        // 日期范围
        dateRange: [],
        // 历史电参量
        data: {},
        //  当前电参量
        eParas: {},
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
        //电压信息
        voltInfo: {
          tooltip: {
            trigger: 'axis'
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: []
          },
          yAxis: {
            type: 'value',
            min: 0,
            max: 300,
            interval: 20
          },
          series: [
            {
              name:'A相电压',
              data: [],
              type: 'line',
              // areaStyle: {
              //   normal: {
              //     // color: 'rgb(32, 160, 255)' // 改变区域颜色
              //   }
              // },
              // itemStyle: {
              //   normal: {
              //     // color: '#6fbae1',
              //     lineStyle: {
              //       // color: '#6fbae1' // 改变折线颜色
              //     }
              //   }
              // }
            },
            {
              name:'B相电压',
              data: [],
              type: 'line',
              // areaStyle: {
              //   normal: {
              //     // color: 'rgb(32, 160, 255)' // 改变区域颜色
              //   }
              // },
              // itemStyle: {
              //   normal: {
              //     // color: '#6fbae1',
              //     lineStyle: {
              //       // color: '#6fbae1' // 改变折线颜色
              //     }
              //   }
              // }
            },

            {
              name:'C相电压',
              data: [],
              type: 'line',
              // areaStyle: {
              //   normal: {
              //     color: 'rgb(32, 160, 255)' // 改变区域颜色
              //   }
              // },
              // itemStyle: {
              //   normal: {
              //     // color: '#6fbae1',
              //     lineStyle: {
              //       // color: '#6fbae1' // 改变折线颜色
              //     }
              //   }
              // }
            },

          ]
        },

        //电流信息
        currInfo: {
          tooltip: {
            trigger: 'axis'
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: []
          },
          yAxis: {
            type: 'value',
            min: 0,
            max: 50,
            interval: 10
          },
          series: [
            {
              name: 'A相电流',
              data: [],
              type: 'line',
              // areaStyle: {
              //   normal: {
              //     // color: 'rgb(32, 160, 255)' // 改变区域颜色
              //   }
              // },
              // itemStyle: {
              //   normal: {
              //     // color: '#6fbae1',
              //     lineStyle: {
              //       // color: '#6fbae1' // 改变折线颜色
              //     }
              //   }
              // }
            },

            {
              name: 'B相电流',
              data: [],
              type: 'line',
              // areaStyle: {
              //   normal: {
              //     // color: 'rgb(32, 160, 255)' // 改变区域颜色
              //   }
              // },
              // itemStyle: {
              //   normal: {
              //     // color: '#6fbae1',
              //     lineStyle: {
              //       // color: '#6fbae1' // 改变折线颜色
              //     }
              //   }
              // }
            },

            {
              name: 'C相电流',
              data: [],
              type: 'line',
              // areaStyle: {
              //   normal: {
              //     color: 'rgb(32, 160, 255)' // 改变区域颜色
              //   }
              // },
              // itemStyle: {
              //   normal: {
              //     // color: '#6fbae1',
              //     lineStyle: {
              //       // color: '#6fbae1' // 改变折线颜色
              //     }
              //   }
              // }
            },

          ]
        },
      }
    },

    created() {

      //查询当前电参量,给eParas赋值
      this.getEParas(this.devId);
      // this.getDicts("status").then(response => {
      //   this.statusOptions = response.data;
      // });
      //查询历史电参量,给eParaList赋值
      // this.ListEPara();

      //  设置定时器，定为0.5秒后开始第一次读数据，以后每5秒读一次数据
      /**
       * =>{} 是匿名函数的写法，(a,b)=>{}有参匿名函数  ()=>{}无参匿名函数
       * @type {number}
       */
      this.alarm = window.setInterval(() => {
        setTimeout(() => {
          this.getEParas(this.devId);
        }, 500)
      }, 5000);

      //测试用，模拟电参量实时变化，暂定为每5s写一条数据
      window.setInterval(() => {
        setTimeout(() => {
          this.testAdd();
        })
      }, 5000);


    },
    methods: {
      /** 获取当前电参量 */
      getEParas(devId) {
        //开始加载
        // this.loading = true;
        // console.log("开始加载,"+devId);
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
        getEParas(devId).then(
          response => {
            //查看后端返回的是什么东西:
            // console.log(response);
            this.eParas = response;
            this.loading = false;
            // this.total = response.total;

            // console.log(this.eParas);
            if (this.voltInfo.xAxis.data.length >= 8) {
              //如果历史电参量条数大于等于8，就删除第一条记录，结合后面的循环形成动态效果
              this.voltInfo.xAxis.data.shift();
              this.voltInfo.series[0].data.shift();
              this.voltInfo.series[1].data.shift();
              this.voltInfo.series[2].data.shift();
            }

            if (this.currInfo.xAxis.data.length >= 8) {
              //  电流赋值
              this.currInfo.xAxis.data.shift();
              this.currInfo.series[0].data.shift();
              this.currInfo.series[1].data.shift();
              this.currInfo.series[2].data.shift();
            }
            //电压：截取时间存到x轴，舍弃日期
            if(this.voltInfo.xAxis.data.at(-1)!==this.eParas.createTime.slice(11)){
              this.voltInfo.xAxis.data.push(this.eParas.createTime.slice(11));
            }

            // console.log(parseFloat(this.eParas.voltA).toFixed(2));
            this.voltInfo.series[0].data.push(parseFloat(this.eParas.voltA));
            this.voltInfo.series[1].data.push(parseFloat(this.eParas.voltB));
            this.voltInfo.series[2].data.push(parseFloat(this.eParas.voltC));

            //电流：截取时间存到x轴，舍弃日期
            //同上
            if(this.currInfo.xAxis.data.at(-1)!==this.eParas.createTime.slice(11)){
              this.currInfo.xAxis.data.push(this.eParas.createTime.slice(11));
            }
            // console.log(parseFloat(this.eParas.currA).toFixed(2));
            this.currInfo.series[0].data.push(parseFloat(this.eParas.currA));
            this.currInfo.series[1].data.push(parseFloat(this.eParas.currB));
            this.currInfo.series[2].data.push(parseFloat(this.eParas.currC));


          }
        );
      },
      /**获取历史电参量*/
      // ListEPara() {
      //   ListEParas().then(
      //     response =>{
      //       //返回历史电参量：对象数组
      //       console.log("打印response:"+response);
      //       this.eParaList=response;
      //       if(response.length>=8) {
      //         //如果历史电参量条数大于等于8，就删除第一条记录，结合后面的循环形成动态效果
      //         this.voltInfo.xAxis.data.shift();
      //         this.voltInfo.series[0].data.shift();
      //       }
      //       console.log(response.length);
      //       for(var i=0;i<response.length;i++){
      //         //截取时间存到x轴，舍弃日期
      //         console.log(this.eParaList[i].createTime.slice(11));
      //         this.voltInfo.xAxis.data.push(this.eParaList[i].createTime.slice(11));
      //         console.log(parseFloat(this.eParaList[i].voltA).toFixed(2));
      //         this.voltInfo.series[0].data.push(parseFloat(this.eParaList[i].voltA));
      //       }
      //     }
      //   )
      // },

      testAdd() {
        testAddEPara(this.devId);
        console.log(this.devId);
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getEParas(this.devId);
      },

      //控制按钮
      handleCommand() {
        //在这里定义命令帧的格式，传给后端
        this.command = "0808 0000 0006 01 03 9c41 0001";
        readHoldingRegister(this.command).then(
          response => {
            console.log(response);
            alert(response);
            //操作控制完成后刷新电参量
            this.getEParas();
          }
        )
      },

      //刷新数据按钮
      handleRefresh() {
        this.getEParas(this.devId);
      },

      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length != 1
        this.multiple = !selection.length
      },
    }

  }
</script>


<style>

</style>


