package com.example.englishwords.sql;

import com.example.englishwords.models.TopicsModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.englishwords.sql.SqlRequest.*;

public class TopicsSqlController extends BaseSqlController {
    private final static String TABLE_TOPICS = " `Topics` ";
    private final static String COLUMN_DATE = " `Topics`.`date` ";
    private final static String COLUMN_NAME = " `Topics`.`name` ";
    private final static String NAME = "`name`";


    private TopicsModel setTopic(ResultSet resultSet) throws SQLException {
        TopicsModel topic = new TopicsModel();
        topic.setId(resultSet.getInt(1));
        topic.setName(resultSet.getString(2));
        topic.setDate(resultSet.getString(3));
        return topic;
    }

    public List<TopicsModel> receiveTopics() {
        List<TopicsModel> topics = new ArrayList<>();
        try {
            resultSet = connectToDataBase().executeQuery(SELECT_ALL.getValue() + FROM.getValue() + TABLE_TOPICS +
                    ORDER_BY.getValue() + COLUMN_DATE + DESC);
            while (resultSet.next()) {
                topics.add(setTopic(resultSet));
            }
            closeConnection();
        } catch (SQLException ex) {
            log.error(String.valueOf(ex));
        }
        return topics;
    }

    public TopicsModel receiveTopic(String topicName) {
        TopicsModel topic = new TopicsModel();
        try {
            resultSet = connectToDataBase().executeQuery(SELECT_ALL.getValue() + FROM.getValue() + TABLE_TOPICS +
                    WHERE.getValue() + COLUMN_NAME + equalsValue(topicName));
            while (resultSet.next()) {
                topic = setTopic(resultSet);
            }
            closeConnection();
        } catch (SQLException ex) {
            log.error(String.valueOf(ex));
        }
        return topic;
    }

    public void updateTopicDate(String topicName) {
        if (topicName != null) {
            try {
                connectToDataBase().executeUpdate(UPDATE.getValue() + TABLE_TOPICS + SET.getValue() +
                        COLUMN_DATE + equalsValue(dateController.receiveDateToday()) + WHERE.getValue() + COLUMN_NAME + equalsValue(topicName));
                closeConnection();
                log.info("Update " + topicName + " Set " + dateController.receiveDateToday());
            } catch (SQLException ex) {
                log.error(String.valueOf(ex));
            }
        }
    }

    public void addTopic(String topicName) {
        if (topicName != null) {
            try {
                connectToDataBase().executeUpdate(INSERT.getValue() + INTO.getValue() +
                        TABLE_TOPICS + roundBracketsValue(NAME) + VALUES.getValue() +
                        roundBracketsTildaValue(topicName));
                closeConnection();
                log.info("Add " + topicName);
            } catch (SQLException ex) {
                log.error(String.valueOf(ex));
            }
        }
    }

    public void deleteTopic(String topicName) {
        if (topicName != null) {
            try {
                connectToDataBase().executeUpdate(DELETE.getValue() + FROM.getValue() +
                        TABLE_TOPICS + WHERE.getValue() + NAME + equalsValue(topicName));
                closeConnection();
                log.info("Add " + topicName);
            } catch (SQLException ex) {
                log.error(String.valueOf(ex));
            }
        }
    }
}