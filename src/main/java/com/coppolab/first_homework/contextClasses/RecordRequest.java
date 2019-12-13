package com.coppolab.first_homework.contextClasses;

import com.coppolab.first_homework.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RecordRequest {

    @Autowired
    UserRepository userRepository;

    String filename;
    String nickname;


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
