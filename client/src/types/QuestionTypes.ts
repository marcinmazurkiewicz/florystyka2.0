type TestAnswer = {
  questionId: number;
  selectedAnswer: string;
};

type Answer = {
  value: string;
  content: string;
};

type Question = {
  id: number;
  content: string;
  answers: Array<Answer>;
  img?: string;
};

type Solution = {
  questionId: number;
  correct?: string;
  selected?: string;
};

export { Answer, Solution, Question, TestAnswer };
