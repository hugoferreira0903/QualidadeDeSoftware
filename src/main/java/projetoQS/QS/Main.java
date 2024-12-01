package projetoQS.QS;

import projetoQS.QS.exceptions.UserExceptions.InvalidCredentialsException;
import projetoQS.QS.exceptions.UserExceptions.UserLoginException;
import projetoQS.QS.exceptions.UserExceptions.UserRegistrationException;


public class Main {
    public static void main(String[] args) throws UserRegistrationException, InvalidCredentialsException {
        UserService userService = new UserService();

        try {
            // Teste: Nome de usuário vazio
            User user1 = new User("", "ValidPass1", "user1@example.com");
            userService.createUser(user1);
        } catch (UserRegistrationException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }

        try {
            // Teste: E-mail inválido
            User user2 = new User("username2", "ValidPass2", "invalidemail");
            userService.createUser(user2);
        } catch (UserRegistrationException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }

        try {
            // Teste: Senha curta
            User user3 = new User("username3", "short", "user3@example.com");
            userService.createUser(user3);
        } catch (UserRegistrationException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }

        try {
            // Teste: Senha sem números
            User user4 = new User("username4", "NoNumbersHere", "user4@example.com");
            userService.createUser(user4);
        } catch (UserRegistrationException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }

        try {
            // Teste: Username duplicado
            User user5 = new User("duplicateUsername", "ValidPass5", "user5@example.com");
            userService.createUser(user5); // Adiciona com sucesso
            User user6 = new User("duplicateUsername", "AnotherPass5", "user6@example.com");
            userService.createUser(user6); // Deve lançar exceção
        } catch (UserRegistrationException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }

        try {
            // Criando um usuário válido
            User user1 = new User("username1", "ValidPass123", "user1@example.com");
            userService.createUser(user1); // Criando usuário

            // Tentando login com senha correta
            userService.loginUser("user1@example.com", "ValidPass123"); // Esperado: login bem-sucedido

            // Tentando login com senha errada
            userService.loginUser("user1@example.com", "WrongPassword"); // Esperado: exceção de credenciais inválidas
        } catch (UserRegistrationException | InvalidCredentialsException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }


        try {
            // Teste: Login com credenciais válidas
            User user8 = new User("username8", "ValidPass8", "user8@example.com");
            userService.createUser(user8);
            userService.loginUser("user8@example.com", "ValidPass8"); // Deve funcionar
            System.out.println("Login bem-sucedido!");
        } catch (UserLoginException | UserRegistrationException e) {
            System.out.println("Exceção inesperada: " + e.getMessage());
        }
    }
}
