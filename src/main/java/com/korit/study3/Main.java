package com.korit.study3;

import com.korit.study3.dto.BoardReqDto;
import com.korit.study3.entity.Board;
import com.korit.study3.service.BoardService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BoardService boardService = BoardService.getInstance();

        while (true) {
            System.out.println("게시판");
            System.out.println("1. 게시글 쓰기");
            System.out.println("2. 게시글 검색");
            System.out.println("3. 닉네임으로 검색");
            System.out.println("4. 제목, 내용으로 검색");
            System.out.println("q. 프로그램 종료");
            System.out.print(": ");
            String select = scanner.nextLine();

            if("q".equalsIgnoreCase(select)) {
                System.out.println("종료");
                break;
            } else if ("1".equals(select)) {
                System.out.println("게시글 쓰기");
                BoardReqDto boardReqDto = new BoardReqDto();

                while (true) {
                    System.out.print("제목: ");
                    boardReqDto.setTitle(scanner.nextLine());
                    if (boardService.isDupleTitle(boardReqDto.getTitle())) {
                        System.out.println("중복된 제목입니다.");
                        continue;
                    }
                    break;
                }
                System.out.print("내용: ");
                boardReqDto.setContent(scanner.nextLine());
                System.out.print("닉네임: ");
                boardReqDto.setUsername(scanner.nextLine());

                boardService.insertBoard(boardReqDto);
                System.out.println("작성 완료");
            }
            else if ("2".equals(select)) {
                System.out.println("게시글 검색");
                System.out.print("게시글ID: ");
                System.out.println(boardService.searchBoardByBoardId(scanner.nextInt()));
            }
            else if ("3".equals(select)) {
                System.out.println("닉네임으로 검색: ");
                System.out.print("닉네임: ");
                List<Board> boardList = boardService.searchBoardByUsername(scanner.nextLine());
                boardList.forEach(System.out::println);

            }
            else if ("4".equals(select)) {
                System.out.println("제목, 내용으로 검색: ");
                System.out.print("검색: ");
                List<Board> boardList = boardService.searchBoardByKeyword(scanner.nextLine());
                boardList.forEach(System.out::println);
            }
        }
    }
}
