export type ResponseStatus = {
  isDataReturned: boolean;
  isError: boolean;
  isPending: boolean;
  errorCode?: number;
  errorMsg?: string;
};
