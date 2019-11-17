package com.raushan.rnews;

public class NewsItem {

        private String ImageUrl;
        private String author;
        private String title, description, publishedat;

    public NewsItem(String imageUrl, String author, String title, String description, String publishedat) {
        ImageUrl = imageUrl;
        this.author = author;
        this.title = title;
        this.description = description;
        this.publishedat = publishedat;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedat() {
        return publishedat;
    }
}
