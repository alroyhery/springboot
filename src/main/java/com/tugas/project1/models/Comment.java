/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ASUS TUF
 */
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(name="content")
    private String content;

    

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
}
