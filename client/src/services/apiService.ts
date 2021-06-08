import { HTTP } from "@/http";
import { ResponseStatus } from "@/types/ResponseStatus";
import {
  getErrorBasedOnStatusCode,
  getResponseError,
  parseValidErrors
} from "@/utils/errorUtils";
import { AxiosError } from "axios";
import { PreparedResponse } from "@/types/PreparedResponse";
import { ValidError } from "@/types/ErrorTypes";

function parseError(
  error: AxiosError
): { code: number; message: string; errors: ValidError } {
  let code: number;
  let message: string;
  let errors: ValidError = {};
  if (error.response?.data) {
    code = error.response.data.status;
    message = getResponseError(error.response.data);
    if (error.response.data.errors) {
      errors = parseValidErrors(error.response.data);
    }
  } else if (error.code) {
    code = Number(error.code);
    message = getErrorBasedOnStatusCode(code);
  } else {
    code = 503;
    message = getErrorBasedOnStatusCode(code);
  }
  return { code, message, errors };
}

function prepareErrorResponseStatus(error: AxiosError): ResponseStatus {
  const { code, message, errors } = parseError(error);
  return {
    isDataReturned: false,
    isPending: false,
    isError: true,
    errorCode: code,
    errorMsg: message,
    errors: errors
  };
}

export async function getRequest<T>(url: string): Promise<PreparedResponse<T>> {
  const result: PreparedResponse<T> = {
    responseStatus: {
      isDataReturned: false,
      isError: false,
      isPending: true
    }
  };

  try {
    const response = HTTP.get(url);
    const { data, headers } = await response;
    result.responseStatus.isPending = false;
    result.responseStatus.isDataReturned = true;
    result.data = data;
    result.headers = headers;
    return Promise.resolve(result);
  } catch (error) {
    return Promise.reject(prepareErrorResponseStatus(error));
  }
}

export async function postRequest<T>(
  url: string,
  payload: RequestPayload
): Promise<PreparedResponse<T>> {
  const result: PreparedResponse<T> = {
    responseStatus: {
      isDataReturned: false,
      isError: false,
      isPending: true,
      errors: {}
    }
  };

  try {
    const response = HTTP.post(url, payload);
    const { data, headers } = await response;
    result.responseStatus.isPending = false;
    result.responseStatus.isDataReturned = true;
    result.data = data;
    result.headers = headers;
    return Promise.resolve(result);
  } catch (error) {
    return Promise.reject(prepareErrorResponseStatus(error));
  }
}
