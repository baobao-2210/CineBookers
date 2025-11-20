package com.example.bcck;

public class Document {
    private String authorName;
    private String title;
    private String docType; // PDF hoặc PPT
    private String subject;
    private String teacher;
    private String major;
    private int downloads;
    private int likes;
    private float rating;

    public Document(String authorName, String title, String docType, String subject,
                    String teacher, String major, int downloads, int likes, float rating) {
        this.authorName = authorName;
        this.title = title;
        this.docType = docType;
        this.subject = subject;
        this.teacher = teacher;
        this.major = major;
        this.downloads = downloads;
        this.likes = likes;
        this.rating = rating;
    }

    // Getters
    public String getAuthorName() { return authorName; }
    public String getTitle() { return title; }
    public String getDocType() { return docType; }
    public String getSubject() { return subject; }
    public String getTeacher() { return teacher; }
    public String getMajor() { return major; }
    public int getDownloads() { return downloads; }
    public int getLikes() { return likes; }
    public float getRating() { return rating; }
}