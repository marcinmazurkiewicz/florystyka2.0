import {
  LoginRequest,
  RegisterRequest,
  LoginResponse,
  RegisterResponse
} from "@/types/AuthTypes";

import { Header, PreparedResponse } from "@/types/PreparedResponse";
import { login } from "@/utils/authUtils";
import {
  sendLoginRequest,
  sendRegisterRequest
} from "@/services/authorizationService";

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
        return Promise.resolve(response);
      })
      .catch(errorStatus => {
        const response: PreparedResponse<LoginResponse> = {
          responseStatus: errorStatus
        };
        return Promise.reject(response);
      });
  }

  function tryRegister(
    registerRequest: RegisterRequest
  ): Promise<PreparedResponse<RegisterResponse>> {
    return sendRegisterRequest(registerRequest).catch(errorStatus => {
      const response: PreparedResponse<LoginResponse> = {
        responseStatus: errorStatus
      };
      return Promise.reject(response);
    });
  }

  return {
    tryLogin,
    tryRegister
  };
}
