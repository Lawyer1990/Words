package com.example.englishwords.models;

import lombok.Data;

@Data
public class MeaningsModel {
    private int id;
    private String meaningOne;
    private String meaningTwo;
    private int topicId;
    private String transcription;

    public MeaningsModel(){}
}
