package org.example;

import lombok.RequiredArgsConstructor;
import org.example.article.Article;
import org.example.article.ArticleController;
import org.example.member.Member;
import org.example.member.MemberController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class App {
    private final ArticleController articleController;


    public void run() {

        List<Member> memeberList = new ArrayList<>();

        System.out.println("== 시스템 시작==");
        Member member1 = new Member(1, "user1", "1234", LocalDate.now().toString());
        memeberList.add(member1);
        Member member2 = new Member(2, "user2", "1234", LocalDate.now().toString());
        memeberList.add(member2);
        Member member3 = new Member(3, "user3", "1234", LocalDate.now().toString());
        memeberList.add(member3);


        int lastMemberId = 1;

        LoginedUser loginedUser = new LoginedUser();
        MemberController memberController = new MemberController();

        while (true) {
            System.out.printf("명령) ");
            String command = Container.getSc().nextLine().trim();


            if (command.equals("종료")) {
                break;
            } else if (command.equals("등록")) {
                if (loginedUser.getLoginId() == null) {
                    System.out.println("게시물 등록을 하려면 먼저 로그인을 해주세요");
                    System.out.println("아이디가 없다면 회원가입을 해주세요");

                    memberController.login(loginedUser, memeberList);
                    if (loginedUser.getLoginId() == null) {
                        continue;
                    }
                }
                articleController.create();
            } else if (command.equals("목록")) {
                articleController.list();
            } else if (command.equals("삭제")) {
                if (loginedUser.getLoginId() == null) {
                    System.out.println("게시물 삭제를 하려면 먼저 로그인을 해주세요");

                    memberController.login(loginedUser, memeberList);
                    if (loginedUser.getLoginId() == null) {
                        continue;
                    }
                }
                articleController.delete();

            } else if (command.equals("수정")) {
                if (loginedUser.getLoginId() == null) {
                    System.out.println("게시물 수정을 하려면 먼저 로그인을 해주세요");

                    memberController.login(loginedUser, memeberList);
                    if (loginedUser.getLoginId() == null) {
                        continue;
                    }
                }
                articleController.update();

            } else if (command.equals("회원가입")) {
                Member member = new Member(lastMemberId, "0", "0", "0");

                // 아이디, 닉네임, 비밀번호, 비밀번호 확인
                // 중복 아이디 검증

                while (true) {
                    System.out.println("아이디를 입력하세요");
                    System.out.printf("아이디 : ");
                    String nameId = Container.getSc().nextLine().trim();
                    boolean isDuplicated = false;
                    for (Member member9 : memeberList) {
                        // 아이디 중복이 없다면
                        if (nameId.equals(member9.getNameId())) {
                            System.out.println("중복 아이디가 존재합니다.");
                            isDuplicated = true;

                        }
                    }
                    // 중복 아이디가 없는 경우
                    if (!isDuplicated) {
                        System.out.println("아이디 확인");
                        member.setNameId(nameId);
                        break;
                    }


                }

                // 1. 비번 입력 , 2. 비밀번호 확인 3. 비번 != 확인비번 틀렸다고 안내 다시입력

                System.out.println("비밀번호를 입력하세요");
                System.out.printf("비밀번호 : ");
                String password = Container.getSc().nextLine();

                while (true) {
                    System.out.printf("비밀번호 확인 : ");
                    String passwordConfirm = Container.getSc().nextLine();

                    if (password.equals(passwordConfirm)) {
                        member.setPassword(password);
                        break;
                    } else {
                        System.out.println("비밀번호가 틀렸습니다. 다시 입력해주세요");
                    }
                }
                System.out.println("비밀번호가 확인 되었습니다.");
                LocalDate now = LocalDate.now();
                member.setRegDate(now.toString());
                memeberList.add(member);

                System.out.println(member.getId() + " " + member.getNameId() + " " + member.getPassword() + " " + member.getRegDate());

                lastMemberId++;
            } else if (command.equals("회원목록_List")) {
                for (Member member : memeberList) {
                    System.out.println(member.getId() + " " + member.getNameId() + " " + member.getPassword() + " " + member.getRegDate() + "\n");
                }
            } else if (command.equals("로그인")) {
                // 아이디
                memberController.login(loginedUser, memeberList);


                // 로그인 객체 어딘가에 저장
            } else if (command.equals("로그아웃")) {
                if (loginedUser.getLoginId() == null) {
                    System.out.println("로그인 상태가 아닙니다.");
                    continue;
                }

                loginedUser.setLoginId(null);
                System.out.println("로그아웃 되었습니다.");
            }
        }
    }
}
