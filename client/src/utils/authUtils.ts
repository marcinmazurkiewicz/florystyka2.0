import { LoginResponse, MemoryToken } from "@/types/AuthTypes";
import store from "@/store/index";
import { getRequest } from "@/services/apiService";
import { Header } from "@/types/PreparedResponse";
let memoryToken: MemoryToken;

function decodeToken(token: string): { authorities: string[]; expiry: number } {
  const base64Url = token.split(".")[1];
  const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  const jsonPayload = decodeURIComponent(
    atob(base64)
      .split("")
      .map(c => "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2))
      .join("")
  );

  const decodedToken = JSON.parse(jsonPayload);
  const authorities: string[] = decodedToken.authorities;
  const expiry: number = decodedToken.exp;
  return { authorities, expiry };
}

function login(token: string): void {
  const { authorities, expiry } = decodeToken(token);
  memoryToken = {
    token,
    expiry,
    authorities
  };
  store.commit("setLogged", true);
}

function getToken(): string {
  return memoryToken ? memoryToken.token : "";
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
  return getRequest<LoginResponse>("api/v3/auth/refresh")
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

export { login, getToken, isLoggedUser, hasRight, hasAnyRight, refreshToken };
