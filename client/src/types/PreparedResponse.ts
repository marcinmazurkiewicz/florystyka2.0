import { ResponseStatus } from "@/types/ResponseStatus";

export enum Header {
  AUTHORIZATION,
  EXPIRES
}

export type Headers = {
  [key in keyof typeof Header]?: string;
};

export type PreparedResponse<T> = {
  data?: T;
  responseStatus: ResponseStatus;
  headers?: Headers;
};
