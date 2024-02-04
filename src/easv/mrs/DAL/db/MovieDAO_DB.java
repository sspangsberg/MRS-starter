package easv.mrs.DAL.db;

// Project imports
import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.mrs.BE.Movie;
import easv.mrs.DAL.IMovieDataAccess;

// Java imports
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_DB implements IMovieDataAccess {

    private MyDatabaseConnector databaseConnector;

    public MovieDAO_DB() throws IOException {
        databaseConnector = new MyDatabaseConnector();
    }

    public List<Movie> getAllMovies() {

        ArrayList<Movie> allMovies = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT1 * FROM dbo.Movie;";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Movie object
                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                int year = rs.getInt("year");

                Movie movie = new Movie(id, year, title);
                allMovies.add(movie);
            }
            return allMovies;

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Movie createMovie(Movie movie) {

        // SQL command
        String sql = "INSERT INTO dbo.Movie (Title,Year) VALUES (?,?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            // Bind parameters
            stmt.setString(1,movie.getTitle());
            stmt.setInt(2, movie.getYear());

            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create movie object and send up the layers
            Movie createdMovie = new Movie(id, movie.getYear(), movie.getTitle());

            return createdMovie;
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMovie(Movie movie) throws Exception {

        // SQL command
        String sql = "UPDATE dbo.Movie SET Title = ?, Year = ? WHERE ID = ?";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            // Bind parameters
            stmt.setString(1,movie.getTitle());
            stmt.setInt(2, movie.getYear());
            stmt.setInt(3, movie.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            // create entry in log file
            ex.printStackTrace();
            throw new Exception("Could not update movie", ex);
        }
    }

    public void deleteMovie(Movie movie) throws Exception {
        // SQL command
        String sql = "DELETE FROM dbo.Movie WHERE ID = ?;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            // Bind parameters
            stmt.setInt(1, movie.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            // create entry in log file
            ex.printStackTrace();
            throw new Exception("Could not delete movie", ex);
        }
    }

    public List<Movie> searchMovies(String query) throws Exception {

        //TODO Do this
        throw new UnsupportedOperationException();
    }

}
