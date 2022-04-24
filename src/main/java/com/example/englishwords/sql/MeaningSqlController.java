package com.example.englishwords.sql;

import com.example.englishwords.models.MeaningsModel;
import com.example.englishwords.models.TopicsModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.englishwords.sql.SqlRequest.*;

public class MeaningSqlController extends BaseSqlController {
    private final static String TABLE_MEANINGS = " Meanings ";
    private final static String COLUMN_MEANING_ID = " `id` ";
    private final static String COLUMN_MEANING_ONE = " `meeningOne` ";
    private final static String COLUMN_MEANING_TWO = " `meeningTwo` ";
    private final static String COLUMN_MEANING_TRANSCRIPTION = " `transcription` ";
    private final static String COLUMN_TOPIC_ID = " `topicId` ";

    private MeaningsModel setMeaning(ResultSet resultSet) throws SQLException {
        MeaningsModel meaning = new MeaningsModel();
        meaning.setId(resultSet.getInt(1));
        meaning.setMeaningOne(resultSet.getString(2));
        meaning.setMeaningTwo(resultSet.getString(3));
        meaning.setMeaningTwo(resultSet.getString(3));
        meaning.setTopicId(resultSet.getInt(4));
        meaning.setTranscription(resultSet.getString(5));
        return meaning;
    }

    public List<MeaningsModel> receiveMeaningsFromTopic(TopicsModel topicsModel) {
        List<MeaningsModel> meanings = new ArrayList<>();
        try {
            resultSet = connectToDataBase().executeQuery(SELECT_ALL.getValue() + FROM.getValue() + TABLE_MEANINGS +
                    WHERE.getValue() + COLUMN_TOPIC_ID + equalsValue(String.valueOf(topicsModel.getId())));
            while (resultSet.next()) {
                meanings.add(setMeaning(resultSet));
            }
            closeConnection();
        } catch (SQLException ex) {
            log.error(String.valueOf(ex));
        }
        return meanings;
    }

    public void addMeanings(String meaningOne, String meaningTwo, String topicId, String transcription) {
        try {
            connectToDataBase().executeUpdate(INSERT.getValue() + INTO.getValue() +
                    TABLE_MEANINGS + roundBracketsValue(COLUMN_MEANING_ONE + COMO +
                    COLUMN_MEANING_TWO + COMO + COLUMN_TOPIC_ID + COMO + COLUMN_MEANING_TRANSCRIPTION) +
                    VALUES.getValue() + roundBracketsValue(bracketsValue(meaningOne.replace("'", "\\'")) + COMO +
                    bracketsValue(meaningTwo.replace("'", "\\'")) + COMO + bracketsValue(topicId) +
                    COMO + bracketsValue(transcription.replace("'", "\\'"))));
            closeConnection();
        } catch (SQLException ex) {
            log.error(String.valueOf(ex));
        }
    }

    public void deleteMeanings(int meaningsId) {
        try {
            connectToDataBase().executeUpdate(DELETE.getValue() + FROM.getValue() +
                    TABLE_MEANINGS + WHERE.getValue() + COLUMN_MEANING_ID + equalsValue(String.valueOf(meaningsId)));
            closeConnection();
        } catch (SQLException ex) {
            log.error(String.valueOf(ex));
        }
    }
}