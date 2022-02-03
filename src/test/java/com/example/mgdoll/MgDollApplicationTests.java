package com.example.mgdoll;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class MgDollApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void randomCode(){
        Random random = new Random();
        for (int i=0;i<30;i++){
            int randomNum = random.nextInt(1000000);
            String codeContext = String.format("%06d", randomNum);
            System.out.println(codeContext);
        }
    }

}
