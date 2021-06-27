import { PreparedResponse } from "@/types/PreparedResponse";
import { getRequest, postRequest } from "@/services/apiService";
import {
  LoginRequest,
  LoginResponse,
  NoneResponse,
  RegisterRequest
} from "@/types/AuthTypes";

export function sendLoginRequest(
  loginRequest: LoginRequest
): Promise<PreparedResponse<LoginResponse>> {
  return postRequest<LoginResponse>("api/v3/auth/login", loginRequest);
}

export function sendRefreshTokenRequest(): Promise<
  PreparedResponse<LoginResponse>
> {
  return getRequest<LoginResponse>("api/v3/auth/refresh");
}

export function sendLogoutRequest(): Promise<PreparedResponse<NoneResponse>> {
  return getRequest<NoneResponse>("api/v3/auth/logout", true);
}

export function sendRegisterRequest(
  registerRequest: RegisterRequest
): Promise<PreparedResponse<NoneResponse>> {
  return postRequest<NoneResponse>("api/v3/users", registerRequest);
}
