package com.qianjun.mock.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qianjun
 * @Description: 用来从本地文件中读取方法名、入参的jsonStr、返参的jsonStr
 * @create 2018-04-19 16:08
 * @last modify by [qianjun 2018-04-19 16:08]
 **/
@Data
public class MockArguments implements Serializable {

    private static final long serialVersionUID = -8906306987184384593L;

    private String memthod;

    private String[] input;

    private String output;

}
