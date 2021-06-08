import { LoginRequest, LoginResponse } from "@/types/AuthTypes";
import { ref, Ref } from "vue";
import { postRequest } from "@/services/apiService";
import { ResponseStatus } from "@/types/ResponseStatus";
import { PreparedResponse } from "@/types/PreparedResponse";

export function useAuthorization() {
  const responseStatus: Ref<ResponseStatus> = ref({
    isDataReturned: true,
    isError: false,
    isPending: false,
    errors: {}
  });

  async function sendLoginRequest(
    loginRequest: LoginRequest
  ): Promise<PreparedResponse<LoginResponse>> {
    return postRequest<LoginResponse>("api/v3/auth/login", loginRequest).catch(
      errorStatus => {
        const response: PreparedResponse<LoginResponse> = {
          responseStatus: errorStatus
        };
        return response;
      }
    );
  }

  return {
    responseStatus,
    sendLoginRequest
  };
}
