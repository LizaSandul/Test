package edu.bsuir.test;

import edu.bsuir.pojo.User;
import edu.bsuir.test.user.BasicTest;
import org.junit.After;
import org.junit.Test;

import static edu.bsuir.test.user.Helper.createUser;
import static org.assertj.core.api.Assertions.assertThat;

public class UpdateUser extends BasicTest {

    private User postUser;

    @Test
    public void shouldUpdateUser() {
        postUser =  createUser("Sam", 30, 70000);
        String userLocation = updateResource("user/1", postUser);
        User getUser = getResource("user/1", User.class);
        assertThat(getUser).isEqualToIgnoringGivenFields(postUser, "id");
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
