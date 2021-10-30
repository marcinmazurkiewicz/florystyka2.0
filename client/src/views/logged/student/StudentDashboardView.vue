<template>
  <main
      class="w-full max-w-screen-lg m-auto my-6 text-white border rounded-lg border-gray-700 bg-dark-gray"
  >
    <section>
      <header
          class="text-center text-4xl text-red pt-16 pb-8 px-3 md:px-6 tracking-wider leading-relaxed"
      >
        Witaj {{ username }}
      </header>

      <div class="py-6 w-full grid grid-col-1 md:grid-cols-2">
        <div class="text-center py-6 mx-12">
          <h5 class="text-red text-2xl">Rozwiązane testy</h5>
          <h6 class="text-center text-xl pt-4 tracking-wider leading-relaxed">
            Statystyki
          </h6>
          <div class="my-4">
            <p>Rozwiązanych testów: {{ testStats.total }}</p>
            <p>Poprawnych odpowiedzi: {{testStats.correct}}</p>
          </div>
          <div class="flex justify-center my-4">
            <chart
                chart-type="doughnut"
                :chart-data="testChartData"
                chart-id="test-question-chart"
                :status="testResponseStatus"
            />
          </div>
        </div>

        <div class="text-center p-6">
          <h5 class="text-red text-2xl">Rozwiązane szybkie pytania</h5>
          <h6 class="text-center text-xl pt-4 tracking-wider leading-relaxed">
            Statystyki
          </h6>
          <div class="my-4">
            <p>Rozwiązanych pytań: {{ singleStats.total }}</p>
            <p>Poprawnych odpowiedzi: {{singleStats.correct}}</p>
          </div>
          <div class="flex justify-center my-4">
            <chart
                chart-type="doughnut"
                :chart-data="singleChartData"
                chart-id="single-question-chart"
                :status="singleResponseStatus"
            />
          </div>
        </div>
      </div>
    </section>
  </main>
</template>
<script lang="ts">
import {
  computed,
  defineComponent,
  onBeforeMount,
  ref,
  Ref,
  watchEffect
} from "vue";
import {getUsername} from "@/utils/authUtils";
import {useSolutionsStats} from "@/composables/useSolutionsStats";
import Chart from "@/components/Chart.vue";
import {ComputedRef} from "@vue/reactivity";

export default defineComponent({
  name: "Dashboard",
  components: {
    Chart
  },
  setup() {
    const username: Ref<string> = ref(getUsername());
    const {
      singleStats,
      testStats,
      singleResponseStatus,
      testResponseStatus,
      getStatsForSingleSolutions,
      getStatsForTestSolutions
    } = useSolutionsStats();

    const correctSinglePercent: ComputedRef<number> = computed(
        () =>
            Math.round(
                (singleStats.value.correct / singleStats.value.total) * 100 * 100
            ) / 100
    );

    const incorrectSinglePercent: ComputedRef<number> = computed(
        () => Math.round(
            ((singleStats.value.total - singleStats.value.correct) / singleStats.value.total) * 100 * 100) / 100
    );

    const correctTestPercent: ComputedRef<number> = computed(
        () => {
          if (testStats.value.correct > 0) {
            return Math.round(
                (testStats.value.correct / testStats.value.total) * 100 * 100
            ) / 100
          } else {
            return 0;
          }
        }
    );

    const incorrectTestPercent: ComputedRef<number> = computed(
        () => Math.round(
            ((testStats.value.total - testStats.value.correct) / testStats.value.total) * 100 * 100) / 100
    );

    function prepareSingleDataset() {
      return {
        labels: ["Zaliczone", "Niezaliczone"],
        datasets: [
          {
            data: [correctSinglePercent.value, incorrectSinglePercent.value],
            backgroundColor: ["#8bc34a", "#b23939"],
            borderWidth: [0, 0]
          }
        ]
      };
    }

    function prepareTestDataset() {
      return {
        labels: ["Zaliczone", "Niezaliczone"],
        datasets: [
          {
            data: [correctTestPercent.value, incorrectTestPercent.value],
            backgroundColor: ["#8bc34a", "#b23939"],
            borderWidth: [0, 0]
          }
        ]
      };
    }

    const singleChartData = ref(prepareSingleDataset());
    const testChartData = ref(prepareTestDataset());

    onBeforeMount(() => {
      getStatsForSingleSolutions();
      getStatsForTestSolutions();
    });

    watchEffect(() => {
      singleChartData.value = prepareSingleDataset();
    });

    watchEffect(() => {
      testChartData.value = prepareTestDataset();
    });

    return {
      singleResponseStatus,
      testResponseStatus,
      singleStats,
      testStats,
      username,
      singleChartData,
      testChartData
    };
  }
});
</script>
