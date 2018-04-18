package edu.bsuir.test.user;

import edu.bsuir.pojo.User;
import edu.bsuir.test.BasicTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class UserPost extends BasicTest {

    private User postUser;

    private final String ALL_USERS = "[{\"id\":1,\"name\":\"Sam\",\"age\":30,\"salary\":70000.0},{\"id\":2,\"name\":\"Tom\",\"age\":40,\"salary\":50000.0},{\"id\":3,\"name\":\"Jerome\",\"age\":45,\"salary\":30000.0},{\"id\":4,\"name\":\"Silvia\",\"age\":50,\"salary\":40000.0}]";

    @Before
    public void setUp() {


    }

    @Test
    public void shouldGetAllUsers() {
        String allUsers = getAllResources("user/");
        assertThat(allUsers).isEqualTo(ALL_USERS);
    }
    @Test
    public void shouldCreateUser() {
        postUser = createUser("Sam", 30, 70000);
        String userLocation = createResource("user/", postUser);
        User getUser = getResource(userLocation, User.class);
        assertThat(getUser).isEqualToIgnoringGivenFields(postUser, "id");
    }

    @Test
    public void shouldUpdateUser() {
        postUser =  createUser("Sam", 30, 70000);
        String userLocation = updateResource("user/1", postUser);
        User getUser = getResource("user/1", User.class);
        assertThat(getUser).isEqualToIgnoringGivenFields(postUser, "id");
    }

    @Test
    public void shouldDeleteAllUsers() {
        deleteAllResources("user/");
        String allUsers = getAllResources("user/");
        assertThat(allUsers).isEmpty();
    }

    @Test
    public void shouldDeleteUser() {
        createUser("Sam", 30, 70000);
        String userLocation = createResource("user/", postUser);
        int statusCode = deleteResource(userLocation);
        assertThat(statusCode).isEqualTo(204);
    }

    @After
    public void shutDown() {
        deleteAllResources("user/");
        createUser("Sam", 30, 70000);
        createUser("Tom", 40, 50000);
        createUser("Jerome", 45, 30000);
        createUser("Silvia", 50, 40000);
    }

    private User createUser(String name, int age, int salary) {
        return new User().setName(name)
                .setAge(age)
                .setSalary(salary);
    }


}
