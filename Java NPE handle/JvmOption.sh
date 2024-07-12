# With this config, NPE will show more detail log ↓↓↓↓↓
# example: Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "str" is null at Main.main(Main.java:4)
java -XX:+ShowCodeDetailsInExceptionMessages -jar app.jar