import { Ref, ref } from "vue";
import {
  getErrorBasedOnErrorType,
  getErrorBasedOnResponse
} from "@/components/ErrorUtils";
import { ErrorType } from "@/types/ErrorTypes";
import {
  QuestionUnit,
  SolutionRequest,
  Test,
  UserChoice
} from "@/types/QuestionTypes";
import {
  checkAnswer,
  checkTest,
  getRandomQuestion,
  getSingleQuestion,
  getTest
} from "@/composables/apiService";
import { ResponseStatus } from "@/types/ResponseStatus";

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
        points.value = response.points;
        total.value = response.total;
        questions.forEach(
          question =>
            (question.correctAnswer = response.solutions[question.question.id])
        );
      })
      .catch(error => {
        responseStatus.value = {
          isDataReturned: false,
          isError: true,
          isPending: false,
          errorMsg: error.response?.data
            ? getErrorBasedOnResponse(error.response.data)
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
