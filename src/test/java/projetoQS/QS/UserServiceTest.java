package projetoQS.QS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import projetoQS.QS.exceptions.UserExceptions.*;

import java.util.List;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Cria uma instância de UserService antes de cada teste
        userService = new UserService();
    }

    @Test
    public void testCreateUser_ValidUser() throws UserRegistrationException {
        // Cria um usuário válido
        User user = new User("username1", "validPassword1", "user1@example.com");

        // Cria o usuário e verifica se a lista tem tamanho 1
        List<User> users = userService.createUser(user);

        assertEquals(1, users.size(), "Deve adicionar o usuário na lista.");
        assertTrue(users.contains(user), "A lista de usuários deve conter o usuário criado.");
    }

    @Test
    public void testCreateUser_EmptyUsername() {
        // Cria um usuário com o nome de usuário vazio
        User user = new User("", "validPassword5", "user8@example.com");

        // Verifica se a exceção correta é lançada
        assertThrows(InvalidUsernameException.class, () -> userService.createUser(user),
            "Deve lançar InvalidUsernameException para nome de usuário vazio.");
    }

    @Test
    public void testCreateUser_InvalidEmail() {
        // Cria um usuário com email inválido
        User user = new User("username10", "validPassword7", "invalidemail");

        // Verifica se a exceção correta é lançada
        assertThrows(InvalidEmailException.class, () -> userService.createUser(user),
            "Deve lançar InvalidEmailException para email inválido.");
    }

    @Test
    public void testCreateUser_ShortPassword() {
        // Cria um usuário com senha curta
        User user = new User("username2", "short", "user2@example.com");

        // Verifica se a exceção correta é lançada
        assertThrows(InvalidPasswordException.class, () -> userService.createUser(user),
            "Deve lançar InvalidPasswordException para senha curta.");
    }

    @Test
    public void testCreateUser_NoNumberInPassword() {
        // Cria um usuário com senha sem números
        User user = new User("username3", "passwordWithoutNumber", "user3@example.com");

        // Verifica se a exceção correta é lançada
        assertThrows(InvalidPasswordException.class, () -> userService.createUser(user),
            "Deve lançar InvalidPasswordException para senha sem número.");
    }

    @Test
    public void testCreateUser_DuplicateUser() throws UserRegistrationException {
        // Cria e adiciona um usuário
        User user1 = new User("username4", "validPassword2", "user4@example.com");
        userService.createUser(user1);

        // Cria um segundo usuário com o mesmo username
        User user2 = new User("username4", "anotherPassword1", "user5@example.com");

        // Verifica se a exceção correta é lançada
        assertThrows(DuplicateUserException.class, () -> userService.createUser(user2),
            "Deve lançar DuplicateUserException para username duplicado.");
    }

    @Test
    public void testLoginUser_ValidCredentials() throws UserRegistrationException {
        // Cria e adiciona um usuário
        User user = new User("username6", "validPassword3", "user6@example.com");
        userService.createUser(user);

        // Tenta fazer login com credenciais válidas (não lança exceção)
        assertDoesNotThrow(() -> userService.loginUser("user6@example.com", "validPassword3"),
            "Não deve lançar exceção para credenciais válidas.");
    }

    

}
