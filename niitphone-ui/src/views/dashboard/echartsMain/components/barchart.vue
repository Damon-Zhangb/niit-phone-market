<template>
  <div id="box" :class="className" :style="{height:height,width:width}" />
</template>

<script>
import axios from 'axios'
import echarts from 'echarts'
require('echarts/theme/shine')
export default {
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '1200px'
    },
    height: {
      type: String,
      default: '500px'
    }
  },
  data() {
    return {
      chart: null,
      list: [],
      do: [],
      minNum: 0,
      maxNum: 0
    }
  },
  watch: {
    list() {
      this.timer()
    }
  },
  mounted() {
    this.initChart()
  },
  destroyed() {
    clearTimeout(this.timer)
  },
  methods: {
    getData() {
      const _this = this
      axios.get('http://localhost:10086/top/getRedis')
        .then(function(response) {
          if (response.data.code === 0) {
            _this.list = response.data.data
            if (_this.list.length >= 9) {
              _this.do = [
                ['amount', 'product'],
                [_this.list[8].number, _this.list[8].phoneName],
                [_this.list[7].number, _this.list[7].phoneName],
                [_this.list[6].number, _this.list[6].phoneName],
                [_this.list[5].number, _this.list[5].phoneName],
                [_this.list[4].number, _this.list[4].phoneName],
                [_this.list[3].number, _this.list[3].phoneName],
                [_this.list[2].number, _this.list[2].phoneName],
                [_this.list[1].number, _this.list[1].phoneName],
                [_this.list[0].number, _this.list[0].phoneName]
              ]
              _this.minNum = _this.list[8].number
            } else {
              _this.do[0] = ['amount', 'product']
              for (let i = 0; i <= _this.list.length - 1; i++) {
                _this.do[i + 1] = [_this.list[_this.list.length - 1 - i].number, _this.list[_this.list.length - 1 - i].phoneName]
              }
              _this.minNum = _this.list[_this.list.length - 1].number
            }

            _this.maxNum = _this.list[0].number
          }
        })
    },
    timer() {
      return setTimeout(() => {
        this.initChart()
      }, 500)
    },
    initChart() {
      this.getData()

      this.chart = echarts.init(this.$el, 'shine')
      this.chart.setOption({
        dataset: {
          source: this.do
        },
        grid: { containLabel: true },
        xAxis: { name: 'amount' },
        yAxis: { type: 'category' },
        visualMap: {
          orient: 'horizontal',
          left: 'center',
          min: this.minNum,
          max: this.maxNum,
          text: ['High Score', 'Low Score'],
          // Map the score column to color
          dimension: 0,
          inRange: {
            color: ['#06ddf1', '#081cd5']
          }
        },
        series: [
          {
            type: 'bar',
            encode: {
              // Map the "amount" column to X axis.
              x: 'amount',
              // Map the "product" column to Y axis
              y: 'product'
            }
          }
        ]
      })
    }
  }
}
</script>

<style scoped>

</style>
