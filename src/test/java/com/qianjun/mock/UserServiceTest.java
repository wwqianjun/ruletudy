package com.qianjun.mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;

import com.qianjun.mock.bean.MockitoStubHelper;
import com.qianjun.mock.bean.User;
import com.qianjun.mock.bean.UserDao;
import com.qianjun.mock.bean.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import com.taobao.itest.ITestSpringContextBaseCase;
import com.taobao.itest.annotation.ITestSpringContext;

/**
 * @author qianjun
 * @Description: TODO
 * @create 2018-04-19 16:11
 * @last modify by [qianjun 2018-04-19 16:11]
 **/
@RunWith(MockitoJUnitRunner.class)
@ITestSpringContext("classpath:applicationContext.xml")
public class UserServiceTest extends ITestSpringContextBaseCase {

    @InjectMocks
    private UserService userService;

    @Spy
//  @Mock
    private UserDao userDao;

    private MockitoStubHelper<UserDao> userDaoStubHelper;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        userDaoStubHelper = new MockitoStubHelper<UserDao>(userDao, "D:/user.txt");
        userDaoStubHelper.doStub();
    }

    @Test
    public void testRegiter() {
        User user = new User("laowang22", "123456");
        Integer register = userService.register(user);
        assertEquals(new Integer(1), register);
    }
    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        // reset
        reset(userDao);
    }
}
