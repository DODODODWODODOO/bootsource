package com.example.movie.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieImageDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;

public interface MovieService {

    PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto);

    // entity => dto
    public default MovieDto entityToDto(Movie movie, List<MovieImage> movieImages, Long reviewCnt, Double avg) {

        // [Movie(mno=91, title=Movie91), MovieImage(inum=273,
        // uuid=50f91be2-5caf-414d-b638-9a019939b517, imgName=img0.jpg, path=null),
        // 2,3.0]
        MovieDto movieDto = MovieDto.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                .createdDate(movie.getCreatedDate())
                .lastModifiedDate(movie.getLastModifiedDate())
                .avg(avg)
                .reviewCnt(reviewCnt)
                .build();

        // 영화 하나 상세 조회 => 이미지를 모두 보여주기
        List<MovieImageDto> movieImageDtos = movieImages.stream().map(movieImage -> {
            return MovieImageDto.builder()
                    .inum(movieImage.getInum())
                    .uuid(movieImage.getUuid())
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .build();
        }).collect(Collectors.toList());

        movieDto.setMovieImageDtos(movieImageDtos);

        return movieDto;
    }

    // dto => entity
    public default Map<String, Object> dtoToEntity(MovieDto dto) {

        return null;
    }
}
