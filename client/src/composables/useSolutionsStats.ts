import {getSingleSolutionStats, getTestSolutionStats} from "@/services/solutionApiService";
import {ref, Ref} from "vue";
import {SolutionStatsResponse} from "@/types/SolutionTypes";
import {ResponseStatus} from "@/types/ResponseStatus";

export function useSolutionsStats() {
    const singleStats: Ref<SolutionStatsResponse> = ref({correct: 0, total: 0});
    const testStats: Ref<SolutionStatsResponse> = ref({correct: 0, total: 0});
    const singleResponseStatus: Ref<ResponseStatus> = ref(ResponseStatus.pending());
    const testResponseStatus: Ref<ResponseStatus> = ref(ResponseStatus.pending());

    const getStatsForSingleSolutions = function () {
        return getSingleSolutionStats()
            .then(response => {
                singleResponseStatus.value = response.responseStatus;
                if (response.data) {
                    singleStats.value = response.data;
                }
                return Promise.resolve();
            })
            .catch(errorStatus => {
                singleResponseStatus.value = errorStatus;
                return Promise.reject();
            });
    };

    const getStatsForTestSolutions = function () {
        return getTestSolutionStats()
            .then(response => {
                testResponseStatus.value = response.responseStatus;
                if (response.data) {
                    testStats.value = response.data;
                }
                return Promise.resolve();
            })
            .catch(errorStatus => {
                testResponseStatus.value = errorStatus;
                return Promise.reject();
            });
    };

    return {
        singleStats,
        testStats,
        singleResponseStatus,
        testResponseStatus,
        getStatsForSingleSolutions,
        getStatsForTestSolutions
    };
}
