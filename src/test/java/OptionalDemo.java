import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {

        UserService userService = new UserService();

        // Case 1: User found
        Optional<User> user1 = userService.findUserById(1);

        String name1 = user1
                .map(User::getName)
                .orElse("User not found");

        System.out.println("User 1: " + name1);


        // Case 2: User not found
        Optional<User> user2 = userService.findUserById(99);

        String name2 = user2
                .map(User::getName)
                .orElse("User not found");

        System.out.println("User 2: " + name2);


        // Case 3: Throw exception if user not found
        try {
            User user = userService.findUserById(99)
                    .orElseThrow(() -> new RuntimeException("User not found in DB"));

            System.out.println(user.getName());

        } catch (RuntimeException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

class UserService {

    public Optional<User> findUserById(int id) {

        if (id == 1) {
            return Optional.of(new User(1, "Parishkar"));
        }

        return Optional.empty();
    }
}

class User {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}