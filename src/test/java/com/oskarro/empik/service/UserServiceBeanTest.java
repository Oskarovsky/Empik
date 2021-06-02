package com.oskarro.empik.service;

import com.oskarro.empik.config.EmpikTestConfiguration;
import com.oskarro.empik.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@Import(EmpikTestConfiguration.class)
public class UserServiceBeanTest {

    @Autowired
    public UserService userService;

    public UserServiceBeanTest() { }

    @Test
    public void test_isThereResponse() {
        assertNotNull(userService.getUser("octocat"));
    }

    @Test
    public void test_checkApiFieldMapping() {
        // given
        String login = "oskarovsky";

        // when
        User user = userService.getUser(login);

        // then
        assertEquals(user.getLogin(), "Oskarovsky");
        assertEquals(user.getId().longValue(), 33788050L);
        assertEquals(user.getAvatarUrl(), "https://avatars.githubusercontent.com/u/33788050?v=4");
        assertEquals(user.getName(), "Oskar Słyk");
        assertEquals(user.getType(), "User");
        assertEquals(user.getCreatedAt(), "2017-11-18T16:53:10Z");
        assertEquals(user.getCalculations().longValue(), 0);
    }


}