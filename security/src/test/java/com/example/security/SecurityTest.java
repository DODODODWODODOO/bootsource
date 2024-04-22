package com.example.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SecurityTest {

    // SecurityConfig 의 passwordEncoder() 가 실핼되면서 주입됨
    @Autowired
    private PasswordEncoder passwordEncoder; // 비밀번호 암호화(encode), 원 비밀번호와 암호화된 비밀번호의 일치(matches) 여부

    @Test
    public void testEncoder() {
        String password = "1111"; // 원 비밀번호

        // 원 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(password);

        // password : 1111, encode password :
        // {bcrypt}$2a$10$Fv1b3/BtxV8kXZawjb36dOmDBTZU1K8Az98wzsKxn5Geo2kVtZPeG
        System.out.println("password : " + password + ", encode password : " + encodePassword);

        // matches(원 비밀번호, 암호화된 비밀번호) = true
        // (1111, {bcrypt}$2a$10$Fv1b3/BtxV8kXZawjb36dOmDBTZU1K8Az98wzsKxn5Geo2kVtZPeG)
        System.out.println(passwordEncoder.matches(password, encodePassword));

    }
}
