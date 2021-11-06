/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ASUS TUF
 */
@Entity
@Table(name = "thread")
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
            
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany
//    @JoinColumn(name = "thread_id")
  //  private List<Comment> comment = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @Column(name = "created_at")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

//    public void setComment(List<Comment> comment) {
//        this.comment = comment;
//    }
//
//    public List<Comment> getComment() {
//        return comment;
//    }
}
