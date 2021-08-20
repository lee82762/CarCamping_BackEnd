package com.Hanium.CarCamping.Exception;

public class CannotRecommendMyReviewException extends RuntimeException {
    public CannotRecommendMyReviewException() {
        super();
    }

    public CannotRecommendMyReviewException(String message) {
        super(message);
    }
}
