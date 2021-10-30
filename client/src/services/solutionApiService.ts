import {PreparedResponse} from "@/types/PreparedResponse";
import {getRequest} from "@/services/apiService";
import {SolutionStatsResponse} from "@/types/SolutionTypes";

export function getSingleSolutionStats(): Promise<PreparedResponse<SolutionStatsResponse>> {
    return getRequest<SolutionStatsResponse>(
        "/api/v3/logged/solutions/single/stats",
        true
    );
}

export function getTestSolutionStats(): Promise<PreparedResponse<SolutionStatsResponse>> {
    return getRequest<SolutionStatsResponse>(
        "/api/v3/logged/solutions/test/stats",
        true
    );
}
