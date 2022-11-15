package easv.mrs.bll;

import easv.mrs.be.Movie;
import easv.mrs.bll.util.MovieSearcher;
import easv.mrs.dal.IMovieDataAccess;
import easv.mrs.dal.MovieDAO_Mock;

import java.util.List;

public class MovieManager {

    private MovieSearcher movieSearcher = new MovieSearcher();

    private IMovieDataAccess movieDAO;

    public MovieManager() {
        movieDAO = new MovieDAO_Mock();
    }

    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    public List<Movie> searchMovies(String query) throws Exception {
        List<Movie> allMovies = getAllMovies();
        List<Movie> searchResult = movieSearcher.search(allMovies, query);
        return searchResult;
    }

}
