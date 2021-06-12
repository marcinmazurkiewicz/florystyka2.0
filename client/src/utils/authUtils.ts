import { MemoryToken } from "@/types/AuthTypes";

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
}

function getToken(): string {
  return memoryToken ? memoryToken.token : "";
}

function isLoggedUser(): boolean {
  return memoryToken != null && memoryToken.token != null;
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

export { login, getToken, isLoggedUser, hasRight, hasAnyRight };
