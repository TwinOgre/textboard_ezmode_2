package org.example;

import org.example.article.Article;
import org.example.member.Member;
import org.example.member.MemberController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Container.init();

        new App().run();


        Container.close();
    }
}