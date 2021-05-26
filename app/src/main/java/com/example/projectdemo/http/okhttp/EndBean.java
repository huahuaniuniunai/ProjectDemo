package com.example.projectdemo.http.okhttp;

public class EndBean {
    private String clientType;
    private String userId;
    private String questionId;
    private String evaluationOpinion;
    private String questionOpinion;
    private String toatalEvaluation;

    public EndBean(String clientType, String userId, String questionId, String evaluationOpinion, String questionOpinion, String toatalEvaluation) {
        this.clientType = clientType;
        this.userId = userId;
        this.questionId = questionId;
        this.evaluationOpinion = evaluationOpinion;
        this.questionOpinion = questionOpinion;
        this.toatalEvaluation = toatalEvaluation;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public void setEvaluationOpinion(String evaluationOpinion) {
        this.evaluationOpinion = evaluationOpinion;
    }

    public void setQuestionOpinion(String questionOpinion) {
        this.questionOpinion = questionOpinion;
    }

    public void setToatalEvaluation(String toatalEvaluation) {
        this.toatalEvaluation = toatalEvaluation;
    }

    public String getClientType() {
        return clientType;
    }

    public String getUserId() {
        return userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getEvaluationOpinion() {
        return evaluationOpinion;
    }

    public String getQuestionOpinion() {
        return questionOpinion;
    }

    public String getToatalEvaluation() {
        return toatalEvaluation;
    }
}
