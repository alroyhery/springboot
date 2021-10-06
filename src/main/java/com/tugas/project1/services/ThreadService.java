/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1.services;

import com.tugas.project1.interfaces.ThreadInterface;
import com.tugas.project1.models.Thread;
import com.tugas.project1.repositories.ThreadRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS TUF
 */
@Service
public class ThreadService implements ThreadInterface {

    @Autowired
    private ThreadRepository threadRepository;

    @Override
    public List<Thread> getAll() {
        return threadRepository.findAll();
    }   
    
        @Override
    public List<Thread> findByUserId(long user_id) {
        return threadRepository.findByUserId(user_id);
    }

    @Override
    public void store(Thread thread) {
        this.threadRepository.save(thread);
    }

    @Override
    public Thread getById(long id) {
        Optional< Thread> optional = threadRepository.findById(id);

        if (!optional.isPresent()) {
            throw new RuntimeException(" Todo not found for id :: " + id);
        }

        Thread thread = optional.get();
        return thread;
    }

    @Override
    public void delete(long id) {
        this.threadRepository.deleteById(id);
    }
    
    
    
    
}

