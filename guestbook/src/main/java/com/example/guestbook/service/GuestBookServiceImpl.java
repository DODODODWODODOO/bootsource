package com.example.guestbook.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.entity.QGuestBook;
import com.example.guestbook.repository.GuestBookRepository;
import com.querydsl.core.BooleanBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository guestBookRepository;

    // @Override
    // public List<GuestBookDto> getList() {
    // List<GuestBook> entites = guestBookRepository.findAll();

    // Function<GuestBook, GuestBookDto> fn = (entity -> entityToDto(entity));
    // return entites.stream().map(fn).collect(Collectors.toList());
    // }

    @Override
    public PageResultDto<GuestBookDto, GuestBook> getList(PageRequestDto requestDto) {

        Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());
        // PagingAndSortingRepository.findAll(Pageable pageable)
        // Page<GuestBook> result = guestBookRepository.findAll(pageable);

        BooleanBuilder builder = getSearch(requestDto);
        // querydsl.QuerydslPredicateExecutor.findAll(Predicate predicate, Pageable
        // pageable)
        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        Function<GuestBook, GuestBookDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<GuestBookDto, GuestBook>(result, fn);
    }

    @Override
    public GuestBookDto getRow(Long gno) {
        return entityToDto(guestBookRepository.findById(gno).get());
    }

    @Override
    public void modify(GuestBookDto modifyDto) {

        GuestBook guestBook = guestBookRepository.findById(modifyDto.getGno()).get();

        guestBook.setTitle(modifyDto.getTitle());
        guestBook.setContent(modifyDto.getContent());
        guestBookRepository.save(guestBook);

    }

    @Override
    public void remove(Long gno) {
        guestBookRepository.deleteById(gno);

    }

    @Override
    public Long insert(GuestBookDto dto) {
        GuestBook guestBook = guestBookRepository.save(dtoToEntity(dto));
        return guestBook.getGno();
    }

    // Book 프로젝트에서는 BookRepository 에 작성함
    private BooleanBuilder getSearch(PageRequestDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder();

        // Q 클래스 사용
        QGuestBook guestBook = QGuestBook.guestBook;

        // type, keyword 가져오기
        String type = requestDto.getType();
        String keyword = requestDto.getKeyword();

        // gno > 0
        builder.and(guestBook.gno.gt(0L));

        if (type == null || type.trim().length() == 0) {
            return builder;
        }

        // 검색 타입이 있는 경우
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (type.contains("t")) {
            conditionBuilder.or(guestBook.title.contains(keyword));
        }
        if (type.contains("c")) {
            conditionBuilder.or(guestBook.content.contains(keyword));
        }
        if (type.contains("w")) {
            conditionBuilder.or(guestBook.writer.contains(keyword));
        }
        builder.and(conditionBuilder);

        return builder;
    }

}
