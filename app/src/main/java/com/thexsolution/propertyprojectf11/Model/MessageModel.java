package com.thexsolution.propertyprojectf11.Model;

public class MessageModel {

    private String id;
    private String userId;
    private String name;
    private String text;
    private String createdAt;

    public MessageModel() {
    }

    public MessageModel(String id, String userId, String name, String text, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.text = text;
        this.createdAt = createdAt;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
