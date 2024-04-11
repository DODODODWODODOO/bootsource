package com.example.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.jpa.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertTest() {
        // LongStream.range(1, 100) : 1~99
        LongStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("Title....." + i)
                    .content("Content....." + i)
                    .writer("user" + (i % 10))
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    public void readTest() {
        System.out.println(boardRepository.findById(5L));
    }

    @Test
    public void getListTest() {
        boardRepository.findAll().forEach(board -> System.out.println(board));
    }

    @Test
    public void updateTest() {
        Optional<Board> result = boardRepository.findById(26L);

        // result.get()
        result.ifPresent(board -> {
            board.setTitle("Update Title.....");
            board.setContent("Update Content.....");
            System.out.println(boardRepository.save(board));
        });

    }

    @Test
    public void deleteTest() {
        // entity 찾기
        Optional<Board> result = boardRepository.findById(27L);
        // 삭제
        boardRepository.delete(result.get());

    }

    @Test
    public void queryMethodTest() {
        // where b1_0.title=?
        // List<Board> list = boardRepository.findByTitle("Title");
        // System.out.println("findByTitle " + list.size());

        // list = boardRepository.findByTitleLike("Title");
        // System.out.println("findByTitleLike " + list.size());
        // where b1_0.title like ? escape '\'

        // list = boardRepository.findByTitleStartingWith("Title");
        // System.out.println("findByTitleStartingWith " + list.size());
        // Title%

        // list = boardRepository.findByTitleEndingWith("Title");
        // System.out.println("findByTitleEndingWith " + list.size());
        // %Title

        // list = boardRepository.findByTitleContaining("Title");
        // System.out.println("findByTitleContaining " + list.size());
        // %Title%

        // list = boardRepository.findByWriterStartingWith("user");
        // System.out.println("findByWriterStartingWith " + list.size());

        // list.forEach(System.out::println);

        // List<Board> list = boardRepository.findByTitleContainingOrContent("Title",
        // "Content");
        // System.out.println("findByTitleContainingOrContent" + list.size());
        // where b1_0.title like ? escape '\' or b1_0.content=?

        // list = boardRepository.findByTitleContainingOrContentContaining("Title",
        // "Content");
        // System.out.println("findByTitleContainingOrContentContaining" + list.size());
        // where b1_0.title like ? escape '\' or b1_0.content like ? escape '\'

        // List<Board> list =
        // boardRepository.findByTitleContainingAndIdGreaterThan("Title", 50L);
        // System.out.println("findByTitleContainingAndIdGreaterThan " + list.size());
        // where b1_0.title like ? escape '\' and b1_0.id>?

        // List<Board> list = boardRepository.findByIdGreaterThanOrderByIdDesc(50L);
        // System.out.println("findByIdGreaterThanOrderByIdDesc" + list.size());
        // where b1_0.id>? order by b1_0.id desc

        // PageRequest.of(페이지 번호, 계시물 수); 페이지번호 0 부터 시작
        Pageable pageable = PageRequest.of(0, 10);
        List<Board> list = boardRepository.findByIdGreaterThanOrderByIdDesc(0L, pageable);
        list.forEach(System.out::println);
    }
}
