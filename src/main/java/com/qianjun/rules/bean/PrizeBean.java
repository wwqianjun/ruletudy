package com.qianjun.rules.bean;

import java.util.Date;

public class PrizeBean extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = -8000624968951536668L;

    private String moduleKey;

    private Long moduleDetailId;

    private String activityCode;

    private String moduleCode;

    private Long detailId;

    private String offerPackageCode;

    private String offerPackageProCode;

    private String prizePackageCode;

    private String prizePackageProCode;

    private String prizeName;

    private String channelId;

    private Byte userType;

    private String userId;

    private String userName;
    
    private String userMobile;
    
    private Byte givingStatus;//'领奖状态：-1不发奖,0未发奖，1已发奖,未确认2'

    private Byte review;// 1校验通过，2校验不通过

    private String reviewResult;
    
    private Byte auditStatus;//审核状态:无需审核0,未审核1，人工审核通过2，人工审核不通过3
    
    private Byte[] auditStatuss;// 审核状态:无需审核0,未审核1，人工审核通过2，人工审核不通过3

    private String extraInfo;

    private String isDeleted;

    private String creator;

    private Date gmtCreated;

    private String modifier;

    private Date gmtModified;
    
    private Boolean sendMsgFlag;

    // 首登已领取,
    private Boolean firstLoginAward = false;
    
    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Boolean getSendMsgFlag() {
        return sendMsgFlag;
    }

    public void setSendMsgFlag(Boolean sendMsgFlag) {
        this.sendMsgFlag = sendMsgFlag;
    }

    public Byte getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getModuleKey() {
        return moduleKey;
    }

    public void setModuleKey(String moduleKey) {
        this.moduleKey = moduleKey;
    }

    public Long getModuleDetailId() {
        return moduleDetailId;
    }

    public void setModuleDetailId(Long moduleDetailId) {
        this.moduleDetailId = moduleDetailId;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode == null ? null : activityCode.trim();
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getOfferPackageCode() {
        return offerPackageCode;
    }

    public void setOfferPackageCode(String offerPackageCode) {
        this.offerPackageCode = offerPackageCode == null ? null : offerPackageCode.trim();
    }

    public String getOfferPackageProCode() {
        return offerPackageProCode;
    }

    public void setOfferPackageProCode(String offerPackageProCode) {
        this.offerPackageProCode = offerPackageProCode == null ? null : offerPackageProCode.trim();
    }

    public String getPrizePackageCode() {
        return prizePackageCode;
    }

    public void setPrizePackageCode(String prizePackageCode) {
        this.prizePackageCode = prizePackageCode == null ? null : prizePackageCode.trim();
    }

    public String getPrizePackageProCode() {
        return prizePackageProCode;
    }

    public void setPrizePackageProCode(String prizePackageProCode) {
        this.prizePackageProCode = prizePackageProCode == null ? null : prizePackageProCode.trim();
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Byte getGivingStatus() {
        return givingStatus;
    }

    public void setGivingStatus(Byte givingStatus) {
        this.givingStatus = givingStatus;
    }
    
    public Byte[] getAuditStatuss() {
		return auditStatuss;
	}

	public void setAuditStatuss(Byte[] auditStatuss) {
		this.auditStatuss = auditStatuss;
	}

	public Byte getReview() {
        return review;
    }

    public void setReview(Byte review) {
        this.review = review;
    }

    public String getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult == null ? null : reviewResult.trim();
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo == null ? null : extraInfo.trim();
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

    public Boolean getFirstLoginAward() {
        return firstLoginAward;
    }

    public void setFirstLoginAward(Boolean firstLoginAward) {
        this.firstLoginAward = firstLoginAward;
    }
}