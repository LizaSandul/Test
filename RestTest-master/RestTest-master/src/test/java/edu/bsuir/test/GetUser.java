package edu.bsuir.test;

import edu.bsuir.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static edu.bsuir.test.user.Helper.createUser;
import static org.assertj.core.api.Assertions.assertThat;

public class GetUser extends BasicTest{

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

    @After
    public void shutDown() {
        deleteAllResources("user/");
        createUser("Sam", 30, 70000);
        createUser("Tom", 40, 50000);
        createUser("Jerome", 45, 30000);
        createUser("Silvia", 50, 40000);
    }



}
