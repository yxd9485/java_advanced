package com.example.multithreading_all.util;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;



/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/7/11 15:32
 */
public class Demo {

    public String getA() {
        return a;
    }

    private String a;

    @Data
    public static class TestD {
        private String beginTime;
        private String endTime;
    }

    public static long getTimeStamp(String dateStr, String pattern){
        if(StringUtils.isEmpty(dateStr)) return System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date == null ? System.currentTimeMillis() : date.getTime();
    }


    public static void main(String[] args) {
        TestD testD = new TestD();

        testD.setBeginTime("ssss");
        testD.setEndTime("ssssss");
        System.out.println(testD.getEndTime());
        int a = 0;
        for (int i=0;i<50;i++) {
            a++;
        }
        System.out.println(a);








    }




}
