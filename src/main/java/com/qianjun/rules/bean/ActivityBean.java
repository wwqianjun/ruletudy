package com.qianjun.rules.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ActivityBean extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -3616415617480759574L;

    /**
     * 业务逻辑字段，间隔天
     */
    private Integer intervalDay;

    private String            code;

    private String            name;

    private String            department;

    private String            channelId;

    private Byte              status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date              displayBeginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date              displayEndTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date              beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date              endTime;

    private String            bizOrigin;

    private String            label;

    private String            url;

    private String            isDeleted;

    private String            creator;

    private Date              gmtCreated;

    private String            modifier;

    private Date              gmtModified;
    //状态： 0-未开始 1-进行中 2-已结束
    private Byte              activityState;
    //查询的渠道数组
    private String[]          channelIds;

    public ActivityBean() {
        //log.info("new PbaActivityDO");
	}

    public ActivityBean(Integer intervalDay) {
    	this.intervalDay=intervalDay;
	}
 
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getDisplayBeginTime() {
        return displayBeginTime;
    }

    public void setDisplayBeginTime(Date displayBeginTime) {
        this.displayBeginTime = displayBeginTime;
    }

    public Date getDisplayEndTime() {
        return displayEndTime;
    }

    public void setDisplayEndTime(Date displayEndTime) {
        this.displayEndTime = displayEndTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBizOrigin() {
        return bizOrigin;
    }

    public void setBizOrigin(String bizOrigin) {
        this.bizOrigin = bizOrigin == null ? null : bizOrigin.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Byte getActivityState() {
        return activityState;
    }

    public void setActivityState(Byte activityState) {
        this.activityState = activityState;
    }

    public String[] getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(String[] channelIds) {
        this.channelIds = channelIds;
    }

	public Integer getIntervalDay() {
		return intervalDay;
	}

	public void setIntervalDay(Integer intervalDay) {
		this.intervalDay = intervalDay;
	}

}
