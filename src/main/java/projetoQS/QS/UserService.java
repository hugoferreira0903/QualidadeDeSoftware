package projetoQS.QS;

import projetoQS.QS.exceptions.UserExceptions.*;
import projetoQS.QS.exceptions.UserExceptions.InvalidCredentialsException;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public List<User> createUser(User user) throws UserRegistrationException {
        // Verifica se o nome de usuário não é vazio
        if (user.getUsername().isEmpty()) {
            throw new InvalidUsernameException("O nome de usuário não pode ser vazio.");
        }

        // Verifica se o e-mail é válido
        if (!isValidEmail(user.getEmail())) {
            throw new InvalidEmailException("O e-mail é inválido.");
        }

        // Verifica se a senha tem mais de 8 caracteres
        if (user.getPassword().length() <= 8) {
            throw new InvalidPasswordException("A senha deve ter mais de 8 caracteres.");
        }

        // Verifica se a senha contém pelo menos 1 número
        if (!user.getPassword().matches(".*\\d.*")) {
            throw new InvalidPasswordException("A senha deve conter pelo menos 1 número.");
        }

        // Verifica se já existe um usuário com o mesmo username, senha ou email
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) ||
                u.getPassword().equals(user.getPassword()) ||
                u.getEmail().equals(user.getEmail())) {
                throw new DuplicateUserException("Dados duplicados: username, senha ou e-mail já existem.");
            }
        }

        // Se todas as verificações passarem, adiciona o usuário à lista
        users.add(user);

        return users; // Retorna a lista de usuários
    }

    // Método para validar o e-mail
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email != null && pattern.matcher(email).matches();
    }

    public void loginUser(String email, String password) throws InvalidCredentialsException {
        System.out.println("Tentando fazer login com email: " + email + " e senha: " + password); // Depuração
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                System.out.println("Email encontrado, verificando senha.");
                if (u.getPassword().equals(password)) {
                    System.out.println("Usuário logado: " + u.getUsername());
                    return; // Usuário encontrado e logado
                } else {
                    throw new InvalidCredentialsException("Credenciais inválidas! A senha está incorreta.");
                }
            }
        }
        throw new InvalidCredentialsException("Credenciais inválidas! Tente novamente.");
    }



    public List<User> getUsers() {
        return users;
    }
}
