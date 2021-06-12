export type AvailableAnswer = "A" | "B" | "C" | "D";

export type UserChoice = AvailableAnswer | "";

export type Timer = {
  minutes: number;
  seconds: number;
};

export type Answer = {
  value: AvailableAnswer;
  content: string;
};

export type Question = {
  id: number;
  content: string;
  answers: Array<Answer>;
  img?: string;
};

export type AdminQuestion = {
  id: number;
  content: string;
  answerA: string;
  answerB: string;
  answerC: string;
  answerD: string;
  correct: string;
  month?: number;
  year?: number;
  img?: string;
};

export type QuestionUnit = {
  question: Question;
  selectedAnswer: UserChoice;
  correctAnswer?: AvailableAnswer;
};

export class Test {
  timer: Timer;
  questions: QuestionUnit[];

  constructor(timer: Timer, questionList: Question[]) {
    this.timer = timer;
    this.questions = [];
    questionList.forEach(q =>
      this.questions.push({ question: q, selectedAnswer: "" })
    );
  }
}

export type TestResponse = {
  timer: Timer;
  questions: Question[];
};

export type SolutionResponse = {
  [key: number]: AvailableAnswer;
};

export type SingleSolutionResponse = {
  solutions: SolutionResponse;
};

export type TestSolutionResponse = {
  points: number;
  total: number;
  solutions: SolutionResponse;
};

export type SolutionRequest = {
  questionId: number;
  selectedAnswer: UserChoice;
};

export function isTest(test: Test | null): test is Test {
  return test != null;
}

export type AdminPagedQuestions = {
  content: AdminQuestion[];
  totalPages: number;
  number: number;
  size: number;
};
