package edu.bsuir.test.user;
import edu.bsuir.pojo.User;

public class Helper {

    public static User createUser(String name, int age, int salary) {
        return new User()
                .setName(name)
                .setAge(age)
                .setSalary(salary);
    }
}
