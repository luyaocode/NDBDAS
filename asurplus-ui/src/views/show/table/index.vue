<template>
  <div style="background: #1ab394">
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="100 0 300 400" style="width:600px;height:350px;background: #f1fa8c">
      <g id="地板">
        <rect class="floor" x="0.5" y="0.5" width="529" height="333"/>
        <path d="M915,221V553H387V221H915m1-1H386V554H916V220Z" transform="translate(-386 -220)"/>
      </g>
      <g id="配电柜">
        <router-link to="/show/chart">
        <rect class="box" x="0.5" y="0.5" width="65" height="65"/>
        </router-link>
        <path d="M451,221v64H387V221h64m1-1H386v66h66V220Z" transform="translate(-386 -220)"/>
        <rect class="box" x="464.5" y="0.5" width="65" height="65"/>
        <path d="M915,221v64H851V221h64m1-1H850v66h66V220Z" transform="translate(-386 -220)"/>
        <rect class="box" x="0.5" y="268.5" width="65" height="65"/>
        <path d="M451,489v64H387V489h64m1-1H386v66h66V488Z" transform="translate(-386 -220)"/>
        <rect class="box" x="464.5" y="268.5" width="65" height="65"/>
        <path d="M915,489v64H851V489h64m1-1H850v66h66V488Z" transform="translate(-386 -220)"/>
      </g>
      <g id="控制开关">
        <router-link to="/operation">
        <circle class="control" cx="32.5" cy="33.5" r="20"/>
        </router-link>
        <path
          d="M418.5,234A19.5,19.5,0,1,1,399,253.5,19.53,19.53,0,0,1,418.5,234m0-1h0A20.5,20.5,0,0,0,398,253.5h0A20.5,20.5,0,0,0,418.5,274h0A20.5,20.5,0,0,0,439,253.5h0A20.5,20.5,0,0,0,418.5,233Z"
          transform="translate(-386 -220)"/>
        <circle class="control" cx="497.5" cy="33.5" r="20"/>
        <path
          d="M883.5,234A19.5,19.5,0,1,1,864,253.5,19.53,19.53,0,0,1,883.5,234m0-1A20.5,20.5,0,0,0,863,253.5h0a20.5,20.5,0,0,0,41,0h0A20.5,20.5,0,0,0,883.5,233Z"
          transform="translate(-386 -220)"/>
        <circle class="control" cx="33.5" cy="301.5" r="20"/>
        <path
          d="M419.5,502A19.5,19.5,0,1,1,400,521.5,19.53,19.53,0,0,1,419.5,502m0-1h0a20.5,20.5,0,0,0,0,41h0a20.5,20.5,0,0,0,0-41Z"
          transform="translate(-386 -220)"/>
        <circle class="control" cx="497.5" cy="301.5" r="20"/>
        <path
          d="M883.5,502A19.5,19.5,0,1,1,864,521.5,19.53,19.53,0,0,1,883.5,502m0-1A20.5,20.5,0,1,0,904,521.5,20.5,20.5,0,0,0,883.5,501Z"
          transform="translate(-386 -220)"/>
      </g>
    </svg>

    <span>编号：</span>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="phaseSequence"
        label="相序"
        width="120">
      </el-table-column>
      <el-table-column
        prop="voltage"
        label="电压"
        width="120">
      </el-table-column>
      <el-table-column
        prop="current"
        label="电流"
        width="120">
      </el-table-column>
      <el-table-column
        prop="activePower"
        label="有功功率"
        width="120">
      </el-table-column>
      <el-table-column
        prop="reactivePower"
        label="无功功率"
        width="120">
      </el-table-column>
      <el-table-column
        prop="apparentPower"
        label="视在功率"
        width="120">
      </el-table-column>
      <el-table-column
        prop="voltagePhaseAngle"
        label="电压相角"
        width="120">
      </el-table-column>
      <el-table-column
        prop="currentPhaseAngle"
        label="电流相角"
        width="120">
      </el-table-column>
      <el-table-column
        prop="totalPower"
        label="总功率"
        width="120">
      </el-table-column>
    </el-table>
  </div>


</template>

<script>
  export default {
    data() {
      return {
        tableData: [{
          phaseSequence: 'A',
          voltage:0,
          current:0,
          activePower:0,
          reactivePower:0,
          apparentPower:0,
          voltagePhaseAngle:0,
          currentPhaseAngle:0,
          totalPower:0,
        },{
          phaseSequence: 'B',
          voltage:0,
          current:0,
          activePower:0,
          reactivePower:0,
          apparentPower:0,
          voltagePhaseAngle:0,
          currentPhaseAngle:0,
          totalPower:0,
        },{
          phaseSequence: 'C',
          voltage:0,
          current:0,
          activePower:0,
          reactivePower:0,
          apparentPower:0,
          voltagePhaseAngle:0,
          currentPhaseAngle:0,
          totalPower:0,
        }
        ]
      }
    }
  }
</script>

<style>
  .floor {
    fill: #c7b299;
  }

  .box {
    fill: #7ac943;
  }
  .control{
    fill:white;
  }

</style>
