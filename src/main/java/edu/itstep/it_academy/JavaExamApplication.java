package edu.itstep.it_academy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JavaExamApplication {


    public static void main(String[] args) {

//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        System.out.println(passwordEncoder.encode("teacher1") + "   teacher1");
//        System.out.println(passwordEncoder.encode("teacher2") + "   teacher2");
//        System.out.println(passwordEncoder.encode("teacher3") + "   teacher3");
//
//        System.out.println(passwordEncoder.encode("student1") + "   student1");
//        System.out.println(passwordEncoder.encode("student2") + "   student2");
//        System.out.println(passwordEncoder.encode("student3") + "   student3");
//        System.out.println(passwordEncoder.encode("student4") + "   student4");
//        System.out.println(passwordEncoder.encode("student5") + "   student5");
//        System.out.println(passwordEncoder.encode("student6") + "   student6");
//        System.out.println(passwordEncoder.encode("student7") + "   student7");
//        System.out.println(passwordEncoder.encode("student8") + "   student8");
//        System.out.println(passwordEncoder.encode("student9") + "   student9");
//        System.out.println(passwordEncoder.encode("student10") + "   student10");



        SpringApplication.run(JavaExamApplication.class, args);
    }

}
