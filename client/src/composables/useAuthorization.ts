import { LoginRequest, LoginResponse } from "@/types/AuthTypes";
import { ref, Ref } from "vue";
import { postRequest } from "@/services/apiService";
import { ResponseStatus } from "@/types/ResponseStatus";
import { Header, PreparedResponse } from "@/types/PreparedResponse";
import { login } from "@/utils/authUtils";

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
    return postRequest<LoginResponse>("api/v3/auth/login", loginRequest)
      .then(response => {
        if (response.headers) {
          const token: string | undefined =
            response.headers[Header.AUTHORIZATION];
          const tokenExp: string | undefined = response.headers[Header.EXPIRES];
          if (token != undefined && tokenExp != undefined)
            login(token, Number.parseInt(tokenExp));
        }

        return response;
      })
      .catch(errorStatus => {
        const response: PreparedResponse<LoginResponse> = {
          responseStatus: errorStatus
        };
        return response;
      });
  }

  return {
    responseStatus,
    sendLoginRequest
  };
}
