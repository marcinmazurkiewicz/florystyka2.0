import { Ref, ref } from "vue";
import { getErrorBasedOnErrorType, getResponseError } from "@/utils/errorUtils";
import { ErrorType } from "@/types/ErrorTypes";
import {
  AdminPagedQuestions,
  AdminQuestion,
  QuestionUnit,
  SolutionRequest,
  Test,
  UserChoice
} from "@/types/QuestionTypes";
import router from "@/router/index.ts";

import {
  checkAnswer,
  checkTest,
  getRandomQuestion,
  getSingleQuestion,
  getTest,
  getPagedQuestions,
  getSingleQuestionPreview,
  saveQuestion
} from "@/services/questionApiService";
import { ResponseStatus } from "@/types/ResponseStatus";
import {  PreparedResponse } from "@/types/PreparedResponse";


export function useQuestion() {
  const solved = ref(false);
  const question: Ref<QuestionUnit | null> = ref(null);
  const selectedAnswer: Ref<UserChoice> = ref("");
  const responseStatus: Ref<ResponseStatus> = ref({
    isDataReturned: false,
    isError: false,
    isPending: true
  });

  function randomQuestion(): void {
    solved.value = false;
    selectedAnswer.value = "";

    getRandomQuestion()
      .then(response => {
        responseStatus.value = response.responseStatus;
        if (response.data) {
          question.value = {
            question: response.data,
            selectedAnswer: ""
          };
        }
      })
      .catch(errorStatus => {
        responseStatus.value = errorStatus;
      });
  }

  function singleQuestion(id: string): void {
    solved.value = false;
    selectedAnswer.value = "";

    const questionId: number = parseInt(id);
    if (isNaN(questionId)) {
      responseStatus.value = {
        isError: true,
        isDataReturned: false,
        isPending: false,
        errorMsg: getErrorBasedOnErrorType(ErrorType.VALIDATION_ERROR)
      };
      return;
    }

    getSingleQuestion(questionId)
      .then(response => {
        responseStatus.value = response.responseStatus;
        if (response.data) {
          question.value = {
            question: response.data,
            selectedAnswer: ""
          };
        }
      })
      .catch(errorStatus => {
        responseStatus.value = errorStatus;
      });
  }

  function submitAnswer(questionUnit: QuestionUnit): void {
    const data = {
      questionId: questionUnit.question.id,
      selectedAnswer: questionUnit.selectedAnswer
    };
    checkAnswer(data)
      .then(response => {
        responseStatus.value = response.responseStatus;
        if (response.data) {
          questionUnit.correctAnswer =
            response.data.solutions[questionUnit.question.id];
        }
      })
      .catch(errorStatus => {
        responseStatus.value = errorStatus;
      });
  }

  return {
    solved,
    question,
    responseStatus,
    getRandomQuestion: randomQuestion,
    getSingleQuestion: singleQuestion,
    submitAnswer,
    selectedAnswer
  };
}

export function useTest() {
  const points = ref(0);
  const total = ref(0);
  const solved = ref(false);
  const testRef: Ref<Test | null> = ref(null);
  const responseStatus: Ref<ResponseStatus> = ref({
    isDataReturned: false,
    isError: false,
    isPending: true
  });

  async function getQuestions(): Promise<void> {
    getTest().then(response => {
      responseStatus.value = response.responseStatus;
      if (typeof response.data !== "undefined") {
        const testResponse = response.data;
        testRef.value = new Test(testResponse.timer, testResponse.questions);
      }
    });
  }

  async function sendAnswers(questions: QuestionUnit[]) {
    solved.value = true;
    const answers: SolutionRequest[] = [];
    questions.forEach(question =>
      answers.push({
        questionId: question.question.id,
        selectedAnswer: question.selectedAnswer
      })
    );
    await checkTest(answers)
      .then(response => {
        if (response.data) {
          points.value = response.data.points;
          total.value = response.data.total;
          questions.forEach(
            question =>
              (question.correctAnswer =
                response.data?.solutions[question.question.id])
          );
        }
      })
      .catch(error => {
        responseStatus.value = {
          isDataReturned: false,
          isError: true,
          isPending: false,
          errorMsg: error.response?.data
            ? getResponseError(error.response.data)
            : getErrorBasedOnErrorType(ErrorType.CONNECT_ERROR)
        };
      });
  }

  return {
    points,
    total,
    test: testRef,
    responseStatus,
    solved,
    getQuestions,
    sendAnswers
  };
}

export function useQuestionsAsAdmin() {
  const nearbyPages = function(
    totalPages: number,
    currentPage: number
  ): number[] {
    const result = [];
    if (totalPages < 5) {
      for (let i = 1; i <= totalPages; i++) {
        result.push(i);
      }
    } else if (currentPage < 3) {
      result.push(1, 2, 3, 4, 5);
    } else if (currentPage > totalPages - 2) {
      result.push(
        totalPages - 4,
        totalPages - 3,
        totalPages - 2,
        totalPages - 1,
        totalPages
      );
    } else {
      result.push(
        currentPage - 2,
        currentPage - 1,
        currentPage,
        currentPage + 1,
        currentPage + 2
      );
    }
    return result;
  };

  const goToDetails = function(questionId: number): void {
    router.push({
      name: "AdminQuestionView",
      params: { questionId: questionId }
    });
  };

  const goToPage = function(
    page: number
  ): Promise<PreparedResponse<AdminPagedQuestions>> {
    return getPagedQuestions(page);
  };

  const getQuestionPreview = function(
    questionId: number
  ): Promise<PreparedResponse<AdminQuestion>> {
    return getSingleQuestionPreview(questionId);
  };

  const addQuestion = function(
    question: FormData
  ): Promise<PreparedResponse<AdminQuestion>> {
    return saveQuestion(question)
      .then(response => {
        return response;
      })
      .catch(errorStatus => {
        const response: PreparedResponse<AdminQuestion> = {
          responseStatus: errorStatus
        };
        return Promise.reject(response);
      });
  };

  return {
    nearbyPages,
    goToPage,
    goToDetails,
    getQuestionPreview,
    addQuestion
  };
}
