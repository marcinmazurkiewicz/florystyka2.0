import {
  LoginRequest,
  RegisterRequest,
  LoginResponse
} from "@/types/AuthTypes";

import { Header, PreparedResponse } from "@/types/PreparedResponse";
import { login } from "@/utils/authUtils";
import { sendLoginRequest } from "@/services/authorizationService";

export function useAuthorization() {
  function tryLogin(
    loginRequest: LoginRequest
  ): Promise<PreparedResponse<LoginResponse>> {
    return sendLoginRequest(loginRequest)
      .then(response => {
        if (response.headers) {
          const token: string | undefined =
            response.headers[Header.AUTHORIZATION];
          if (token != undefined) login(token);
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

  function tryRegister(
    loginRequest: RegisterRequest
  ): Promise<PreparedResponse<LoginResponse>> {
    return sendLoginRequest(loginRequest)
      .then(response => {
        if (response.headers) {
          const token: string | undefined =
            response.headers[Header.AUTHORIZATION];
          if (token != undefined) login(token);
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
    tryLogin,
    tryRegister
  };
}
