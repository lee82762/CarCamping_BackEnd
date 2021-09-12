package com.Hanium.CarCamping.domain.dto.review;

import com.Hanium.CarCamping.domain.entity.Review;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResponseOneReviewDto {
    private Long review_id;
    private String writer;
    private String title;
    private Float Score;
    private String contents;
    private LocalDateTime date;
    private Integer recommend;
    private String images;
    public static ResponseOneReviewDto convertToOneReviewDto(Review review) {
        ResponseOneReviewDto r=new ResponseOneReviewDto();
        r.review_id= review.getReview_id();
        r.writer=review.getWriter().getNickname();
        r.title= review.getTitle();
        r.Score= review.getScore();
        r.contents= review.getContents();
        r.date=review.getDate();
        r.recommend=review.getRecommend();
        r.images=review.getImages();
        return r;
    }
}
