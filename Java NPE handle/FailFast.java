public class User {
    private String name;

    public User(String name) {
        if (name == null) {
            // Fail-fast behavior involves manually writing checks and throwing exceptions when invalid conditions are detected. 
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }
}
