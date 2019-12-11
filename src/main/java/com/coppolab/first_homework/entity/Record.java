package com.coppolab.first_homework.entity;

import javax.persistence.*;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int recordId;

    //file path
    private String filename;

    private String bucketName;
    //File name in the bucket
    private String objectName;

    @ManyToOne
    private User author;

    @Transient
    private int authorId;



    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
