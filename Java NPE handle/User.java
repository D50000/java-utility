import lombok.NonNull;

public class User {
    private String name;

    // Lombok: Automatically generates bytecode null checks at the beginning of the method.
    // if (name == null) {
    //     throw new NullPointerException("name is marked non-null but is null");
    // }
    public void setName(@NonNull String name) {
        this.name = name;
    }
}
