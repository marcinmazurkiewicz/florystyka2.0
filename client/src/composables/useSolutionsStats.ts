import { getSingleSolutionStats } from "@/services/solutionApiService";
import { ref, Ref } from "vue";
import { SolutionStatsResponse } from "@/types/SolutionTypes";
import { ResponseStatus } from "@/types/ResponseStatus";

export function useSolutionsStats() {
  const singleStats: Ref<SolutionStatsResponse> = ref({ correct: 0, total: 0 });
  const responseStatus: Ref<ResponseStatus> = ref(ResponseStatus.pending());

  const getStatsForSingleSolutions = function() {
    return getSingleSolutionStats()
      .then(response => {
        responseStatus.value = response.responseStatus;
        if (response.data) {
          singleStats.value = response.data;
        }
        return Promise.resolve();
      })
      .catch(errorStatus => {
        responseStatus.value = errorStatus;
        return Promise.reject();
      });
  };

  return {
    singleStats,
    responseStatus,
    getStatsForSingleSolutions
  };
}
