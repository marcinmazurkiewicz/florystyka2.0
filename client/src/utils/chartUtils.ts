import {
  ArcElement,
  BarController,
  BarElement,
  BubbleController,
  CategoryScale,
  Chart,
  ChartConfiguration,
  ChartOptions,
  Decimation,
  DoughnutController,
  Filler,
  Legend,
  LinearScale,
  LineController,
  LineElement,
  LogarithmicScale,
  PieController,
  PointElement,
  PolarAreaController,
  RadarController,
  RadialLinearScale,
  ScatterController,
  TimeScale,
  TimeSeriesScale,
  Title,
  Tooltip
} from "chart.js";
import ChartDataLabels from "chartjs-plugin-datalabels";

Chart.register(
  ArcElement,
  LineElement,
  BarElement,
  PointElement,
  BarController,
  BubbleController,
  DoughnutController,
  LineController,
  PieController,
  PolarAreaController,
  RadarController,
  ScatterController,
  CategoryScale,
  LinearScale,
  LogarithmicScale,
  RadialLinearScale,
  TimeScale,
  TimeSeriesScale,
  Decimation,
  Filler,
  Legend,
  Title,
  Tooltip,
  ChartDataLabels
);

export function renderChart(
  canvasElementId: string,
  config: ChartConfiguration
): Chart {
  return new Chart(canvasElementId, config);
}

export function getDefaultPercentChart(): ChartOptions {
  return {
    plugins: {
      datalabels: {
        color: "#FFFFFF",
        font: {
          size: 18,
          weight: "bold"
        },
        formatter: function(value) {
          return value + "%";
        }
      },
      legend: {
        display: true,
        position: "bottom",
        align: "end"
      }
    }
  };
}
