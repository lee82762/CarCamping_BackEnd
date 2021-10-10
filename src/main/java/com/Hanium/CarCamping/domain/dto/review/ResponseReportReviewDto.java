package com.Hanium.CarCamping.domain.dto.review;

import com.Hanium.CarCamping.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReportReviewDto {
    private Long review_id;
    private String title;
    private String contents;
    private Integer reportCount;
    public static ResponseReportReviewDto convertToResponseReportReviewDto(Review review) {
        ResponseReportReviewDto responseReportReviewDto=new ResponseReportReviewDto();
        responseReportReviewDto.review_id=review.getReview_id();
        responseReportReviewDto.title=review.getTitle();
        responseReportReviewDto.contents=review.getContents();
        responseReportReviewDto.reportCount= review.getReportCount();
        return responseReportReviewDto;
    }
}
