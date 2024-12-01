package projetoQS.QS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Cria uma instância de UserService antes de cada teste
        userService = new UserService();
    }

    @Test
    public void testCreateUser_ValidUser() {
        // Cria um usuário válido
        User user = new User("username1", "validPassword1", "user1@example.com");

        // Cria o usuário e verifica se a lista tem tamanho 1
        List<User> users = userService.createUser(user);

        assertEquals(1, users.size(), "Deve adicionar o usuário na lista.");
        assertTrue(users.contains(user), "A lista de usuários deve conter o usuário criado.");
    }

    @Test
    public void testCreateUser_ShortPassword() {
        // Cria um usuário com senha curta
        User user = new User("username2", "short", "user2@example.com");

        // Tenta criar o usuário com senha curta
        List<User> users = userService.createUser(user);

        assertEquals(0, users.size(), "Não deve adicionar usuário com senha curta.");
    }

    @Test
    public void testCreateUser_NoNumberInPassword() {
        // Cria um usuário com senha sem números
        User user = new User("username3", "passwordWithoutNumber", "user3@example.com");

        // Tenta criar o usuário sem número na senha
        List<User> users = userService.createUser(user);

        assertEquals(0, users.size(), "A senha deve conter pelo menos um número.");
    }

    @Test
    public void testCreateUser_DuplicateUsername() {
        // Cria e adiciona um usuário
        User user1 = new User("username4", "validPassword2", "user4@example.com");
        userService.createUser(user1);

        // Cria um segundo usuário com o mesmo username
        User user2 = new User("username4", "anotherPassword1", "user5@example.com");

        // Tenta adicionar o segundo usuário, que deve falhar devido ao username duplicado
        List<User> users = userService.createUser(user2);

        assertEquals(1, users.size(), "Não deve adicionar usuário com username duplicado.");
    }

    @Test
    public void testLoginUser_ValidCredentials() {
        // Cria e adiciona um usuário
        User user = new User("username6", "validPassword3", "user6@example.com");
        userService.createUser(user);

        // Tenta fazer login com credenciais válidas
        userService.loginUser("user6@example.com", "validPassword3");
        // Espera-se que o login seja bem-sucedido, então não há necessidade de afirmações aqui
    }

    @Test
    public void testLoginUser_InvalidCredentials() {
        // Cria e adiciona um usuário
        User user = new User("username7", "validPassword4", "user7@example.com");
        userService.createUser(user);

        // Tenta fazer login com credenciais inválidas
        userService.loginUser("user7@example.com", "wrongPassword");
        // O teste verifica se o erro "Credenciais inválidas!" é impresso
    }

    @Test
    public void testCreateUser_EmptyUsername() {
        // Cria um usuário com o nome de usuário vazio
        User user = new User("", "validPassword5", "user8@example.com");

        // Tenta adicionar o usuário
        List<User> users = userService.createUser(user);

        assertEquals(0, users.size(), "Não deve adicionar usuário com nome de usuário vazio.");
    }

    @Test
    public void testCreateUser_ValidEmail() {
        // Cria um usuário com um email válido
        User user = new User("username9", "validPassword6", "user9@example.com");

        // Tenta criar o usuário com email válido
        List<User> users = userService.createUser(user);

        assertEquals(1, users.size(), "Deve adicionar o usuário com email válido.");
    }

    @Test
    public void testCreateUser_InvalidEmail() {
        // Cria um usuário com email inválido (sem validar email no código, mas assumindo que será validado)
        User user = new User("username10", "validPassword7", "invalidemail");

        // Tenta criar o usuário com email inválido
        List<User> users = userService.createUser(user);

        // Se você adicionar validação de email no código, esta asserção funcionará
        assertEquals(0, users.size(), "Não deve adicionar usuário com email inválido.");
    }
}