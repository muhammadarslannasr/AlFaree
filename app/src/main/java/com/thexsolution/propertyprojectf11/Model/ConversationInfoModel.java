package com.thexsolution.propertyprojectf11.Model;

public class ConversationInfoModel {
    private String ObjectName;
    private String objectToken;
    private String seenStatus;
    private String chanelName;
    private String messageTime;

    public ConversationInfoModel() {
    }

    public ConversationInfoModel(String objectName, String objectToken, String seenStatus, String chanelName, String messageTime) {
        ObjectName = objectName;
        this.objectToken = objectToken;
        this.seenStatus = seenStatus;
        this.chanelName = chanelName;
        this.messageTime = messageTime;
    }

    public String getObjectName() {
        return ObjectName;
    }

    public void setObjectName(String objectName) {
        ObjectName = objectName;
    }

    public String getObjectToken() {
        return objectToken;
    }

    public void setObjectToken(String objectToken) {
        this.objectToken = objectToken;
    }

    public String getSeenStatus() {
        return seenStatus;
    }

    public void setSeenStatus(String seenStatus) {
        this.seenStatus = seenStatus;
    }

    public String getChanelName() {
        return chanelName;
    }

    public void setChanelName(String chanelName) {
        this.chanelName = chanelName;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
