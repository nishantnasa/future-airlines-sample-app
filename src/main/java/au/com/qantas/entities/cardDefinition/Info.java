package au.com.future-airlines.entities.cardDefinition;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by boses on 26/06/2018.
 */
public class Info {

    public String name;
    public List<String> contentCategories;
    public List<String> tags;
    public Boolean serviceMessage;
    public Long startDate;
    public Long endDate;
    public Integer ttl;
    public String delivery;
    public Integer priority;
    public Integer sensitiveContent;
    public String targetAudience;
    public List<String> channel;
    public String termsAndConditions;
    public String feedbackType;
    public Integer pool;
    public String vertical;
    public String business;
    public List<String> displayCondition;
    public Boolean campaign;
    public List<String> geofence;
    public String region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContentCategories() {
        return contentCategories;
    }

    public void setContentCategories(List<String> contentCategories) {
        this.contentCategories = contentCategories;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getServiceMessage() {
        return serviceMessage;
    }

    public void setServiceMessage(Boolean serviceMessage) {
        this.serviceMessage = serviceMessage;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getSensitiveContent() {
        return sensitiveContent;
    }

    public void setSensitiveContent(Integer sensitiveContent) {
        this.sensitiveContent = sensitiveContent;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public List<String> getChannel() {
        return channel;
    }

    public void setChannel(List<String> channel) {
        this.channel = channel;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public Integer getPool() {
        return pool;
    }

    public void setPool(Integer pool) {
        this.pool = pool;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public List<String> getDisplayCondition() {
        return displayCondition;
    }

    public void setDisplayCondition(List<String> displayCondition) {
        this.displayCondition = displayCondition;
    }

    public Boolean getCampaign() {
        return campaign;
    }

    public void setCampaign(Boolean campaign) {
        this.campaign = campaign;
    }

    public List<String> getGeofence() {
        return geofence;
    }

    public void setGeofence(List<String> geofence) {
        this.geofence = geofence;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
