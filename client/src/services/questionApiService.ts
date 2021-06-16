import {
  AdminPagedQuestions,
  AdminQuestion,
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

export async function getPagedQuestions(
  pageNo: number
): Promise<PreparedResponse<AdminPagedQuestions>> {
  return getRequest<AdminPagedQuestions>(
    `/api/v3/admin/questions?page=${pageNo - 1}`,
    true
  );
}
export async function getSingleQuestionPreview(
  id: number
): Promise<PreparedResponse<AdminQuestion>> {
  return getRequest<AdminQuestion>(`/api/v3/admin/questions/${id}`, true);
}
