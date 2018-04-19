package com.qianjun.mock;

import com.qianjun.mock.bean.User;
import com.qianjun.mock.bean.UserDao;
import com.qianjun.mock.bean.UserService;
import com.taobao.itest.ITestSpringContextBaseCase;
import com.taobao.itest.annotation.ITestSpringContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * @author qianjun
 * @Description:
 *      @Mock 在需要Mock的类使用@Mock注解，功能相当于Mockito.mock(...)
 *      @InjectMocks 用在被测试对象上，使对象可以自动注入依赖的mock对象
 *      @Spy 其功能类似Mock，用在需要Mock的类，如果其被调用的方法没有打桩，则会真是调用该方法
 *
 * @create 2018-04-19 15:12
 * @last modify by [qianjun 2018-04-19 15:12]
 **/
@RunWith(MockitoJUnitRunner.class)
@ITestSpringContext("classpath*:applicationContext.xml")
public class UserServiceLoginTest extends ITestSpringContextBaseCase {
    @InjectMocks
    private UserService userService;

    @Spy
//	@Mock
    private UserDao userDao;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // 如果用 @Mock注解的话，需要加下面这句进行初始化mock的类，
        // 或者在类上面加 @RunWith(MockitoJUnitRunner.class)
//		 MockitoAnnotations.initMocks(UserDao.class);
        when(userDao.selectByUsernameAndPassword(anyString(), anyString())).thenReturn(new User());
    }

    @Test
    public void testLogin() throws Exception {

        boolean login = userService.login("laowang", "123456");

        assertTrue(login);
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
