import { PreparedResponse } from "@/types/PreparedResponse";
import { getRequest, postRequest } from "@/services/apiService";
import {
  LoginRequest,
  LoginResponse,
  RegisterRequest,
  RegisterResponse
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

export function sendRegisterRequest(
  registerRequest: RegisterRequest
): Promise<PreparedResponse<RegisterResponse>> {
  return postRequest<RegisterResponse>("api/v3/users", registerRequest);
}
