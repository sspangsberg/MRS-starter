package easv.mrs.DAL;

// Project imports
import easv.mrs.BE.Movie;
import easv.mrs.util.MRSException;

// Java imports
import java.util.List;

public interface IMovieDataAccess {

    List<Movie> getAllMovies() throws MRSException;

    Movie createMovie(Movie movie) throws MRSException;

    void updateMovie(Movie movie) throws MRSException;

    void deleteMovie(Movie movie) throws MRSException;
}
