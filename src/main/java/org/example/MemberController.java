package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {


    Scanner sc;
    public MemberController(){

        sc = new Scanner(System.in);
    }
    public void login(LoginedUser loginedUser, List<Member> memeberList ){
        if(loginedUser.getLoginId() != null){
            System.out.println("로그인 중입니다.");
            return;
        }
        System.out.println("아이디를 입력하세요");
        System.out.printf("아이디 : ");
        String userId = sc.nextLine().trim();

        for(int i = 0; i < memeberList.size(); i++){
            if(userId.equals(memeberList.get(i).getNameId())){
                while(true){
                    System.out.println("비밀번호를 입력하세요");
                    System.out.printf("비밀번호 : ");
                    String password = sc.nextLine().trim();

                    if(password.equals(memeberList.get(i).getPassword())){
                        loginedUser.setLoginId(userId);
                        loginedUser.setLoginPassword(password);
                        System.out.println("로그인을 환영합니다. "+ loginedUser.getLoginId()+"님");
                        return;
                    } else {
                        System.out.println("== 비밀번호를 틀렸습니다. ==");
                    }
                }
            } else if(!userId.equals(memeberList.get(i).getNameId())){
                System.out.println("아이디가 존재하지 않습니다.");
                return;
            }
        }
    }
}
