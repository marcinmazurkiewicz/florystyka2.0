import {
  ErrorType,
  ErrorMap,
  ResponseError,
  ValidError,
  ErrorInfo
} from "@/types/ErrorTypes";

const errorMap: ErrorMap = {
  [ErrorType.ABOVE_MAX]: "Wartość przekracza dopuszczalny limit",
  [ErrorType.AT_LEAST_ONE]: "Musisz wybrać przynajmniej jedną opcję",
  [ErrorType.CAPTCHA_ERROR]: "Niezaliczona weryfikacja reCAPTCHA",
  [ErrorType.CHOOSE_CORRECT]: "Zaznacz prawidłową odpowiedź",
  [ErrorType.CONNECT_ERROR]: "Nie udało się nawiązać połączenia z serwerem.",
  [ErrorType.CREDENTIALS_ERROR]: "Nieprawidłowy login/hasło",
  [ErrorType.EMPTY]: "Pole nie może być puste",
  [ErrorType.FILE_PROCESSING_ERROR]: "Wystąpił problem z zapisem pliku",
  [ErrorType.FORBIDDEN]: "Nie posiadasz odpowiednich uprawnień",
  [ErrorType.NOT_FOUND]: "Pytanie nie istnieje",
  [ErrorType.NOT_MAIL]: "Niepoprawny format adresu email",
  [ErrorType.NOT_MATCH]: "Pola muszą mieć tą samą wartość",
  [ErrorType.NOT_UNIQUE]: "Wartość jest już przypisana do innego konta",
  [ErrorType.PARSE_ERROR]: "Błędny format wysyłanych danych",
  [ErrorType.UNAUTHORIZED]: "Dostęp wyłącznie dla zalogowanych użytkowników",
  [ErrorType.UNDER_MIN]: "Wartość jest poniżej dolnego dopuszczalnego limitu",
  [ErrorType.UNKNOWN]: "Błąd serwera",
  [ErrorType.TOKEN_EXPIRED]: "Sesja wygasła",
  [ErrorType.TYPE_MISMATCH]: "Niewłaściwy format pliku",
  [ErrorType.VALIDATION_ERROR]: "Niepoprawne dane"
};

const errorCodeMap: { [key: number]: ErrorType } = {
  400: ErrorType.VALIDATION_ERROR,
  401: ErrorType.UNAUTHORIZED,
  403: ErrorType.FORBIDDEN,
  404: ErrorType.NOT_FOUND,
  503: ErrorType.CONNECT_ERROR
};

const getErrorBasedOnErrorType = (error: ErrorType): string => errorMap[error];

const getErrorBasedOnStatusCode = (status: number): string =>
  getErrorBasedOnErrorType(errorCodeMap[status]);

const getResponseError = (error: ResponseError): string => {
  let errorMsg: string = errorMap[error.error];
  const errorCode: number = error.status;
  if (!errorMsg) {
    errorMsg = getErrorBasedOnStatusCode(errorCode);
  }
  return errorMsg ? errorMsg : error.message;
};

const getErrorBasedOnErrorInfo = (error: ErrorInfo): string => {
  const errorMsg: string = getErrorBasedOnErrorType(error.errorType);
  return errorMsg ? errorMsg : error.msg;
};

const parseValidErrors = (error: ResponseError): ValidError => {
  const result: ValidError = {};
  if (error.errors) {
    for (const [key, val] of Object.entries(error.errors)) {
      result[key] = getErrorBasedOnErrorInfo(val);
    }
  }
  return result;
};

export {
  getResponseError,
  getErrorBasedOnErrorType,
  getErrorBasedOnStatusCode,
  getErrorBasedOnErrorInfo,
  parseValidErrors
};
