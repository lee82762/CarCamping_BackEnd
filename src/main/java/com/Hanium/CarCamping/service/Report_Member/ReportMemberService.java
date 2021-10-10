package com.Hanium.CarCamping.service.Report_Member;

import com.Hanium.CarCamping.Exception.alreadyReportReviewException;
import com.Hanium.CarCamping.domain.entity.Report_Member;
import com.Hanium.CarCamping.domain.entity.Review;
import com.Hanium.CarCamping.domain.entity.member.Member;
import com.Hanium.CarCamping.repository.MemberRepository;
import com.Hanium.CarCamping.repository.ReportMemberRepository;
import com.Hanium.CarCamping.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportMemberService {
    private final ReportMemberRepository reportMemberRepository;

    public void report(Member member, Review review) {
        Report_Member report_member = Report_Member.createReport_Member(review, member);
        if (review.getReporters().contains(report_member)) {
            throw new alreadyReportReviewException();
        }
        reportMemberRepository.save(report_member);
    }
}
