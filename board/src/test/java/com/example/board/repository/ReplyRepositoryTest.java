package com.example.board.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            long bno = (long) (Math.random() * 100) + 1;

            Member member = Member.builder().email("user11@naver.com").build();

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply..." + i)
                    .replyer(member)
                    .board(board)
                    .build();
            replyRepository.save(reply);
        });
    }

    @Transactional
    @Test
    public void getRow() {

        Reply reply = replyRepository.findById(2L).get();
        System.out.println(reply); // Reply(rno=2, text=Reply....2, replyer=guest2)

        // FetchType.LAZY 이기 때문에 reply 부모 계시물은 가져오지 않음
        System.out.println(reply.getBoard());
    }

    // select * from reply r where r.
    @Test
    public void getReplies() {

        Board board = Board.builder().bno(39L).build();

        List<Reply> replies = replyRepository.getRepliesByBoardOrderByRno(board);

        System.out.println(replies.toString());
    }
}
