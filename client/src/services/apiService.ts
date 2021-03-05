import { HTTP } from "@/http";
import { ResponseStatus } from "@/types/ResponseStatus";
import {
  getResponseError,
  getErrorBasedOnStatusCode
} from "@/utils/errorUtils";
import { AxiosError } from "axios";
import { PreparedResponse } from "@/types/PreparedResponse";

function parseError(error: AxiosError): { code: number; message: string } {
  let code: number;
  let message: string;
  if (error.response?.data) {
    code = error.response.data.status;
    message = getResponseError(error.response.data);
  } else if (error.code) {
    code = Number(error.code);
    message = getErrorBasedOnStatusCode(code);
  } else {
    code = 503;
    message = getErrorBasedOnStatusCode(code);
  }
  return { code, message };
}

function prepareErrorResponseStatus(error: AxiosError): ResponseStatus {
  const { code, message } = parseError(error);
  return {
    isDataReturned: false,
    isPending: false,
    isError: true,
    errorCode: code,
    errorMsg: message
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
    const { data } = await response;
    result.responseStatus.isPending = false;
    result.responseStatus.isDataReturned = true;
    result.data = data;
    return Promise.resolve(result);
  } catch (error) {
    return Promise.reject(prepareErrorResponseStatus(error));
  }
}

export async function postRequest<T>(
  url: string,
  payload: any
): Promise<PreparedResponse<T>> {
  const result: PreparedResponse<T> = {
    responseStatus: {
      isDataReturned: false,
      isError: false,
      isPending: true
    }
  };

  try {
    const response = HTTP.post(url, payload);
    const { data } = await response;
    result.responseStatus.isPending = false;
    result.responseStatus.isDataReturned = true;
    result.data = data;
    return Promise.resolve(result);
  } catch (error) {
    return Promise.reject(prepareErrorResponseStatus(error));
  }
}
