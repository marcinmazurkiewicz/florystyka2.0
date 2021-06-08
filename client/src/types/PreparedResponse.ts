import { ResponseStatus } from "@/types/ResponseStatus";

export type Header = {
  [key: string]: string;
};

export type PreparedResponse<T> = {
  data?: T;
  responseStatus: ResponseStatus;
  headers?: Header[];
};
