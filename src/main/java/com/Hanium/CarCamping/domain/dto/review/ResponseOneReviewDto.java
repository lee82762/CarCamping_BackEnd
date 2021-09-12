package com.Hanium.CarCamping.domain.dto.review;

import com.Hanium.CarCamping.domain.entity.Review;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
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
        return ResponseOneReviewDto.builder()
                .review_id(review.getReview_id())
                .writer(review.getWriter().getNickname())
                .title(review.getTitle())
                .Score(review.getScore())
                .date(review.getDate())
                .contents(review.getContents())
                .recommend(review.getRecommend())
                .images(review.getImages())
                .build();
    }
}
