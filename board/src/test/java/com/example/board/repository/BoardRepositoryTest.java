package com.example.board.repository;

import static org.mockito.ArgumentMatchers.isNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.board.entity.Board;
import com.example.board.entity.Member;

import jakarta.transaction.Transactional;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder().email("user" + i + "@gmail.com").build();

            Board board = Board.builder()
                    .title("title.." + i)
                    .content("content.." + i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });
    }

    @Transactional
    @Test
    public void readBoard() {
        Board board = boardRepository.findById(3L).get();
        System.out.println(board);
        // Board(bno=3, title=title..1, content=content..1,
        // writer=Member(email=user1@gmail.com, password=1111, name=USER1))
        System.out.println(board.getWriter());
    }

    @Test
    public void testList() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> list = boardRepository.list("tcw", "Title", pageable);
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
            // Board board = (Board) objects[0];
            // Member member = (Member) objects[1];
            // Long replyCnt = (Long) objects[2];
            // System.out.println(board + " " + member + " " + replyCnt);
        }
    }
}
