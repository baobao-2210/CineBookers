package com.example.bcck.poster;

import java.io.Serializable;

public class Document implements Serializable {

    // --- CÁC THUỘC TÍNH (FIELDS) ---
    private String authorName;
    private String title;
    private String docType;
    private String subject;
    private String teacher;
    private String major;
    private int downloads;
    private int likes;
    private float rating;

    private String uploaderName;
    private String year;
    private long uploadTimestamp;
    private String fileUrl;

    // THUỘC TÍNH MỚI CHO CHAT
    private String uploaderId;

    // --- CONSTRUCTORS ---

    public Document() {
        // Constructor rỗng (cần thiết cho Firebase hoặc Deserialization)
    }

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

        this.uploaderName = "Người dùng ẩn danh";
        this.year = "Chưa xác định";
        this.uploadTimestamp = System.currentTimeMillis();
        this.fileUrl = null;
        this.uploaderId = "default_id"; // Gán giá trị mặc định cho ID
    }

    // --- GETTERS ---
    public String getAuthorName() { return authorName; }
    public String getTitle() { return title; }
    public String getDocType() { return docType; }
    public String getSubject() { return subject; }
    public String getTeacher() { return teacher; }
    public String getMajor() { return major; }
    public int getDownloads() { return downloads; }
    public int getLikes() { return likes; }
    public float getRating() { return rating; }

    public String getUploaderName() { return uploaderName; }
    public String getYear() { return year; }
    public long getUploadTimestamp() { return uploadTimestamp; }
    public String getFileUrl() { return fileUrl; }

    // --- GETTER MỚI CHO CHAT ---
    public String getUploaderId() { return uploaderId; }

    // --- SETTERS (Rất quan trọng để gán dữ liệu sau khi khởi tạo, ví dụ: gán ID) ---

    public void setUploaderName(String uploaderName) { this.uploaderName = uploaderName; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    public void setYear(String year) { this.year = year; }

    // SETTER MỚI CHO CHAT
    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }
}