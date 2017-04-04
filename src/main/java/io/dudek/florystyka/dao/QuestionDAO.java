package io.dudek.florystyka.dao;

import io.dudek.florystyka.model.AnswerType;
import io.dudek.florystyka.model.Question;
import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionDAO implements QDAO {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
    }

    private Connection getConnection() throws SQLException, ParserConfigurationException, SAXException, IOException {
        DBAccess dba = new DBAccess();
        Map<String, String> DBData = dba.getAccessData();
        return DriverManager.getConnection("jdbc:mysql://" + DBData.get("address") + "?useSSL=false", DBData.get("username"),
                DBData.get("password"));
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }


    public List<Question> findAllQuestion() {
        String sql = "select * from questions";
        List<Question> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet resultSet = statment.executeQuery();
            while (resultSet.next()) {
                Question q = new Question();
                q.setId(resultSet.getInt("id"));
                q.setContent(resultSet.getString("content"));
                q.addAnswer(AnswerType.A, resultSet.getString("answerA"));
                q.addAnswer(AnswerType.B, resultSet.getString("answerB"));
                q.addAnswer(AnswerType.C, resultSet.getString("answerC"));
                q.addAnswer(AnswerType.D, resultSet.getString("answerD"));
                switch (resultSet.getString("ok")) {
                    case "A":
                        q.setCorrect(AnswerType.A);
                        break;
                    case "B":
                        q.setCorrect(AnswerType.B);
                        break;
                    case "C":
                        q.setCorrect(AnswerType.C);
                        break;
                    case "D":
                        q.setCorrect(AnswerType.D);
                        break;
                }
                q.setImg(resultSet.getString("img"));

                result.add(q);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public List<Question> generateQuestionsList(int[] id) {
        List<Question> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            for(int i = 0; i < id.length; i++) {
                String sql = "select * from questions where id=" + id[i];
                PreparedStatement statment = connection.prepareStatement(sql);
                ResultSet resultSet = statment.executeQuery();
                while (resultSet.next()) {
                    Question q = new Question();
                    q.setId(resultSet.getInt("id"));
                    q.setContent(resultSet.getString("content"));
                    q.addAnswer(AnswerType.A, resultSet.getString("answerA"));
                    q.addAnswer(AnswerType.B, resultSet.getString("answerB"));
                    q.addAnswer(AnswerType.C, resultSet.getString("answerC"));
                    q.addAnswer(AnswerType.D, resultSet.getString("answerD"));
                    switch (resultSet.getString("ok")) {
                        case "A":
                            q.setCorrect(AnswerType.A);
                            break;
                        case "B":
                            q.setCorrect(AnswerType.B);
                            break;
                        case "C":
                            q.setCorrect(AnswerType.C);
                            break;
                        case "D":
                            q.setCorrect(AnswerType.D);
                            break;
                    }
                    q.setImg(resultSet.getString("img"));

                    result.add(q);
                }
            }
        } catch (SQLException | ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public Question findQuestionById(int id) {
        String sql = "select * from questions where id=" + id;
        Connection connection = null;
        Question q = new Question();
        try {
            connection = getConnection();
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet resultSet = statment.executeQuery();
            if (resultSet.next()) {
                q.setId(resultSet.getInt("id"));
                q.setContent(resultSet.getString("content"));
                q.addAnswer(AnswerType.A, resultSet.getString("answerA"));
                q.addAnswer(AnswerType.B, resultSet.getString("answerB"));
                q.addAnswer(AnswerType.C, resultSet.getString("answerC"));
                q.addAnswer(AnswerType.D, resultSet.getString("answerD"));
                switch (resultSet.getString("ok")) {
                    case "A":
                        q.setCorrect(AnswerType.A);
                        break;
                    case "B":
                        q.setCorrect(AnswerType.B);
                        break;
                    case "C":
                        q.setCorrect(AnswerType.C);
                        break;
                    case "D":
                        q.setCorrect(AnswerType.D);
                        break;
                }
                q.setImg(resultSet.getString("img"));

            }
        } catch (SQLException | ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return q;
    }

    public int getMaxId() {
        String sql = "select id from questions order by id desc limit 1";
        Connection connection = null;
        int id = 0;
        try {
            connection = getConnection();
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet resultSet = statment.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");

            }
        } catch (SQLException | ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return id;
    }

}
