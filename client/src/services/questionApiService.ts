import {
  Question,
  SingleSolutionResponse,
  SolutionRequest,
  TestResponse,
  TestSolutionResponse,
  UserChoice
} from "@/types/QuestionTypes";

import { PreparedResponse } from "@/types/PreparedResponse";
import { getRequest, postRequest } from "@/services/apiService";

export async function getTest(): Promise<PreparedResponse<TestResponse>> {
  return getRequest<TestResponse>("/api/v3/questions/test");
}

export function checkTest(
  answers: SolutionRequest[]
): Promise<PreparedResponse<TestSolutionResponse>> {
  return postRequest<TestSolutionResponse>("/api/v3/solutions/test", answers);
}

export async function getSingleQuestion(
  id: number
): Promise<PreparedResponse<Question>> {
  return getRequest<Question>(`/api/v3/questions/${id}`);
}

export async function getRandomQuestion(): Promise<PreparedResponse<Question>> {
  return getRequest<Question>("/api/v3/questions/random");
}

export async function checkAnswer(answer: {
  questionId: number;
  selectedAnswer: UserChoice;
}): Promise<PreparedResponse<SingleSolutionResponse>> {
  return postRequest<SingleSolutionResponse>(
    "/api/v3/solutions/single",
    answer
  );
}
