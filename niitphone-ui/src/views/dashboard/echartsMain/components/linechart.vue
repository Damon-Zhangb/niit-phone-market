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
      default: '900px'
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
      axios.get('http://localhost:10086/top/getType')
        .then(function(response) {
          if (response.data.code === 0) {
            _this.list = response.data.data
            for (let i = 0; i <= _this.list.length - 1; i++) {
              _this.do[i] = { value: response.data.data[i]['phoneNum'], name: response.data.data[i]['phoneType'] }
            }
            _this.maxNum = _this.list[0].phoneNum * 1.5
            _this.minNum = _this.list[_this.list.length - 1].phoneNum
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
        title: {
          text: 'Customized Pie',
          left: 'center',
          top: 20,
          textStyle: {
            color: '#ccc'
          }
        },

        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },

        visualMap: {
          show: false,
          min: this.minNum,
          max: this.maxNum,
          inRange: {
            colorLightness: [0.6, 1]
          }
        },
        series: [
          {
            name: '访问来源',
            type: 'pie',
            radius: '77%',
            center: ['50%', '50%'],
            data: this.do,
            roseType: 'radius',
            label: {
              color: 'rgb(15,140,198)'
            },
            labelLine: {
              lineStyle: {
                color: 'rgb(4,142,160)'
              },
              smooth: 0.2,
              length: 5,
              length2: 10
            },
            itemStyle: {
              color: '#1d17d7',
              shadowBlur: 200,
              shadowColor: 'rgba(6,81,232,0.5)'
            },

            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function(idx) {
              return Math.random() * 200
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
