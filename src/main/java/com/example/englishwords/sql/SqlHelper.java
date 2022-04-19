package com.example.englishwords.sql;

import com.example.englishwords.models.MeaningsModel;
import com.example.englishwords.models.TopicsModel;

import java.util.List;

public class SqlHelper {
    public TopicsSqlController topicsSqlController = new TopicsSqlController();
    public MeaningSqlController meaningSqlController = new MeaningSqlController();

    public List<MeaningsModel> receiveMeaning(String topicName) {
        return meaningSqlController.receiveMeaningsFromTopic(topicsSqlController.receiveTopic(topicName));
    }

    public void addTopic(String topicName) {
        topicsSqlController.addTopic(topicName);
    }

    public void addWords(String meaningOne, String meaningTwo, String topicName, String transcription) {
        TopicsModel topic = topicsSqlController.receiveTopic(topicName);
        meaningSqlController.addMeanings(meaningOne, meaningTwo, String.valueOf(topic.getId()), transcription);
    }

    public void deleteWords(String meaningOne, String meaningTwo, String topicName) {
        List<MeaningsModel> meanings = receiveMeaning(topicName);
        meaningSqlController.deleteMeanings(meanings.stream().filter(s -> s.getMeaningOne().equals(meaningOne))
                .filter(a -> a.getMeaningTwo().equals(meaningTwo)).findFirst().get().getId());
    }
}
