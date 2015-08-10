package com.example.urec.transitionsgo;

/**
 * Created by urec on 10.08.15.
 */
public class ListItem {
    private String largeImage;
    private String smallImage;
    private String title;
    private String subTitle;

    public ListItem(String largeImage, String smallImage, String title, String subTitle) {
        this.largeImage = largeImage;
        this.smallImage = smallImage;
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
