export interface LoginRequest extends RequestPayload {
  username: string;
  password: string;
}

export interface RegisterRequest extends RequestPayload {
  username: string;
  password: string;
  confirmPassword: string;
}

export type LoginResponse = {
  id: number;
  email: string;
  authorities: string[];
};

export type MemoryToken = {
  token: string;
  expiry: number;
  authorities: string[];
};
