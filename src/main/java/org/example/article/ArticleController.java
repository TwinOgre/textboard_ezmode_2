package org.example.article;

import org.example.Container;
import org.example.LoginedUser;
import org.example.member.Member;
import org.example.member.MemberController;

import java.time.LocalDate;
import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

public class ArticleController {
    int lastId = 1;
    public ArticleController(){

    }

    public void create(LoginedUser loginedUser, MemberController memberController, List<Member> memeberList, List<Article> articleList){
        if(loginedUser.getLoginId() == null){
            System.out.println("게시물 등록을 하려면 먼저 로그인을 해주세요");
            System.out.println("아이디가 없다면 회원가입을 해주세요");

            memberController.login(loginedUser,memeberList);
            if(loginedUser.getLoginId() == null){
                return;
            }
        }
        System.out.printf("제목 : ");
        String title = Container.getSc().nextLine();
        System.out.printf("내용 : ");
        String content = Container.getSc().nextLine();
        // Article(id, 제목, 내용, 작가)
        Article article = new Article(lastId, title, content, LocalDate.now().toString() ,loginedUser.getLoginId());
        articleList.add(article);

        lastId++;
    }
    public void list(List<Article> articleList) {
        System.out.println("번호 / 제목 / 내용 / 등록일 / 작성자");
        System.out.println("------------------------------");
        for (Article article : articleList) {
            System.out.printf("%d,   %s,   %s,  %s,   %s\n", article.getId(), article.getTitle(), article.getContent(), LocalDate.now().toString(),article.getAuthor());
        }
    }
    public void delete(LoginedUser loginedUser, MemberController memberController, List<Member> memeberList, List<Article> articleList){
        if(loginedUser.getLoginId() == null){
            System.out.println("게시물 삭제를 하려면 먼저 로그인을 해주세요");

            memberController.login(loginedUser,memeberList);
            if(loginedUser.getLoginId() == null){
                return;
            }
        }
        System.out.println("삭제할 id를 입력하세요");
        System.out.printf("ID : ");
        int removeId = Integer.parseInt(Container.getSc().nextLine().trim());

        Article article = null;
        for (int i = 0; i < articleList.size(); i++) {
            if (removeId == articleList.get(i).getId()) {
                article = articleList.get(i);

            }
        }
        if(article == null){
            System.out.println("해당 게시글은 존재하지 않습니다.");
            return;
        }
        if(article.getAuthor() != loginedUser.getLoginId()){
            System.out.println("해당 작성자만 삭제가 가능합니다.");
            return;
        }
        articleList.remove(article);
        System.out.println(removeId + "번 게시글이 삭제되었습니다.");
    }
    public void modify(LoginedUser loginedUser, MemberController memberController, List<Member> memeberList, List<Article> articleList){
        if(loginedUser.getLoginId() == null){
            System.out.println("게시물 수정을 하려면 먼저 로그인을 해주세요");

            memberController.login(loginedUser,memeberList);
            if(loginedUser.getLoginId() == null){
                return;
            }
        }
        System.out.println("수정할 id를 입력하세요");
        System.out.printf("ID : ");
        int modifyId = Integer.parseInt(Container.getSc().nextLine().trim());

        for (int i = 0; i < articleList.size(); i++) {
            if (modifyId == articleList.get(i).getId()) {
                Article article = null;
                for (int t = 0; t < articleList.size(); t++) {
                    if (modifyId == articleList.get(i).getId()) {
                        article = articleList.get(i);

                    }
                }
                if(article == null){
                    System.out.println("해당 게시글은 존재하지 않습니다.");
                    continue;
                }
                if(!Objects.equals(article.getAuthor(), loginedUser.getLoginId())){
                    System.out.println("해당 작성자만 수정이 가능합니다.");
                    break;
                }
                Article modifyArticle = articleList.get(i);
                System.out.printf("기존 제목 : %s \n", article.getTitle());
                System.out.printf("수정할 제목 : ");
                String title = Container.getSc().nextLine();

                System.out.printf("기존 내용 : %s \n", article.getContent());
                System.out.printf("수정할 내용 : ");
                String content = Container.getSc().nextLine();

                modifyArticle.setTitle(title);
                modifyArticle.setContent(content);
                System.out.println(modifyId + "번 게시글이 수정되었습니다.");
            }
        }
    }
}
