package com.google.mediaplayerdemo;

/**
 * Created by Rajesh Kumar Dawar on 28-03-2017.
 */

public class FileBean {
    int image;
    String title;

    public FileBean() {
    }

    public FileBean(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
