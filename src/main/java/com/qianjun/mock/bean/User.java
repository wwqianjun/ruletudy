package com.qianjun.mock.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author qianjun
 * @Description: TODO
 * @create 2018-04-19 15:03
 * @last modify by [qianjun 2018-04-19 15:03]
 **/
@Data
@ToString
public class User {
    private Integer id;

    private String username;

    private String password;
    /**
     *
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @param username
     * @param password
     */
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    /**
     * @param id
     */
    public User(Integer id) {
        super();
        this.id = id;
    }
    /**
     * @param id
     * @param username
     * @param password
     */
    public User(Integer id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
