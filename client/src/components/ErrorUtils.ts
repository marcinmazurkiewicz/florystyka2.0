import { ErrorType, ErrorMap, ResponseError } from "@/types/ErrorTypes";

const errorMap: ErrorMap = {
  [ErrorType.ABOVE_MAX]: "Wartość przekracza dopuszczalny limit",
  [ErrorType.EMPTY]: "Pole nie może być puste",
  [ErrorType.AT_LEAST_ONE]: "Musisz wybrać przynajmniej jedną opcję",
  [ErrorType.NOT_MAIL]: "Niepoprawny format adresu email",
  [ErrorType.NOT_MATCH]: "Pola muszą mieć tą samą wartość",
  [ErrorType.NOT_UNIQUE]: "Wartość jest już przypisana do innego konta",
  [ErrorType.UNDER_MIN]: "Wartość jest poniżej dolnego dopuszczalnego limitu",
  [ErrorType.TYPE_MISMATCH]: "Niewłaściwy format pliku",
  [ErrorType.FORBIDDEN]: "Nie posiadasz odpowiednich uprawnień",
  [ErrorType.CREDENTIALS_ERROR]: "Nieprawidłowy login/hasło",
  [ErrorType.TOKEN_EXPIRED]: "Sesja wygasła",
  [ErrorType.UNAUTHORIZED]: "Dostęp wyłącznie dla zalogowanych użytkowników",
  [ErrorType.VALIDATION_ERROR]: "Niepoprawne dane",
  [ErrorType.PARSE_ERROR]: "Błędny format wysyłanych danych",
  [ErrorType.FILE_PROCESSING_ERROR]: "Wystąpił problem z zapisem pliku",
  [ErrorType.CHOOSE_CORRECT]: "Zaznacz prawidłową odpowiedź",
  [ErrorType.CONNECT_ERROR]: "Nie udało się nawiązać połączenia z serwerem.",
  [ErrorType.UNKNOWN]: "Błąd serwera"
};

const getErrorBasedOnResponse = (error: ResponseError): string => {
  const errorMsg: string = errorMap[error.error];
  return errorMsg ? errorMsg : error.message;
};

const getErrorBasedOnErrorType = (error: ErrorType): string => {
  return errorMap[error];
};

export { getErrorBasedOnResponse, getErrorBasedOnErrorType };
