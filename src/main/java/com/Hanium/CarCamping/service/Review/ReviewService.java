package com.Hanium.CarCamping.service.Review;

import com.Hanium.CarCamping.domain.dto.review.CreateReviewDto;
import com.Hanium.CarCamping.domain.entity.CampSite;
import com.Hanium.CarCamping.domain.entity.Review;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    @Transactional
    public void saveReview(CreateReviewDto createReviewDto, Member writer, CampSite campSite) {
        reviewRepository.save(Review.createReview(createReviewDto, writer,campSite));
    }

    public List<Review> getCampSiteReviewByDateDESC(Long campSite_id) {
        return reviewRepository.findByCampSiteDateDESC(campSite_id);
    }
    public List<Review> getCampSiteReviewByDateASC(Long campSite_id) {
        return reviewRepository.findByCampSiteDateASC(campSite_id);
    }
    public List<Review> getCampSiteReviewByScoreDESC(Long campSite_id) {
        return reviewRepository.findByCampSiteDESC(campSite_id);
    }
    public List<Review> getCampSiteReviewByScoreASC(Long campSite_id) {
        return reviewRepository.findByCampSiteASC(campSite_id);
    }

    public Review getReview(Long id) {
        return reviewRepository.getById(id);
    }







}
