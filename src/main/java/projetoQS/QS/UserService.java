package projetoQS.QS;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class UserService {
    
    List<User> users = new ArrayList<>();
    
    public List<User> createUser(User user) {
        // Verifica se o nome de usuário não é vazio
        if (user.getUsername().isEmpty()) {
            System.out.println("Erro ao cadastrar usuário: O nome de usuário não pode ser vazio.");
            return users; // Não adiciona o usuário
        }
        
        // Verifica se o e-mail é válido
        if (!isValidEmail(user.getEmail())) {
            System.out.println("Erro ao cadastrar usuário: O e-mail é inválido.");
            return users; // Não adiciona o usuário
        }

        // Verifica se a senha tem mais de 8 caracteres
        if (user.getPassword().length() <= 8) {
            System.out.println("Erro ao cadastrar usuário: A senha deve ter mais de 8 caracteres.");
            return users; // Não adiciona o usuário
        }
        
        // Verifica se a senha contém pelo menos 1 número
        if (!user.getPassword().matches(".*\\d.*")) {
            System.out.println("Erro ao cadastrar usuário: A senha deve conter pelo menos 1 número.");
            return users; // Não adiciona o usuário
        }
        
        // Verifica se já existe um usuário com o mesmo username, senha ou email
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) || 
                u.getPassword().equals(user.getPassword()) || 
                u.getEmail().equals(user.getEmail())) {
                System.out.println("Erro ao cadastrar usuário: Dados duplicados.");
                return users; // Não adiciona o usuário
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

    // Método para login de usuário
    public void loginUser(String email, String password) {
        // Verifica se existe um usuário com o email e a senha fornecidos
        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                System.out.println("Usuário logado: " + u.getUsername());
                return; // Usuário encontrado e logado, então retorna
            }
        }

        // Se não encontrar, informa que as credenciais são inválidas
        System.out.println("Credenciais inválidas! Tente novamente.");
    }

    public List<User> getUsers() {
        return users;
    }
}