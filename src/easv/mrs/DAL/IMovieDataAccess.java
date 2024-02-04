package easv.mrs.DAL;

import easv.mrs.BE.Movie;

import java.util.List;

public interface IMovieDataAccess {

    List<Movie> getAllMovies() throws Exception;
    Movie createMovie(Movie movie) throws Exception;
    void updateMovie(Movie movie) throws Exception;
    void deleteMovie(Movie movie) throws Exception;
}
