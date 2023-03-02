package com.binus.thesis.fisheryapp.base.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class GeneratorUtils {

    public static String generateId(String prefix, Date date, int numdigit) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

        Random rand = new Random();

        String dateFormat = "";

        if (date != null)
            dateFormat = sdf.format(date);

        String[] arr = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        String randomkey = "";
        for (int i = 0; i < numdigit; i++) {

            int random = rand.nextInt(10);
            randomkey += arr[random];
        }

        return prefix + dateFormat + randomkey;
    }

}
