import { MemoryToken } from "@/types/AuthTypes";

let memoryToken: MemoryToken;

function login(token: string, tokenExp: number) {
  memoryToken = {
    token: token,
    expiryAtSeconds: tokenExp
  };
}

function getToken() {
  return memoryToken ? memoryToken.token : "";
}

export { login, getToken };
