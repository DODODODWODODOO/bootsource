package com.example.movie.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

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

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    // 저장된 파일의 위치
    public String getImageURL() {
        String fullPath = "";

        try {
            fullPath = URLEncoder.encode(path + "/" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fullPath;
    }

    // 썸네일 파일의 위치
    public String getThumbImageURL() {
        String thumbFullPath = "";

        try {
            thumbFullPath = URLEncoder.encode(path + "/s_" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return thumbFullPath;
    }

}
