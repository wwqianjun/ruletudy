package com.qianjun.rules.rule;

import com.qianjun.rules.bean.ActivityBean;
import com.qianjun.rules.bean.Constants;
import com.qianjun.rules.bean.PrizeBean;
import com.qianjun.rules.core.Rule;
import com.qianjun.rules.core.impl.BasicRule;

import com.qianjun.rules.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * 针对是否登入规则校验
 */
@Slf4j
public class CompositeLoginWinningRule extends BasicRule {

    private int                 priority = 2;

    private String              name     = "LGL001";

    public static final String isNotFirstLogin = "NO";

    private Map<String, Object> params   = null;

    @SuppressWarnings("unchecked")
    @Override
    public void execute() throws Exception {
        Object oPrize = getParams().get("prizeWinning");
        if (oPrize == null) {
            log.info(" prizeWinning is null !");
            return;
        }
        PrizeBean prize = (PrizeBean) oPrize;
        if (prize.getReview() != null && 1 == prize.getReview().intValue()) {
            log.info("prizeWinning 校验通过，无需重复校验 !");
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
        Object oActivity = getParams().get("pbaActivityDO");
        if (oActivity == null) {
            log.info("activity is null !");
            return;
        }
        ActivityBean activity = (ActivityBean) oActivity;
        try {
            boolean loginAPP = false;
            boolean loginWechat = false;
            boolean firstLoginApp = false;
            Integer validTime = 0;
            for (Iterator<JSONObject> it = jsonRules.iterator(); it.hasNext();) {
                JSONObject ruleJson = (JSONObject) it.next();
                String name = ruleJson.getString("name");
                String val = ruleJson.getString("val");
                if (StringUtils.isBlank(val)) {
                    continue;
                }
                if (StringUtils.equalsIgnoreCase(name, "valid_time")) {
                    validTime = Integer.valueOf(val);
                }
                if (StringUtils.equalsIgnoreCase(name, "loginWechat")) {
                    loginWechat = Boolean.valueOf(val);
                }
                if (StringUtils.equalsIgnoreCase(name, "loginAPP")) {
                    loginAPP = Boolean.valueOf(val);
                }
                if (StringUtils.equalsIgnoreCase(name, "firstLoginApp")) {
                    firstLoginApp = Boolean.valueOf(val);
                }
            }
            // 直接设置为通过
            if (!loginAPP && !loginWechat && !firstLoginApp) {
                prize.setReview(Constants.PRIZE_RULE_REVIEW_STATUS_PASS);
                prize.setReviewResult(Constants.PRIZE_RULE_REVIEW_STATUS_PASS_RESULT);
                return;
            }
            //设置校验的开始时间，结束时间
            String endTime = null;
            String startTime = DateTimeUtil.dateFormat(DateTimeUtil.FORMATE_FULL, prize.getGmtCreated());
            if (validTime != null) {
                endTime = DateTimeUtil.plusHours(DateTimeUtil.FORMATE_FULL, prize.getGmtCreated(), validTime);
            } else {
                endTime = DateTimeUtil.plusHours(DateTimeUtil.FORMATE_FULL, activity.getEndTime(), 1);
            }

            //验证首次登录app
            if (firstLoginApp) {
                if ("1".equals(getParams().get("isFirstLogin"))) {
                    prize.setReview(Constants.PRIZE_RULE_REVIEW_STATUS_PASS);
                    prize.setReviewResult(Constants.PRIZE_RULE_FIRST_APP_REVIEW_STATUS_PASS_RESULT);
                    prize.setFirstLoginAward(true);
                    return;
                } else if ( isNotFirstLogin.equals( getParams().get("isFirstLogin") ) ) {
                    // 对登录写个特殊逻辑（兼容task执行），规则要求首次登录&用户执行非首次登录
                    log.info("accountId={}, 要求首登,但不是, isFirstLogin={}",prize.getUserId(), getParams().get("isFirstLogin"));
                    prize.setReview(Constants.PRIZE_RULE_REVIEW_STATUS_NO_PASS);
                    prize.setReviewResult(Constants.PRIZE_RULE_SECOND_APP_REVIEW_STATUS_PASS_RESULT);
                    return;
                }
            }
            //  验证app
            if (loginAPP == true && loginWechat == false) {
                checkAppRule(prize, startTime, endTime);
                return;
            }
            //  验证wechat
            if (loginAPP == false && loginWechat == true) {
                checkWechatRule(prize, startTime, endTime);
                return;
            }
            //  2个其中一个过了，其他不执行了
            if (loginAPP == true && loginWechat == true) {
                checkAppRule(prize, startTime, endTime);
                if (Constants.PRIZE_RULE_REVIEW_STATUS_PASS == prize.getReview()) {
                    return;
                }
                checkWechatRule(prize, startTime, endTime);
                return;
            }
        } catch (Exception e) {
            prize.setReview(Constants.PRIZE_RULE_REVIEW_STATUS_UNKNOWN);
            prize.setReviewResult(Constants.PRIZE_RULE_REVIEW_STATUS_UNKNOWN_RESULT);
            log.error("校验app,wechar规则失败", e);
        }
    }

    /**
     * 校验App规则
     * 
     * @param prize
     * @param startTime
     * @param endTime
     * @throws Exception
     */
    public void checkAppRule(PrizeBean prize, String startTime, String endTime) throws Exception {
        try {
//            Boolean status = LoginApp.check(prize.getUserId(), startTime, endTime);
            Boolean status = true;
            if (status) {
                prize.setReview(Constants.PRIZE_RULE_REVIEW_STATUS_PASS);
                prize.setReviewResult(Constants.PRIZE_RULE_APP_REVIEW_STATUS_PASS_RESULT);
            } else {
                prize.setReview(Constants.PRIZE_RULE_REVIEW_STATUS_NO_PASS);
                prize.setReviewResult(Constants.PRIZE_RULE_APP_REVIEW_STATUS_NO_PASS_RESULT);
            }
        } catch (Exception e) {
            log.error("校验app规则失败", e);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 校验wechat规则
     * 
     * @param prize
     * @param startTime
     * @param endTime
     * @throws Exception
     */
    public void checkWechatRule(PrizeBean prize, String startTime, String endTime) throws Exception {
        try {

            // 微信校验逻辑省略
//            JSONObject result = LoginWechat.checkLogin(prize.getUserId());
            JSONObject result = new JSONObject();
            result.put("openid","openid");

            boolean loginStatus = false;
            if (result != null) {

                String openId = result.getString("openid");
                if (StringUtils.isNotBlank(openId)) {
                    loginStatus = true;
                }
            }
            if (loginStatus) {
                prize.setReview(Constants.PRIZE_RULE_REVIEW_STATUS_PASS);
                prize.setReviewResult(Constants.PRIZE_RULE_WECHAT_REVIEW_STATUS_PASS_RESULT);
            } else {
                prize.setReview(Constants.PRIZE_RULE_REVIEW_STATUS_NO_PASS);
                prize.setReviewResult(Constants.PRIZE_RULE_WECHAT_REVIEW_STATUS_NO_PASS_RESULT);
            }
        } catch (Exception e) {
            log.error("校验wechat规则失败", e);
            throw new Exception(e.getMessage());
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
