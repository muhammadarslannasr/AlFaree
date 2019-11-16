package com.thexsolution.propertyprojectf11.Model;

public class FriendlyMessage {

    private String text;
    private String name;
    private String imageUrl;
    private String userID;
    public String timestamp;
    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name,String userID,String timestamp) {
        this.text = text;
        this.name = name;
        this.userID = userID;
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String url) {
        this.imageUrl = url;
    }

}
