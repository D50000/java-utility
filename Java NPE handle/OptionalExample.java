import java.util.Optional;

/**
 * Optional<T> more elegant and support useful method "orElseGet",
 * "orElseThrow", "map".
 */
public class OptionalExample {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Simulating user fetching
        Optional<User> userOptional = userService.findUserById(1L);
        // `User::getName` shorthand for `(User u) -> u.getName()`
        Optional<String> userName = userOptional.map(User::getName);
        System.out.println("Name: " + userName);

        // If user is present, print the name
        // logout: User found: John Doe
        userOptional.ifPresent(user -> System.out.println("User found: " + user.getName()));

        // If user is not present, print a default message
        // logout without error: User: Default User
        User user = userOptional.orElse(new User("Default User"));
        System.out.println("User: " + user.getName());
    }
}

class UserService {
    public Optional<User> findUserById(Long id) {
        // Simulate fetching a user
        User user = (id == 1L) ? new User("John Doe") : null;
        // If result is null return `Optional.empty()`.
        return Optional.ofNullable(user);
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
