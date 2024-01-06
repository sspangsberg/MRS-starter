package easv.mrs.BLL;

// Project imports
import easv.mrs.BE.Movie;
import easv.mrs.BLL.util.MovieSearcher;
import easv.mrs.DAL.IMovieDataAccess;
import easv.mrs.DAL.db.MovieDAO_DB;
import easv.mrs.util.MRSException;

// Java imports
import java.util.List;

public class MovieManager {

    private MovieSearcher movieSearcher = new MovieSearcher();
    private IMovieDataAccess movieDAO;

    public MovieManager() throws MRSException {
        //movieDAO = new MovieDAO_Mock();
        //movieDAO = new MovieDAO_File();
        movieDAO = new MovieDAO_DB();
    }

    public List<Movie> getAllMovies() throws MRSException {
        return movieDAO.getAllMovies();
    }

    public List<Movie> searchMovies(String query) throws MRSException {
        List<Movie> allMovies = getAllMovies();
        List<Movie> searchResult = movieSearcher.search(allMovies, query);
        return searchResult;
    }

    public Movie createNewMovie(Movie newMovie) throws MRSException {
        return movieDAO.createMovie(newMovie);
    }

    public void updateMovie(Movie selectedMovie) throws MRSException {
        movieDAO.updateMovie(selectedMovie);
    }

    public void deleteMovie(Movie selectedMovie) throws MRSException {
        movieDAO.deleteMovie(selectedMovie);
    }
}
