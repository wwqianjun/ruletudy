package com.qianjun.rules.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qianjun
 * @Description: TODO
 * @create 2018-04-18 10:28
 * @last modify by [qianjun 2018-04-18 10:28]
 **/
@Data
public class RuleBean  implements Serializable {

    private static final long serialVersionUID = 4457853782392784812L;

    String product_code;
    String product_name;
    String rule_value;

}
