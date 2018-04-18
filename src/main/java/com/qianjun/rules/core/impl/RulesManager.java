package com.qianjun.rules.core.impl;

import com.qianjun.rules.core.Rule;
import com.qianjun.rules.utils.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class RulesManager {
    private static final Logger      LOGGER   = LoggerFactory.getLogger(RulesManager.class);

    private static Map<String, Rule> rulesMap = new TreeMap<>();

    private static void createRuleIntance() {
        List<Class<?>> list = ClassUtil.getAllClassByInterface(Rule.class, "com.qianjun.rules");
        if (CollectionUtils.isNotEmpty(list)) {
            for (Class<?> c : list) {
                try {
                    Rule rule = (Rule) c.newInstance();
                    String ruleName = rule.getName();
                    rulesMap.put(ruleName, rule);
                } catch (InstantiationException | IllegalAccessException e) {
                    log.warn("RulesManager createRuleIntance InstantiationException",e);
                }
            }
        }
    }

    public static Rule getRule(String ruleCode) {
        if (StringUtils.isBlank(ruleCode)) {
            return null;
        }
        if (rulesMap.isEmpty()) {
            createRuleIntance();
        }
        try {
            return rulesMap.get(ruleCode);
        } catch (Exception e) {
            LOGGER.error("create rule instance error : " + ruleCode, e);
        }
        return null;
    }

}
