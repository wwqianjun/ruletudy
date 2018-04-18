package com.qianjun.rules.core.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.qianjun.rules.bean.ActivityBean;
import com.qianjun.rules.bean.PrizeBean;
import com.qianjun.rules.core.Rule;

import com.qianjun.rules.core.RulesEngine;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class RulesEngineBuilder {

    private Set<Rule> rules;

    public static RulesEngineBuilder aNewRulesEngine() {
        return new RulesEngineBuilder();
    }

    public RulesEngineBuilder handlerPrizesRule(JSONArray jsonRules, PrizeBean prizeWinning,
                                                ActivityBean pbaActivityDO) {
        return handlerPrizesRule(jsonRules, prizeWinning, pbaActivityDO, null);
    }

    public RulesEngineBuilder handlerPrizesRule(JSONArray jsonRules, PrizeBean prizeWinning,
                                                ActivityBean pbaActivityDO, JSONObject firstLogin) {
        if (CollectionUtils.isNotEmpty(jsonRules)) {
            rules = new HashSet<>();
            for (Iterator<JSONObject> it = jsonRules.iterator(); it.hasNext(); ) {
                JSONObject ruleJson = (JSONObject) it.next();
                String ruleCode = ruleJson.getString("rule_code");
                if (StringUtils.isBlank(ruleCode)) {
                    continue;
                }
                //获取实例化对象
                Rule rule = RulesManager.getRule(ruleCode);
                //拼装规则所需数据
                Map<String, Object> params = new HashMap<>();
                params.put("prizeWinning", prizeWinning);
                params.put("jsonRules", jsonRules);
                params.put("pbaActivityDO", pbaActivityDO);
                if ("LGL001".equals(ruleCode) && firstLogin != null) {
                    params.put("isFirstLogin", firstLogin.getString("isFirstLogin"));
                }
                rule.setParams(params);
                //添加规则到执行集合
                rules.add(rule);
            }
        }
        return this;
    }

    public RulesEngine build() {
        return new DefaultRulesEngine(rules);
    }
}
