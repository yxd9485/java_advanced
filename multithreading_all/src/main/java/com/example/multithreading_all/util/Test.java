package com.example.multithreading_all.util;

import java.util.Optional;

public class Test{


    public static void main(String[] args){
        String qrcodeUrl = "HTTP://VJ1.TV/JZ/N/BPYZUH7C65MF";
        String sweepstr = qrcodeUrl.substring(qrcodeUrl.lastIndexOf("/") + 1);
        System.out.println(sweepstr);

        //    String a = "HTTP://VJ1.TV/JZ/N/BPYZUH7C65MF";
        String a = "x";
        String b = null;
  //      final String substring = a.substring(a.lastIndexOf("/")-1).substring(0,1);
   //     final String s = Optional.ofNullable(a).orElse("0");
    //    System.out.println(s);
     //   System.out.println(Optional.ofNullable(null).orElse("0")+"--------");

        switch (a) {
            case "W":
            case "X":
                b = a+"+"+a;
                break;
            default:
                b = a + a +"---=--=-=";
                break;
        }
        System.out.println(b);



    }
    //判断素数
    private static boolean isPrime(int n){
        boolean flag = true;
        if(n==1)
            flag = false;
        else{
            for(int i=2;i<=Math.sqrt(n);i++){
                if((n%i)==0 || n==1){
                    flag = false;
                    break;
                }
                else
                    flag = true;
            }
        }
        return flag;
    }
}