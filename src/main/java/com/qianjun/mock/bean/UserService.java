package com.qianjun.mock.bean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qianjun
 * @Description: TODO
 * @create 2018-04-19 15:10
 * @last modify by [qianjun 2018-04-19 15:10]
 **/
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * @param user
     * @return
     */
    public Integer register(User user) {
        // TODO check params.
        User findUser = userDao.selectByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (findUser == null) {
            // register failed.
            return -1;
        }
        // register
        return userDao.save(user);
    }

    /**
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        User user = userDao.selectByUsernameAndPassword(username, password);
        return user != null ? true : false;
    }
}
