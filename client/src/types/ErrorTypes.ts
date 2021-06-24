enum ErrorType {
  ABOVE_MAX = "ABOVE_MAX",
  AT_LEAST_ONE = "AT_LEAST_ONE",
  CAPTCHA_ERROR = "CAPTCHA_ERROR",
  CHOOSE_CORRECT = "CHOOSE_CORRECT",
  CONNECT_ERROR = "CONNECT_ERROR",
  CREDENTIALS_ERROR = "CREDENTIALS_ERROR",
  EMPTY = "EMPTY",
  FILE_PROCESSING_ERROR = "FILE_PROCESSING_ERROR",
  FORBIDDEN = "FORBIDDEN",
  NOT_FOUND = "NOT_FOUND",
  NOT_MAIL = "NOT_MAIL",
  NOT_MATCH = "NOT_MATCH",
  NOT_UNIQUE = "NOT_UNIQUE",
  PARSE_ERROR = "PARSE_ERROR",
  TOKEN_EXPIRED = "TOKEN_EXPIRED",
  TYPE_MISMATCH = "TYPE_MISMATCH",
  UNAUTHORIZED = "UNAUTHORIZED",
  UNDER_MIN = "UNDER_MIN",
  UNKNOWN = "UNKNOWN",
  VALIDATION_ERROR = "VALIDATION_ERROR"
}

type ErrorInfo = {
  errorType: ErrorType;
  msg: string;
};

type FieldError = {
  [key: string]: ErrorInfo;
};

type ErrorMap = {
  [key in keyof typeof ErrorType]: string;
};

type ResponseError = {
  error: ErrorType;
  message: string;
  path: string;
  status: number;
  errors?: FieldError;
};

type ValidError = {
  [key: string]: string;
};

export { ErrorType, ErrorMap, ResponseError, ErrorInfo, ValidError };
