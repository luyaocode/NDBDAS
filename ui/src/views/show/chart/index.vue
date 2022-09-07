<template>
  <div style="height: 100%" class="app-container">
    <div>
      <el-card class="box-card">
        <div style="color: #666;font-size: 13px;">
          <svg-icon icon-class="system" style="margin-right: 5px"/>
          <span>
            服务器系统：{{ data.sys.os }}
          </span>
          <span>
            IP：{{ data.sys.ip }}
          </span>
          <span>
            系统已不间断运行：{{ data.sys.day }}
          </span>
          <i class="el-icon-refresh" style="margin-left: 40px" @click="init"/>
        </div>
      </el-card>
    </div>

    <div>
      <el-row :gutter="6">
        <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" style="margin-bottom: 10px">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold;color: #666;font-size: 15px">实时电压监控</span>
            </div>
            <div>
              <v-chart :options="cpuInfo"/>
            </div>
          </el-card>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" style="margin-bottom: 10px">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold;color: #666;font-size: 15px">实时电流监控</span>
            </div>
            <div>
              <v-chart :options="memoryInfo"/>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

  </div>


</template>

<script>
  import {getServer} from "@/api/monitor/server";
  import ECharts from 'vue-echarts'
  import 'echarts/lib/chart/line'
  import 'echarts/lib/component/polar'

  export default {
    name: 'ServerMonitor',
    components: {
      'v-chart': ECharts
    },
    data() {
      return {
        monitor: null,
        data: {},
        cpuInfo: {
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
            max: 100,
            interval: 20
          },
          series: [{
            data: [],
            type: 'line',
            areaStyle: {
              normal: {
                color: 'rgb(32, 160, 255)' // 改变区域颜色
              }
            },
            itemStyle: {
              normal: {
                color: '#6fbae1',
                lineStyle: {
                  color: '#6fbae1' // 改变折线颜色
                }
              }
            }
          }]
        },
        memoryInfo: {
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
            max: 100,
            interval: 20
          },
          series: [{
            data: [],
            type: 'line',
            areaStyle: {
              normal: {
                color: 'rgb(32, 160, 255)' // 改变区域颜色
              }
            },
            itemStyle: {
              normal: {
                color: '#6fbae1',
                lineStyle: {
                  color: '#6fbae1' // 改变折线颜色
                }
              }
            }
          }]
        }
      }
    },
    created() {
      this.init()
      this.openLoading();
      this.monitor = window.setInterval(() => {
        setTimeout(() => {
          this.init()
        }, 2)
      }, 3500)
    },
    destroyed() {
      clearInterval(this.monitor)
    },
    methods: {
      init() {
        getServer().then(data => {
          this.data = data
          this.loading.close();
          if (this.cpuInfo.xAxis.data.length >= 8) {
            this.cpuInfo.xAxis.data.shift()
            this.memoryInfo.xAxis.data.shift()
            this.cpuInfo.series[0].data.shift()
            this.memoryInfo.series[0].data.shift()
          }
          this.cpuInfo.xAxis.data.push(data.time)
          this.memoryInfo.xAxis.data.push(data.time)
          this.cpuInfo.series[0].data.push(parseFloat(data.cpu.used))
          this.memoryInfo.series[0].data.push(parseFloat(data.memory.usageRate))
        })
      },
      // 打开加载层
      openLoading() {
        this.loading = this.$loading({
          lock: true,
          text: "拼命读取中",
          spinner: "el-icon-loading",
          background: "rgba(0, 0, 0, 0.7)",
        });
      },
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  ::v-deep .box-card {
    margin-bottom: 5px;

    span {
      margin-right: 28px;
    }

    .el-icon-refresh {
      margin-right: 10px;
      float: right;
      cursor: pointer;
    }
  }

  .cpu, .memory, .swap, .disk {
    width: 20%;
    float: left;
    padding-bottom: 20px;
    margin-right: 5%;
  }

  .title {
    text-align: center;
    font-size: 15px;
    font-weight: 500;
    color: #999;
    margin-bottom: 16px;
  }

  .echarts {
    height: 390px!important;
  }

  .footer {
    text-align: center;
    font-size: 15px;
    font-weight: 500;
    color: #999;
    margin-top: -5px;
    margin-bottom: 10px;
  }

  .content {
    text-align: center;
    margin-top: 5px;
    margin-bottom: 5px;
  }
</style>

