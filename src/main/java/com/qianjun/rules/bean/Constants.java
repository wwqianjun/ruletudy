package com.qianjun.rules.bean;

/**
 * 通用常量类
 */
public interface Constants {
    /**
     * 奖品规则审核状态未知
     */
    public static final byte    PRIZE_RULE_REVIEW_STATUS_UNKNOWN               = 3;
    /**
     * 奖品规则审核状态通过
     */
    public static final byte    PRIZE_RULE_REVIEW_STATUS_PASS                  = 1;
    /**
     * 奖品规则审核状态不通过
     */
    public static final byte    PRIZE_RULE_REVIEW_STATUS_NO_PASS               = 2;

    public static final byte    PRIZE_RULE_NEED_AUDIT                          = 1;

    public static final byte    PRIZE_RULE_NOT_NEED_AUDIT                      = 0;

    /**
     * 奖品规则审核状态描述
     */
    public static final String  PRIZE_RULE_REVIEW_STATUS_PASS_RESULT           = "无需校验登录状态，直接通过";
    public static final String  PRIZE_RULE_WECHAT_REVIEW_STATUS_PASS_RESULT    = "校验wechat已登录";
    public static final String  PRIZE_RULE_WECHAT_REVIEW_STATUS_NO_PASS_RESULT = "校验wechat未登录";
    public static final String  PRIZE_RULE_APP_REVIEW_STATUS_PASS_RESULT       = "校验app已登录";
    public static final String  PRIZE_RULE_APP_REVIEW_STATUS_NO_PASS_RESULT    = "校验app未登录";
    public static final String  PRIZE_RULE_REVIEW_STATUS_UNKNOWN_RESULT        = "校验登录状态未知";
    public static final String  PRIZE_RULE_FIRST_APP_REVIEW_STATUS_PASS_RESULT = "校验app已首次登录";
    public static final String  PRIZE_RULE_SECOND_APP_REVIEW_STATUS_PASS_RESULT = "校验app非首登或已发首登奖";

    /**
     * 库存预警数20%
     */
    public static final Integer STOCK_WARNING_NUM_TWENTYPERCENT                = 20;
    /**
     * 库存预警数10%
     */
    public static final Integer STOCK_WARNING_NUM_TENPERCENT                   = 10;
    /**
     * 库存预警数1
     */
    public static final Integer STOCK_WARNING_NUM_ONE                          = 1;

    /**
     * 库存预警数0
     */
    public static final Integer STOCK_WARNING_NUM_ZERO                         = 0;

    /**
     * 活动结束间隔天数
     */
    public static final Integer ACTIVITY_END_INTERVAL_DAY                      = 3;

    /**
     * 活动奖品规则redis缓存key
     */

    /**
     * 每次穷举行记录数
     */
    public static final Integer EACH_EXHAUSTION_ROWS_NUM                       = 200;

    /**
     * 活动未发奖
     */
    public static final Byte    ACTIVITY_GIVING_STATUS_REJECT                  = -1;
    public static final Byte    ACTIVITY_GIVING_STATUS_NOT_DONE                = 0;
    public static final Byte    ACTIVITY_GIVING_STATUS_DONE                    = 1;
    /**
     * 标识(2): 自动发奖占位
     */
    public static final Byte    ACTIVITY_GIVING_STATUS_AUTO                    = 2;

    public static final Integer TASK_MSG_TYPE_EMALI                            = 2;
    public static final String  TASK_MSG_SERVICE_TYPE_EMALI                    = "2";
    /**
     * 活动组件keyredis缓存key
     */
    public static final String  ACTIVITY_MODULE_KEY_REDIS_CACHE_KEY            = "activity_module_key_redis_cache_key_";
    /**
     * 奖品发放扣库存预警
     */
    public static final Integer PRIZE_AWARD_STOCK_WARNING_TYPE                 = 1;

    /**
     * 奖品占用库存预警预警
     */
    public static final Integer PRIZE_OCCUPY_STOCK_WARNING_TYPE                = 2;

    /**
     * 库存预警默认接收邮件信息
     */
    public static final String  STOCK_WARNING_DEFAULT_TO_MAILS                 = "itqianjun2011@123.com";
    public static final String  STOCK_WARNING_MAIL_TEMPLATE_NO                 = "MWC-001";
    public static final String  PRIZE_AWARD_STOCK_WARNING_MAIL_TITLE           = "活动-%s奖品库存预警";
    public static final String  PRIZE_AWARD_STOCK_WARNING_MAIL_MSG             = "Hi %s，</br>%s活动<font color='red'> %s </font>中奖品<font color='red'> %s </font>库存总数为<font color='red'> %s </font>个，已被领取<font color='red'>%s</font>个，剩余奖品数量为<font color='red'>%s</font>个，如有需要请至奖品管理中及时调整奖品数量。";

    public static final String  PRIZE_OCCUPY_STOCK_WARNING_MAIL_TITLE          = "活动-%s奖品占用库存预警";
    public static final String  PRIZE_OCCUPY_STOCK_WARNING_MAIL_MSG            = "Hi %s，</br>%s活动<font color='red'> %s </font>中奖品<font color='red'> %s </font>库存总数为<font color='red'> %s </font>个，已被占用<font color='red'> %s </font>个（已发奖%s个，未发奖%s个），剩余奖品数量为<font color='red'> %s </font>个，如有需要请至奖品管理中及时调整奖品数量。";

    public static final String  HTML_BLANK                                     = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

    /**
     * 活动进行中（上架）
     */
    public static final Byte    ACTIVITY_EFFECTIVE                             = 1;

    /**
     * 领奖状态-不发奖
     */
    public static final Byte    PRIZE_WINNING_REFUSE_GIVING                    = -1;

    /**
     * 临时用户还库存默认时间-15min
     */
    public static final Integer STOCK_TEMPORARY_TIME_DEFAULT                   = 15;

    /**
     * pba_lottery_detail-标识中奖
     */
    public static final Byte    PBA_LOTTERY_DETAIL_WINNING                     = 1;

    /**
     * 临时用户
     */
    public static final String  PBA_MODULE_TEMPORARY_USER                      = "-1";

    /**
     * 每次穷举行记录数---用来开多线程
     */
    public static final int     THREAD_NUM_DEFAULT                             = 10;

    /**
     * 每次穷举行记录数最大值---用来开多线程
     */
    public static final int     THREAD_NUM_MAX                                 = 5;

    /**
     * 缓存时间---1天
     */
    public static final int     CACHE_ONE_DAY                                  = 60 * 60 * 24;

    /**
     * 规则校验---审核
     */

    public static final String  needAudit                                      = "needAudit";

    /**
     * offer channe deafult
     */
    public static final String  OFFER_CHANNEL_DEAFULT                          = "1";

    /**
     * 发奖渠道，最福利----1
     */
    public static final Byte    GIVE_TYPE_WELFARE                              = 1;
}
