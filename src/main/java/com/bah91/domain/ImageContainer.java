package com.bah91.domain;

/**
 * Created by bah91 on 11/08/17.
 */
public class ImageContainer {

    private String filename;
    private String url;
    private String description;

    public ImageContainer(String filename, String url, String description) {
        this.filename = filename;
        this.url = url;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ImageContainer{" +
                "filename='" + filename + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
