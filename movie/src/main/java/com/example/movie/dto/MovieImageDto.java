package com.example.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieImageDto {

    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    // 무비 관계

}
