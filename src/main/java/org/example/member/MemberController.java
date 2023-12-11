package org.example.member;

import org.example.Container;
import org.example.LoginedUser;
import org.example.member.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MemberController {

    int lastMemberId = 1;
    Scanner sc;

    public MemberController() {

        sc = new Scanner(System.in);
    }

    public void login(LoginedUser loginedUser, List<Member> memeberList) {
        if (loginedUser.getLoginId() != null) {
            System.out.println("로그인 중입니다.");
            return;
        }
        System.out.println("아이디를 입력하세요");
        System.out.printf("아이디 : ");
        String userId = sc.nextLine().trim();

        for (int i = 0; i < memeberList.size(); i++) {
            if (userId.equals(memeberList.get(i).getNameId())) {
                while (true) {
                    System.out.println("비밀번호를 입력하세요");
                    System.out.printf("비밀번호 : ");
                    String password = sc.nextLine().trim();

                    if (password.equals(memeberList.get(i).getPassword())) {
                        loginedUser.setLoginId(userId);
                        loginedUser.setLoginPassword(password);
                        System.out.println("로그인을 환영합니다. " + loginedUser.getLoginId() + "님");
                        return;
                    } else {
                        System.out.println("== 비밀번호를 틀렸습니다. ==");
                    }
                }
            }
        }
    }

    public void register(List<Member> memeberList) {
        Member member = new Member(lastMemberId, "0", "0", "0");

        while (true) {
            System.out.println("아이디를 입력하세요");
            System.out.printf("아이디 : ");
            String nameId = Container.getSc().nextLine().trim();
            boolean isDuplicated = false;
            for (Member member9 : memeberList) {
                if (nameId.equals(member9.getNameId())) {
                    System.out.println("중복 아이디가 존재합니다.");
                    isDuplicated = true;

                }
            }
            if (!isDuplicated) {
                System.out.println("아이디 확인");
                member.setNameId(nameId);
                break;
            }
        }

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
    }
    public void memberList(List<Member> memeberList){
        for (Member member : memeberList) {
            System.out.println(member.getId() + " " + member.getNameId() + " " + member.getPassword() + " " + member.getRegDate() + "\n");
        }
    }
    public void logOut(LoginedUser loginedUser){
        if(loginedUser.getLoginId() == null){
            System.out.println("로그인 상태가 아닙니다.");
            return;
        }

        loginedUser.setLoginId(null);
        System.out.println("로그아웃 되었습니다.");
    }
}
