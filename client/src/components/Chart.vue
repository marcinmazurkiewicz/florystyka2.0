<template>
  <div class="flex justify-center my-4">
    <div v-show="status.isDataReturned" style="width: 300px; height: 300px">
      <canvas :id="chartId"></canvas>
    </div>
    <img
      v-show="status.isPending"
      src="@/assets/img/loading-chart.svg"
      alt="Loading chart"
    />
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, PropType, toRefs, watch } from "vue";
import { getDefaultPercentChart, renderChart } from "@/utils/chartUtils";
import { ResponseStatus } from "@/types/ResponseStatus";
import {
  Chart,
  ChartConfiguration,
  ChartData,
  ChartOptions,
  ChartType
} from "chart.js";

export default defineComponent({
  name: "Chart",
  props: {
    chartId: {
      type: String,
      required: true
    },
    chartType: {
      type: String as PropType<ChartType>,
      required: true
    },
    chartData: {
      type: Object as PropType<ChartData>,
      required: true
    },
    chartOptions: {
      type: Object as PropType<ChartOptions>,
      required: false
    },
    status: Object as PropType<ResponseStatus>
  },
  setup(props) {
    const { chartId, chartType, chartData } = toRefs(props);
    let chart: Chart;
    const chartDataConfig: ChartConfiguration = {
      type: chartType.value,
      data: chartData.value,
      options: getDefaultPercentChart()
    };

    watch(
      () => props.chartData,
      chartData => {
        if (chart) {
          chart.data = chartData;
          chart.update();
        }
      }
    );

    onMounted(() => {
      chart = renderChart(chartId.value, chartDataConfig);
    });

    return {};
  }
});
</script>
