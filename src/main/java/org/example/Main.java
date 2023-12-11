package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 시스템 시작==");


        List<Article> articleList = new ArrayList<>();
        List<Member> memeberList = new ArrayList<>();
        int lastId = 1;
        int lastMemberId = 1;
        while (true) {
            System.out.printf("명령) ");
            String command = sc.nextLine().trim();


            if (command.equals("종료")) {
                break;
            } else if (command.equals("등록")) {
                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");
                String content = sc.nextLine();

                Article article = new Article(lastId, title, content);
                articleList.add(article);

                lastId++;
            } else if (command.equals("목록")) {
                System.out.println("번호/제목/ 내용");
                System.out.println("----------------");
                for (Article article : articleList) {
                    System.out.printf("%d,   %s,   %s\n", article.getId(), article.getTitle(), article.getContent());
                }
            } else if (command.equals("삭제")) {
                System.out.println("삭제할 id를 입력하세요");
                System.out.printf("ID : ");
                int removeId = Integer.parseInt(sc.nextLine().trim());

                for (int i = 0; i < articleList.size(); i++) {
                    if (removeId == articleList.get(i).getId()) {
                        articleList.remove(articleList.get(i));
                    }
                }
                System.out.println(removeId + "번 게시글이 삭제되었습니다.");
            } else if (command.equals("수정")) {
                System.out.println("수정할 id를 입력하세요");
                System.out.printf("ID : ");
                int modifyId = Integer.parseInt(sc.nextLine().trim());

                for (int i = 0; i < articleList.size(); i++) {
                    if (modifyId == articleList.get(i).getId()) {
                        Article article = articleList.get(i);
                        System.out.printf("기존 제목 : %s \n", article.getTitle());
                        System.out.printf("수정할 제목 : ");
                        String title = sc.nextLine();

                        System.out.printf("기존 내용 : %s \n", article.getContent());
                        System.out.printf("수정할 내용 : ");
                        String content = sc.nextLine();

                        article.setTitle(title);
                        article.setContent(content);
                    }
                }
                System.out.println(modifyId + "번 게시글이 수정되었습니다.");
            } else if (command.equals("회원가입")) {
                Member member = new Member(lastMemberId, "0", "0", "0");

                // 아이디, 닉네임, 비밀번호, 비밀번호 확인
                // 중복 아이디 검증

                while (true) {
                    System.out.println("아이디를 입력하세요");
                    System.out.printf("아이디 : ");
                    String nameId = sc.nextLine().trim();
                    boolean isDuplicated = false;
                    for (Member member1 : memeberList) {
                        // 아이디 중복이 없다면
                        if (nameId.equals(member1.getNameId())) {
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
                String password = sc.nextLine();

                while (true) {
                    System.out.printf("비밀번호 확인 : ");
                    String passwordConfirm = sc.nextLine();

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
            }
        }


        sc.close();
    }
}