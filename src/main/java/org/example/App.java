package org.example;

import org.example.article.Article;
import org.example.article.ArticleController;
import org.example.member.Member;
import org.example.member.MemberController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App {
    ArticleController articleController;
    List<Article> articleList;
    List<Member> memeberList;
    LoginedUser loginedUser;
    MemberController memberController;
    Request request;


    public App(){
        articleController = new ArticleController();
        articleList = new ArrayList<>();
        memeberList = new ArrayList<>();
        loginedUser = new LoginedUser();
        memberController = new MemberController();
        request = new Request();
    }

    public void run(){


        System.out.println("== 시스템 시작==");
        Member member1 = new Member( 1, "user1", "1234", LocalDate.now().toString());
        memeberList.add(member1);
        Member member2 = new Member( 2, "user2", "1234", LocalDate.now().toString());
        memeberList.add(member2);
        Member member3 = new Member( 3, "user3", "1234", LocalDate.now().toString());
        memeberList.add(member3);

        while (true) {
            System.out.printf("명령) ");
            String command = Container.getSc().nextLine().trim();

            switch (command){
                case "종료":
                    return;
                case "등록":
                    new ArticleController().create(loginedUser, memberController, memeberList, articleList);
                    break;
                case "목록":
                    new ArticleController().list(articleList);
                    break;
                case "삭제":
                    new ArticleController().delete(loginedUser, memberController, memeberList, articleList);
                    break;
                case "수정":
                    new ArticleController().modify(loginedUser, memberController, memeberList, articleList);
                    break;
                case "회원가입":
                    new MemberController().register(memeberList);
                    break;
                case "회원가입_List":
                    new MemberController().memberList(memeberList);
                    break;
                case "로그인":
                    memberController.login(loginedUser,memeberList);
                    break;
                case "로그아웃":
                    new MemberController().logOut(loginedUser);
                    break;
            }

//            if (command.equals("종료")) {
//                break;
//            } else if (command.equals("등록")) {
//                new ArticleController().create(loginedUser, memberController, memeberList, articleList);
//
//            } else if (command.equals("목록")) {
//                new ArticleController().list(articleList);
//
//            } else if (command.equals("삭제")) {
//                new ArticleController().delete(loginedUser, memberController, memeberList, articleList);
//
//            } else if (command.equals("수정")) {
//                new ArticleController().modify(loginedUser, memberController, memeberList, articleList);
//
//            } else if (command.equals("회원가입")) {
//                new MemberController().register(memeberList);
//
//            } else if (command.equals("회원목록_List")) {
//                new MemberController().memberList(memeberList);
//
//            }  else if(command.equals("로그인")){
//                memberController.login(loginedUser,memeberList);
//
//            } else if(command.equals("로그아웃")){
//                new MemberController().logOut(loginedUser);
//            }
        }
    }
}
