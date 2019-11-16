package com.thexsolution.propertyprojectf11.Model;

public class Credit {

    public String nameIcon;
    public String iconURL;
    public String attributionURL;
    public String iconMadeBy;
    public String creativeCommon;

    public Credit(String nameIcon, String iconURL, String attributionURL, String iconMadeBy, String creativeCommon) {
        this.nameIcon = nameIcon;
        this.iconURL = iconURL;
        this.attributionURL = attributionURL;
        this.iconMadeBy = iconMadeBy;
        this.creativeCommon = creativeCommon;
    }

    public String getCreativeCommon() {
        return creativeCommon;
    }

    public void setCreativeCommon(String creativeCommon) {
        this.creativeCommon = creativeCommon;
    }

    public String getIconMadeBy() {
        return iconMadeBy;
    }

    public void setIconMadeBy(String iconMadeBy) {
        this.iconMadeBy = iconMadeBy;
    }

    public String getNameIcon() {
        return nameIcon;
    }

    public void setNameIcon(String nameIcon) {
        this.nameIcon = nameIcon;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getAttributionURL() {
        return attributionURL;
    }

    public void setAttributionURL(String attributionURL) {
        this.attributionURL = attributionURL;
    }
}
