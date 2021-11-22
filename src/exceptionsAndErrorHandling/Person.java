package exceptionsAndErrorHandling;

public class Person {

    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
    }

    private void setAge(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age should be in the range [0...120]");
        }
        this.age = age;
    }

    private void setLastName(String lastName) {
        if (StringUtils.isNullOrEmpty(lastName)) {
            throw new IllegalArgumentException("The firs name cannot be null or empty");
        }
        this.lastName = lastName;
    }

    private void setFirstName(String firstName) {
        if (StringUtils.isNullOrEmpty(firstName)) {
            throw new IllegalArgumentException("The firs name cannot be null or empty");
        }
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
