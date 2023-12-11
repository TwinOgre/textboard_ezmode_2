package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 시스템 시작==");

        List<Article> articleList = new ArrayList<>();
        int lastId = 1;

        while(true){
            System.out.printf("명령) ");
            String command = sc.nextLine().trim();


            if(command.equals("종료")){
                break;
            } else if(command.equals("등록")){
                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");
                String content = sc.nextLine();

                Article article = new Article(lastId, title, content);
                articleList.add(article);

                lastId++;
            } else if(command.equals("목록")){
                System.out.println("번호/제목/ 내용");
                System.out.println("----------------");
                for( Article article : articleList){
                    System.out.printf("%d,   %s,   %s\n", article.getId(), article.getTitle(), article.getContent());
                }
            } else if(command.equals("삭제")){
                System.out.println("삭제할 id를 입력하세요");
                System.out.printf("ID : ");
                int removeId = Integer.parseInt(sc.nextLine().trim());

                for(int i = 0; i < articleList.size(); i++){
                    if(removeId == articleList.get(i).getId()){
                        articleList.remove(articleList.get(i));
                    }
                }
                System.out.println(removeId + "번 게시글이 삭제되었습니다.");
            }
        }


        sc.close();
    }
}