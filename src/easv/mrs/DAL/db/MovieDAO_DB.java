package easv.mrs.DAL.db;

// Project imports
import easv.mrs.BE.Movie;
import easv.mrs.DAL.IMovieDataAccess;
import easv.mrs.util.MRSException;

// Java imports
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_DB implements IMovieDataAccess {

    private DBConnector dbConnector = DBConnector.getInstance();

    public MovieDAO_DB() throws MRSException {}

    @Override
    public List<Movie> getAllMovies() throws MRSException {

        ArrayList<Movie> allMovies = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "SELECT * FROM dbo.Movie;";
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

        }
        catch (SQLException ex)
        {
            // fixme: optionally log to file, db etc.
            throw new MRSException("Could not get movies from database.", ex);
        }
    }

    @Override
    public Movie createMovie(Movie movie) throws MRSException {

        // SQL command
        String sql = "INSERT INTO dbo.Movie (Title,Year) VALUES (?,?);";

        try (Connection conn = dbConnector.getConnection();
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
        }
        catch (SQLException ex)
        {
            // fixme: optionally log to file, db etc.
            throw new MRSException("Could not get movies from database.", ex);
        }
    }

    @Override
    public void updateMovie(Movie movie) throws MRSException {

        // SQL command
        String sql = "UPDATE dbo.Movie SET Title = ?, Year = ? WHERE ID = ?";

        try (Connection conn = dbConnector.getConnection();
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
            // fixme: optionally log to file, db etc.
            throw new MRSException("Could not get movies from database.", ex);
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws MRSException {
        // SQL command
        String sql = "DELETE FROM dbo.Movie WHERE ID = ?;";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            // Bind parameters
            stmt.setInt(1, movie.getId());

            // Run the specified SQL statement
            stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            // fixme: optionally log to file, db etc.
            throw new MRSException("Could not get movies from database.", ex);
        }
    }

    public List<Movie> searchMovies(String query) throws Exception {

        //TODO Do this
        throw new UnsupportedOperationException();
    }
}
