package com.Hanium.CarCamping.domain.dto.review;

import com.Hanium.CarCamping.domain.entity.Review;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseReviewDto {
    private Long review_id;
    private String writer;
    private String title;
    private Float Score;
    private LocalDateTime date;
    private Integer recommend;

    public static ResponseReviewDto convertToReviewDto(Review review) {
        return ResponseReviewDto.builder()
                .review_id(review.getReview_id())
                .writer(review.getWriter().getNickname())
                .title(review.getTitle())
                .Score(review.getScore())
                .date(review.getDate())
                .recommend(review.getRecommend())
                .build();
    }
}
