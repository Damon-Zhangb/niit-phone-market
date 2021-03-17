<template>
  <div class="dashboard-editor-container">
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <barchart />
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <linechart />
    </el-row>
    <div />
  </div>
</template>

<script>
import barchart from './components/barchart'
import linechart from '@/views/dashboard/echartsMain/components/linechart'
export default {
  name: 'Echartsmain',
  components: { barchart, linechart }
}
</script>

<style  lang="scss" scoped>
  .dashboard-editor-container {
    padding: 32px;
    background-color: rgb(240, 242, 245);
    position: relative;
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
  }
</style>
