public void setName(String name) {
    // If data is null it will show the warning.
    this.name = Objects.requireNonNull(name, "Name cannot be null");
}

