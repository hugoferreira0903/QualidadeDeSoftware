package projetoQS.QS.exceptions;

public class UserExceptions {

    public static class UserRegistrationException extends Exception {
        public UserRegistrationException(String message) {
            super(message);
        }
    }

    public static class InvalidUsernameException extends UserRegistrationException {
        public InvalidUsernameException(String message) {
            super(message);
        }
    }

    public static class InvalidEmailException extends UserRegistrationException {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

    public static class InvalidPasswordException extends UserRegistrationException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }
    
    public static class UserLoginException extends RuntimeException {
        public UserLoginException(String message) {
            super(message);
        }
    }

    public static class DuplicateUserException extends UserRegistrationException {
        public DuplicateUserException(String message) {
            super(message);
        }   
        
    }
    
    public static class InvalidCredentialsException extends Exception {
        public InvalidCredentialsException(String message) {
            super(message);
        }
    }
    
}
