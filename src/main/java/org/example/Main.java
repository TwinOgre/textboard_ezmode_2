package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 시스템 시작==");


        while(true){
            System.out.printf("명령) ");
            String command = sc.nextLine().trim();
            if(command.equals("종료")){
                break;
            }
        }


        sc.close();
    }
}