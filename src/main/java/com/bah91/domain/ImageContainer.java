package com.bah91.domain;

/**
 * Created by bah91 on 11/08/17.
 */
public class ImageContainer {

    private String filename;
    private String url;

    public ImageContainer(String filename, String url) {
        this.filename = filename;
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageContainer{" +
                "filename='" + filename + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
