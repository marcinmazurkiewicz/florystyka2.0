export default {
    data() {
        return {
            error: {
                ABOVE_MAX: 'Wartość przekracza dopuszczalny limit',
                EMPTY: 'Pole nie może być puste',
                AT_LEAST_ONE: 'Musisz wybrać przynajmniej jedną opcję',
                NOT_MAIL: 'Niepoprawny format adresu email',
                NOT_MATCH: 'Pola muszą mieć tą samą wartość',
                NOT_UNIQUE: 'Wartość jest już przypisana do innego konta',
                UNDER_MIN: 'Wartość jest poniżej dolnego dopuszczalnego limitu',
                TYPE_MISMATCH: 'Niewłaściwy format pliku',
                FORBIDDEN: 'Nie posiadasz odpowiednich uprawnień',
                CREDENTIALS_ERROR: 'Nieprawidłowy login/hasło',
                TOKEN_EXPIRED: 'Sesja wygasła',
                UNAUTHORIZED: 'Dostęp wyłącznie dla zalogowanych użytkowników',
                VALIDATION_ERROR: 'Błędne dane',
                FILE_PROCESSING_ERROR: 'Wystąpił problem z zapisem pliku',
                CHOOSE_CORRECT: 'Zaznacz prawidłową odpowiedź'
            }
        }
    },
    methods: {
        getErrorMsg(error) {
            let errorMsg = this.error[error.errorType];
            return errorMsg ? errorMsg : error.msg;
        }
    }
}