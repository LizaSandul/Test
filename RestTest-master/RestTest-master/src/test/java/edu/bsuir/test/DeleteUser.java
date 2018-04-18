package edu.bsuir.test;

import edu.bsuir.pojo.User;
import edu.bsuir.test.user.BasicTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static edu.bsuir.test.user.Helper.createUser;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteUser extends BasicTest {

    private User postUser;

    @Before
    public void setUp() {

        postUser = createUser("Sam", 30, 70000);
    }

    @Test
    public void shouldDeleteAllUsers() {
        deleteAllResources("user/");
        String allUsers = getAllResources("user/");
        assertThat(allUsers).isEmpty();
    }

    @Test
    public void shouldDeleteUser() {
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

}
