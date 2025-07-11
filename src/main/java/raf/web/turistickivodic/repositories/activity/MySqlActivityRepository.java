package raf.web.turistickivodic.repositories.activity;

import raf.web.turistickivodic.entities.Activity;
import raf.web.turistickivodic.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlActivityRepository extends MySqlAbstractRepository implements ActivityRepository {
    @Override
    public Activity addActivity(Activity activity) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            String[] generatedColumns = {"activityId"};
            preparedStatement = connection.prepareStatement("INSERT INTO activitys(activity) VALUES (?)", generatedColumns);

            preparedStatement.setString(1, activity.getActivity());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                activity.setActivityId(resultSet.getInt(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return activity;
    }

    @Override
    public List<Activity> allActivities(int currentPage, int pageSize) {

        List<Activity> activity = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();

            int offset = (currentPage - 1) * pageSize;

            preparedStatement = connection.prepareStatement("SELECT * FROM activitys LIMIT ? OFFSET ?");
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, offset);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                activity.add(new Activity( resultSet.getInt("destinationId"),
                        resultSet.getString("activity")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return activity;
    }

    @Override
    public Activity findActivity(Integer id) {

        Activity activity = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM activitys WHERE activityId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String activityDesc = resultSet.getString("activity");
                activity = new Activity(id, activityDesc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return activity;
    }

    @Override
    public void deleteActivity(Integer id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM activitys WHERE activityId = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public Activity editActivity(Integer id, Activity activity) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE activitys SET activity = ? WHERE activityId = ?");
            preparedStatement.setString(1, activity.getActivity());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return activity;
    }

    @Override
    public List<Activity> getActivitiesByArticleId(Integer articleId) {

        List<Activity> activities = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT a.* FROM activitys a " +
                    "JOIN article_activity aa ON a.activityId = aa.activityId " +
                    "WHERE aa.articleId = ?");
            preparedStatement.setInt(1, articleId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int activityId = resultSet.getInt("activityId");
                String activityName = resultSet.getString("activity");
                activities.add(new Activity(activityId, activityName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return activities;
    }
}
