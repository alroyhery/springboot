/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1.interfaces;

import com.tugas.project1.models.Thread;
import java.util.List;

/**
 *
 * @author ASUS TUF
 */
public interface ThreadInterface {

    List<Thread> getAll();

    void store(Thread thread) throws Exception;

    Thread getById(long id);

    void delete(long id);

    List<Thread> findByUserId(long user_id);
}
