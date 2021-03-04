import { HTTP } from "@/http";
import {
  Question,
  SingleSolutionResponse,
  SolutionRequest,
  TestResponse,
  TestSolutionResponse,
  UserChoice
} from "@/types/QuestionTypes";
import { ResponseStatus } from "@/types/ResponseStatus";
import {
  getErrorBasedOnResponse,
  getErrorBasedOnStatusCode
} from "@/components/ErrorUtils";
import { AxiosError } from "axios";
import { PreparedResponse } from "@/types/PreparedResponse";

function parseError(error: AxiosError): { code: number; message: string } {
  let code: number;
  let message: string;
  if (error.response?.data) {
    code = error.response.data.status;
    message = getErrorBasedOnResponse(error.response.data);
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

export async function getTest(): Promise<PreparedResponse<TestResponse>> {
  const result: PreparedResponse<TestResponse> = {
    responseStatus: {
      isDataReturned: false,
      isError: false,
      isPending: true
    }
  };

  try {
    const response = HTTP.get("/api/v3/questions/test");
    const { data } = await response;
    result.responseStatus.isPending = false;
    result.responseStatus.isDataReturned = true;
    result.data = data;
    return Promise.resolve(result);
  } catch (error) {
    return Promise.reject(prepareErrorResponseStatus(error));
  }
}

export function checkTest(
  answers: SolutionRequest[]
): Promise<TestSolutionResponse> {
  return HTTP.post(`/api/v3/solutions/test`, answers).then(
    response => response.data
  );
}

export async function getSingleQuestion(
  id: number
): Promise<PreparedResponse<Question>> {
  const result: PreparedResponse<Question> = {
    responseStatus: {
      isDataReturned: false,
      isError: false,
      isPending: true
    }
  };

  try {
    const response = HTTP.get(`/api/v3/questions/${id}`);
    const { data } = await response;
    result.responseStatus.isPending = false;
    result.responseStatus.isDataReturned = true;
    result.data = data;
    return Promise.resolve(result);
  } catch (error) {
    return Promise.reject(prepareErrorResponseStatus(error));
  }
}

export async function getRandomQuestion(): Promise<PreparedResponse<Question>> {
  const result: PreparedResponse<Question> = {
    responseStatus: {
      isDataReturned: false,
      isError: false,
      isPending: true
    }
  };

  try {
    const response = HTTP.get("/api/v3/questions/random");
    const { data } = await response;
    result.responseStatus.isPending = false;
    result.responseStatus.isDataReturned = true;
    result.data = data;
    return Promise.resolve(result);
  } catch (error) {
    return Promise.reject(prepareErrorResponseStatus(error));
  }
}

export async function checkAnswer(answer: {
  questionId: number;
  selectedAnswer: UserChoice;
}): Promise<PreparedResponse<SingleSolutionResponse>> {
  const result: PreparedResponse<SingleSolutionResponse> = {
    responseStatus: {
      isDataReturned: false,
      isError: false,
      isPending: true
    }
  };

  try {
    const response = HTTP.post(`/api/v3/solutions/single`, answer);
    const { data } = await response;
    result.responseStatus.isPending = false;
    result.responseStatus.isDataReturned = true;
    result.data = data;
    return Promise.resolve(result);
  } catch (error) {
    return Promise.reject(prepareErrorResponseStatus(error));
  }
}
