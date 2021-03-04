enum ErrorType {
  ABOVE_MAX = "ABOVE_MAX",
  EMPTY = "EMPTY",
  AT_LEAST_ONE = "AT_LEAST_ONE",
  NOT_MAIL = "NOT_MAIL",
  NOT_MATCH = "NOT_MATCH",
  NOT_UNIQUE = "NOT_UNIQUE",
  UNDER_MIN = "UNDER_MIN",
  TYPE_MISMATCH = "TYPE_MISMATCH",
  FORBIDDEN = "FORBIDDEN",
  CREDENTIALS_ERROR = "CREDENTIALS_ERROR",
  TOKEN_EXPIRED = "TOKEN_EXPIRED",
  UNAUTHORIZED = "UNAUTHORIZED",
  VALIDATION_ERROR = "VALIDATION_ERROR",
  FILE_PROCESSING_ERROR = "FILE_PROCESSING_ERROR",
  CHOOSE_CORRECT = "CHOOSE_CORRECT",
  PARSE_ERROR = "PARSE_ERROR",
  CONNECT_ERROR = "CONNECT_ERROR",
  NOT_FOUND = "NOT_FOUND",
  UNKNOWN = "UNKNOWN"
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

type ParsedError = {
  [key: string]: string;
};

export { ErrorType, ErrorMap, ResponseError, ErrorInfo, ParsedError };
