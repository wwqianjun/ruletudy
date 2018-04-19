package com.qianjun.mock.bean;

/**
 * @author qianjun
 * @Description: TODO
 * @create 2018-04-19 15:04
 * @last modify by [qianjun 2018-04-19 15:04]
 **/
public class UserDao {
    public User selectById(Integer id) {
        // test code
        return new User(id);
    }

    public int updateById(User user) {
        // test code
        return 0;
    }

    public int save(User user) {
        // test code
        return 1;
    }

    public User selectByUsernameAndPassword(String username, String password) {
        // test code
        return new User(1, username, password);
    }
}
