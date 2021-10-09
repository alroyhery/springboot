/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1.repositories;

import com.tugas.project1.models.Thread;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS TUF
 */
@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

    List<Thread> findByUserId(long user_id);
}
