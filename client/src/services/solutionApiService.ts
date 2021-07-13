import { PreparedResponse } from "@/types/PreparedResponse";
import { getRequest } from "@/services/apiService";
import { SolutionStatsResponse } from "@/types/SolutionTypes";

export function getSingleSolutionStats(): Promise<
  PreparedResponse<SolutionStatsResponse>
> {
  return getRequest<SolutionStatsResponse>(
    "/api/v3/logged/solutions/stats/single",
    true
  );
}
