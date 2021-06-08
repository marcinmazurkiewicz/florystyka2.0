import { ValidError } from "@/types/ErrorTypes";

export class ResponseStatus {
  isDataReturned: boolean;
  isError: boolean;
  isPending: boolean;
  errorCode?: number;
  errorMsg?: string;
  errors?: ValidError;

  constructor(isDataReturned: boolean, isError: boolean, isPending: boolean) {
    this.isDataReturned = isDataReturned;
    this.isError = isError;
    this.isPending = isPending;
    this.errors = {};
  }

  static pending = () => new ResponseStatus(false, false, true);
  static error = () => new ResponseStatus(false, true, false);
  static ok = () => new ResponseStatus(true, false, false);
}
