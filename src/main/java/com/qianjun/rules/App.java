package com.qianjun.rules;

import com.qianjun.rules.bean.ActivityBean;
import com.qianjun.rules.bean.PrizeBean;
import com.qianjun.rules.core.impl.RulesEngineBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ScriptException, NoSuchMethodException, IOException {

        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = App.class.getClassLoader().getResourceAsStream("config.properties");
        // 使用properties对象加载输入流
        properties.load(in);
        // 获取key对应的value值
        String ruleLogin =  properties.getProperty("rule_login_1");
        // 规则json化
        net.sf.json.JSONArray prizesRuleArray = net.sf.json.JSONArray.fromObject(ruleLogin);
        net.sf.json.JSONArray prizeItemRuleArray = new  net.sf.json.JSONArray();
        String product = "KICZTJUZ2CGC_ZA-SUB-OFFER00000000000001090004";
         Iterator<JSONObject> prizes =  prizesRuleArray.iterator();
        while (prizes.hasNext()){
            JSONObject next = prizes.next();

            String product_code = next.getString("product_code");
            String ruleValue = next.getString("rule_value");

            JSONArray.fromObject(ruleValue);
            if (product.equals(product_code)){
                prizeItemRuleArray = net.sf.json.JSONArray.fromObject(ruleValue);
            }
        }
        System.out.println(prizesRuleArray);

        PrizeBean prizeBean = new PrizeBean();
        ActivityBean activityBean = new ActivityBean();
        net.sf.json.JSONObject firstLogin = new net.sf.json.JSONObject();
        firstLogin.put("isFirstLogin","1");
        //规则引擎执行规则. 改变奖品的状态
        RulesEngineBuilder.aNewRulesEngine().handlerPrizesRule(prizeItemRuleArray, prizeBean, activityBean, firstLogin)
                .build().executeRules();

        // DB update prizeBean

        // 发奖 award

    }

}
