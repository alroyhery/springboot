package com.tugas.project1.services;

import com.tugas.project1.interfaces.UserInterface;
import com.tugas.project1.models.User;
import com.tugas.project1.repositories.UserRepository;
import java.math.BigInteger;
import java.security.MessageDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(User user) throws Exception {
        String hashed = this.hash(user.getPassword());
        user.setPassword(hashed);
        
        if(user.getEmail().equals("")){
            throw new IllegalArgumentException("Email cannot be null!"); 
        }
        
        if(user.getName().equals("")){
            throw new IllegalArgumentException("Name cannot be null!"); 
        }
        
        if(user.getPassword().equals("")){
            throw new Exception("Password cannot be null!"); 
        }
        
        if(user.getName().contentEquals("~!@#$%^&*()_+-=<>,.?/")){
            throw new IllegalArgumentException("Symbols on username is not allowed!");
        }
        
        if(user.getEmail().contentEquals("~!#$%^&*()<>?,/")){
            throw new IllegalArgumentException("Symbols other than . and @ are not allowed!");
        }
        
        if(user.getName().equals("1234567890")){
            throw new IllegalArgumentException("Username without alphabets is not allowed!");
        }

        this.userRepository.save(user);
    }

    @Override
    public User auth(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        }

        if (!this.match(user.getPassword(), password)) {
            return null;
        }

        return user;
    }

    private String hash(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDiggest = md.digest(password.getBytes());

        BigInteger no = new BigInteger(1, messageDiggest);

        String hashText = no.toString(16);
        while (hashText.length() < 32) {
            hashText = "0" + hashText;
        }

        return hashText;
    }

    private boolean match(String password, String rawPassword)
            throws Exception {
        rawPassword = this.hash(rawPassword);
        return password.equals(rawPassword);
    }

}
