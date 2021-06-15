import { HTTP, authHTTP } from "@/http";
import { ResponseStatus } from "@/types/ResponseStatus";
import {
  getErrorBasedOnStatusCode,
  getResponseError,
  parseValidErrors
} from "@/utils/errorUtils";
import { AxiosError } from "axios";
import { Header, Headers, PreparedResponse } from "@/types/PreparedResponse";
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

function getHeaders(headers: { [key: string]: string }): Headers {
  const result: Headers = {};
  if (headers["authorization"]) {
    result[Header.AUTHORIZATION] = headers["authorization"];
  }
  if (headers["expires"]) {
    result[Header.EXPIRES] = headers["expires"];
  }
  return result;
}

export async function getRequest<T>(
  url: string,
  auth = false
): Promise<PreparedResponse<T>> {
  const result: PreparedResponse<T> = {
    responseStatus: ResponseStatus.pending()
  };

  try {
    const response = auth ? await authHTTP.get(url) : await HTTP.get(url);
    const { data, headers } = response;
    result.responseStatus.isPending = false;
    result.responseStatus.isDataReturned = true;
    result.data = data;
    result.headers = getHeaders(headers);
    return Promise.resolve(result);
  } catch (error) {
    return Promise.reject(prepareErrorResponseStatus(error));
  }
}

export async function postRequest<T>(
  url: string,
  payload: RequestPayload,
  auth = false
): Promise<PreparedResponse<T>> {
  const result: PreparedResponse<T> = {
    responseStatus: ResponseStatus.pending()
  };

  try {
    const response = auth
      ? await authHTTP.post(url, payload)
      : await HTTP.post(url, payload);
    const { data, headers } = response;
    result.responseStatus.isPending = false;
    result.responseStatus.isDataReturned = true;
    result.data = data;
    result.headers = getHeaders(headers);
    return Promise.resolve(result);
  } catch (error) {
    return Promise.reject(prepareErrorResponseStatus(error));
  }
}
