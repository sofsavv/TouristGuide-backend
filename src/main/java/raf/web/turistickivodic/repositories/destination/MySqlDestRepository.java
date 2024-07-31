package raf.web.turistickivodic.repositories.destination;

import raf.web.turistickivodic.entities.Destination;
import raf.web.turistickivodic.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDestRepository extends MySqlAbstractRepository implements DestinationRepository {


    @Override
    public Destination addDestination(Destination destination) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            String[] generatedColumns = {"destinationId"};
            preparedStatement = connection.prepareStatement("INSERT INTO destinations(destination, about) VALUES (?,?)", generatedColumns);

            preparedStatement.setString(1, destination.getDestination());
            preparedStatement.setString(2, destination.getAbout());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                destination.setDestinationId(resultSet.getInt(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return destination;
    }

    @Override
    public List<Destination> allDestinations() {

        List<Destination> destinations = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM destinations");
            while(resultSet.next()){
                destinations.add(new Destination( resultSet.getInt("destinationId"),
                        resultSet.getString("destination"),
                        resultSet.getString("about")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return destinations;
    }

    @Override
    public Destination findDestination(Integer id) {

        Destination destination = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM destinations WHERE destinationId = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String name = resultSet.getString("destination");
                String about = resultSet.getString("about");
                destination = new Destination(id, name, about);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return destination;
    }

    @Override
    public void deleteDestination(Integer id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM destinations WHERE destinationId = ?");
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
    public Destination editDestination(Integer id, Destination destination) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE destinations SET destination = ?, about = ? WHERE destinationId = ?");
            preparedStatement.setString(1, destination.getDestination());
            preparedStatement.setString(2, destination.getAbout());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return destination;
    }
}
