package com.tugas.project1.interfaces;

import com.tugas.project1.models.User;

public interface UserInterface {
    void register(User user) throws Exception;
    User auth(String email, String password) throws Exception;
}