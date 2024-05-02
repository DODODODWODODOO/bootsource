package com.example.movie.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.movie.dto.ReviewDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import com.example.movie.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDto> getListOfMovie(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();
        List<Review> reviews = reviewRepository.findByMovie(movie);

        // List<Review> ==> List<ReviewDto>
        Function<Review, ReviewDto> fn = review -> entityToDto(review);
        return reviews.stream().map(fn).collect(Collectors.toList());
    }

    @Override
    public Long addReview(ReviewDto reviewDto) {

        Review review = dtoToEntity(reviewDto);
        return reviewRepository.save(review).getReviewNo();
    }

    @Override
    public void removeReview(Long reviewNo) {
        reviewRepository.deleteById(reviewNo);
    }

    @Override
    public ReviewDto getReview(Long reviewNo) {

        return entityToDto(reviewRepository.findById(reviewNo).get());
    }

    @Override
    public Long updateReview(ReviewDto reviewDto) {
        // save() =>
        // 1) select 2) insert or update 결정
        // return reviewRepository.save(dtoToEntity(reviewDto)).getReviewNo();

        Optional<Review> result = reviewRepository.findById(reviewDto.getReviewNo());

        if (result.isPresent()) {

            Review review = result.get();
            review.setText(reviewDto.getText());
            review.setGrade(reviewDto.getGrade());

            reviewRepository.save(dtoToEntity(reviewDto));
        }
        return reviewDto.getReviewNo();
    }

}
