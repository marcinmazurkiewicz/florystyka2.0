import { ResponseStatus } from "@/types/ResponseStatus";

export type PreparedResponse<T> = {
  data?: T;
  responseStatus: ResponseStatus;
};
