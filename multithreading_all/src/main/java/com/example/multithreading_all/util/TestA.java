package com.example.multithreading_all.util;

import java.util.Random;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/7/11 13:02
 */
public class TestA {
        public static void main(String[] args) {
            double aHitRate = 0.3;
            double bHitRate = 0.5;

            String winner = duel(aHitRate, bHitRate);
            System.out.println("胜利者是：" + winner);
        }

        public static String duel(double aHitRate, double bHitRate) {
            Random random = new Random();
            boolean aShot;
            boolean bShot;

            while (true) {
                aShot = random.nextDouble() <= aHitRate;
                bShot = random.nextDouble() <= bHitRate;

                if (aShot && !bShot) {
                    return "A";
                } else if (!aShot && bShot) {
                    return "B";
                }
            }
        }
    }

