package com.qianjun.rules.rule;


import com.qianjun.rules.bean.Constants;
import com.qianjun.rules.bean.PrizeBean;
import com.qianjun.rules.core.Rule;
import com.qianjun.rules.core.impl.BasicRule;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;

@Slf4j
public class AuditWinningPrizeRule extends BasicRule {

    private String              name     = "LGL003";

    private int                 priority = 10;

    private Map<String, Object> params   = null;

    @SuppressWarnings("unchecked")
    @Override
    public void execute() throws Exception {
        try {
            Object oPrize = getParams().get("prizeWinning");
            if (oPrize == null) {
                log.info("prizeWinning is null !");
                return;
            }
            PrizeBean prize = (PrizeBean) oPrize;
            Byte stauts = prize.getAuditStatus();
            if (stauts != null && stauts.intValue() > -1) {
                log.info("prizeWinning  is null !");
                return;
            }
            Object oJsonRules = getParams().get("jsonRules");
            if (oJsonRules == null) {
                log.info("jsonRules is null !");
                return;
            }
            JSONArray jsonRules = (JSONArray) oJsonRules;
            if (CollectionUtils.isEmpty(jsonRules)) {
                log.info("jsonRules is isEmpty !");
                return;
            }
            boolean flag = true;
            for (Iterator<JSONObject> it = jsonRules.iterator(); it.hasNext();) {
                JSONObject ruleJson = it.next();
                String ruleName = ruleJson.getString("name");
                //针对审核的规则单独创建
                if (StringUtils.equalsIgnoreCase(ruleName, Constants.needAudit)) {
                    String val = ruleJson.getString("val");
                    if (StringUtils.isBlank(val)) {
                        log.info("needAudit is null default!");
                    }
                    flag = Boolean.valueOf(val);
                    break;
                }
            }
            prize.setAuditStatus(Constants.PRIZE_RULE_NOT_NEED_AUDIT);
            if (flag) {
                prize.setAuditStatus(Constants.PRIZE_RULE_NEED_AUDIT);
            }
        } catch (Exception e) {
            log.error("奖品审核异常:", e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(final Rule rule) {
        if (getPriority() < rule.getPriority()) {
            return rule.getPriority();
        } else {
            return this.priority;
        }
    }

}
