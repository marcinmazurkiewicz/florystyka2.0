import { MemoryToken, NoneResponse } from "@/types/AuthTypes";
import store from "@/store/index";
import { Header, PreparedResponse } from "@/types/PreparedResponse";
import {
  sendLogoutRequest,
  sendRefreshTokenRequest
} from "@/services/authorizationService";

let memoryToken: MemoryToken;

function decodeToken(
  token: string
): { authorities: string[]; expiry: number; username: string } {
  const base64Url = token.split(".")[1];
  const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  const jsonPayload = decodeURIComponent(
    atob(base64)
      .split("")
      .map(c => "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2))
      .join("")
  );

  const decodedToken = JSON.parse(jsonPayload);
  const username: string = decodedToken.sub;
  const authorities: string[] = decodedToken.authorities;
  const expiry: number = decodedToken.exp;
  return { authorities, expiry, username };
}

function login(token: string): void {
  const { authorities, expiry, username } = decodeToken(token);
  memoryToken = {
    token,
    expiry,
    authorities,
    username
  };
  store.commit("setLogged", true);
}

function getToken(): string {
  return memoryToken ? memoryToken.token : "";
}

function getUsername(): string {
  return memoryToken.username;
}

function isLoggedUser(): boolean {
  return memoryToken != null && Date.now() / 1000 < memoryToken.expiry;
}

function hasRight(permission: string): boolean {
  return memoryToken?.authorities?.includes(permission);
}

function hasAnyRight(permissions: string[]): boolean {
  let hasPermission = false;
  permissions.forEach(permission => {
    if (hasRight(permission)) {
      hasPermission = true;
    }
  });
  return hasPermission;
}

function refreshToken(): Promise<boolean> {
  return sendRefreshTokenRequest()
    .then(response => {
      if (response.headers) {
        const token: string | undefined =
          response.headers[Header.AUTHORIZATION];
        if (token != undefined) {
          login(token);
          return Promise.resolve(true);
        } else {
          return Promise.resolve(false);
        }
      }
      return false;
    })
    .catch(() => {
      return Promise.resolve(false);
    });
}

function logout(): Promise<PreparedResponse<NoneResponse>> {
  return sendLogoutRequest().then(response => {
    memoryToken = {
      token: "",
      expiry: Date.now() / 1000,
      authorities: [],
      username: ""
    };
    store.commit("logout");
    return response;
  });
}

export {
  login,
  getToken,
  getUsername,
  isLoggedUser,
  hasRight,
  hasAnyRight,
  refreshToken,
  logout
};
