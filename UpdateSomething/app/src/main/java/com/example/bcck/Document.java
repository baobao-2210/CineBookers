package com.example.bcck;

import android.os.Parcel;
import android.os.Parcelable;

// Class Document phải implements Parcelable
public class Document implements Parcelable {

    private String authorName;
    private String title;
    private String docType;
    private String subject;
    private String teacher;
    private String major;
    private int downloads;
    private int likes;
    private float rating;

    // 1. Constructor Gốc (dùng để tạo dữ liệu ban đầu)
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

    // ************ Bắt đầu phần Parcelable (Để Intent có thể truyền đối tượng) ************

    // 2. Constructor đọc từ Parcel (Dùng khi nhận Intent)
    protected Document(Parcel in) {
        authorName = in.readString();
        title = in.readString();
        docType = in.readString();
        subject = in.readString();
        teacher = in.readString();
        major = in.readString();
        downloads = in.readInt();
        likes = in.readInt();
        rating = in.readFloat();
    }

    // 3. CREATOR (Bắt buộc để tạo đối tượng từ Parcel)
    public static final Creator<Document> CREATOR = new Creator<Document>() {
        @Override
        public Document createFromParcel(Parcel in) {
            return new Document(in);
        }

        @Override
        public Document[] newArray(int size) {
            return new Document[size];
        }
    };

    // 4. writeToParcel (Dùng khi gửi đối tượng bằng Intent)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(authorName);
        dest.writeString(title);
        dest.writeString(docType);
        dest.writeString(subject);
        dest.writeString(teacher);
        dest.writeString(major);
        dest.writeInt(downloads);
        dest.writeInt(likes);
        dest.writeFloat(rating);
    }

    // 5. describeContents (Thường trả về 0)
    @Override
    public int describeContents() {
        return 0;
    }

    // ************ Getter Methods (Để code DocumentFragment hoạt động) ************

    public String getAuthorName() {
        return authorName;
    }

    public String getTitle() {
        return title;
    }

    public String getDocType() {
        return docType;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getMajor() {
        return major;
    }

    public int getDownloads() {
        return downloads;
    }

    public int getLikes() {
        return likes;
    }

    public float getRating() {
        return rating;
    }
}