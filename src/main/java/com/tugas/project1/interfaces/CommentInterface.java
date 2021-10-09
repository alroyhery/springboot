/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1.interfaces;

import com.tugas.project1.models.Comment;
import java.util.List;

/**
 *
 * @author ASUS TUF
 */
public interface CommentInterface {

    List<Comment> getAll();

    List<Comment> findByThreadId(long thread_id);

    List<Comment> findByUserId(long user_id);

    void send(Comment comment);
    
    void delete(long thread_id);
}
