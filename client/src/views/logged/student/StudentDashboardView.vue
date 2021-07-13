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
          <div class="flex justify-center my-4"></div>
        </div>
        <div class="text-center p-6">
          <h5 class="text-red text-2xl">Rozwiązane szybkie pytania</h5>
          <h6 class="text-center text-xl pt-4 tracking-wider leading-relaxed">
            Statystyki
          </h6>
          <div class="flex justify-center my-4">
            <chart
              chart-type="doughnut"
              :chart-data="chartData"
              chart-id="single-question-chart"
              :status="responseStatus"
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
import { getUsername } from "@/utils/authUtils";
import { useSolutionsStats } from "@/composables/useSolutionsStats";
import Chart from "@/components/Chart.vue";
import { ComputedRef } from "@vue/reactivity";

export default defineComponent({
  name: "Dashboard",
  components: {
    Chart
  },
  setup() {
    const username: Ref<string> = ref(getUsername());
    const {
      singleStats,
      responseStatus,
      getStatsForSingleSolutions
    } = useSolutionsStats();

    const correctPercent: ComputedRef<number> = computed(
      () =>
        Math.round(
          (singleStats.value.correct / singleStats.value.total) * 100 * 100
        ) / 100
    );

    const incorrectPercent: ComputedRef<number> = computed(
      () => 100 - correctPercent.value
    );

    function prepareDataset() {
      return {
        labels: ["Zaliczone", "Niezaliczone"],
        datasets: [
          {
            data: [correctPercent.value, incorrectPercent.value],
            backgroundColor: ["#8bc34a", "#b23939"],
            borderWidth: [0, 0]
          }
        ]
      };
    }

    const chartData = ref(prepareDataset());

    onBeforeMount(() => getStatsForSingleSolutions());

    watchEffect(() => {
      chartData.value = prepareDataset();
    });

    return {
      responseStatus,
      singleStats,
      username,
      correctPercent,
      incorrectPercent,
      chartData
    };
  }
});
</script>
