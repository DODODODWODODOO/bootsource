package com.example.book.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;

import jakarta.transaction.Transactional;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    // 테스트 시 properties #DDL 을 create 로 해놓으면 그전에 테스트는 사라져서
    // none 으로 해놓을것 (부모키가 존재 하지 않습니다)

    @Test
    public void testCategoryCreate() {

        Category category = Category.builder().name("컴퓨터").build();
        categoryRepository.save(category);

        category = Category.builder().name("경제/경영").build();
        categoryRepository.save(category);

        category = Category.builder().name("인문").build();
        categoryRepository.save(category);

        category = Category.builder().name("소설").build();
        categoryRepository.save(category);

        category = Category.builder().name("자기계발").build();
        categoryRepository.save(category);
    }

    @Test
    public void testPublisherCreate() {

        Publisher publisher = Publisher.builder().name("로드북").build();
        publisherRepository.save(publisher);

        publisher = Publisher.builder().name("다산").build();
        publisherRepository.save(publisher);

        publisher = Publisher.builder().name("웅진지식하우스").build();
        publisherRepository.save(publisher);

        publisher = Publisher.builder().name("비룡소").build();
        publisherRepository.save(publisher);

        publisher = Publisher.builder().name("을유문화사").build();
        publisherRepository.save(publisher);
    }

    @Test
    public void testBookCreate() {

        LongStream.rangeClosed(1, 20).forEach(i -> {
            Book book = Book.builder()
                    .price(25000)
                    .salePrice(22500)
                    .title("스프링 부트 프레임워크 " + i)
                    .writer("홍길동")
                    .category(Category.builder().id((i % 5) + 1).build())
                    .publisher(Publisher.builder().id((i % 5) + 1).build())
                    .build();

            bookRepository.save(book);
        });

    }

    @Transactional
    @Test
    public void testBookList() {
        List<Book> books = bookRepository.findAll();

        books.forEach(book -> {
            System.out.println(book);
            System.out.println("출판사 " + book.getPublisher().getName());
            System.out.println("분야 " + book.getCategory().getName());
        });
    }

    @Test
    public void testCateNameList() {
        List<Category> list = categoryRepository.findAll();

        list.forEach(category -> System.out.println(category));
        // Category(id=1, name=컴퓨터)

        // List<String> cateList = new ArrayList<>();
        // list.forEach(category -> cateList.add(category.getName())); // 컴퓨터

        List<String> cateList = list.stream().map(entity -> entity.getName()).collect(Collectors.toList()); // 컴퓨터

        cateList.forEach(System.out::println);
    }
}
