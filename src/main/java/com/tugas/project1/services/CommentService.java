/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1.services;

import com.tugas.project1.interfaces.CommentInterface;
import com.tugas.project1.models.Comment;
import com.tugas.project1.repositories.CommentRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS TUF
 */
@Service
public class CommentService implements CommentInterface {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public void send(Comment comment) {
        this.commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByUserId(long user_id) {
        return commentRepository.findByUserId(user_id);
    }

    @Override
    public List<Comment> findByThreadId(long thread_id) {
        return commentRepository.findByThreadId(thread_id);
    }
    
    @Override
    public void delete(long thread_id) {
        this.commentRepository.deleteById(thread_id);
    }
    
    @Override
    public Comment getById(long id){
        Optional< Comment> optional = commentRepository.findById(id);

        if (!optional.isPresent()) {
            throw new RuntimeException(" Todo not found for id :: " + id);
        }

        Comment comment = optional.get();
        return comment;
    }
}
