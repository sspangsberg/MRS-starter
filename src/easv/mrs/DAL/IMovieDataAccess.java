package easv.mrs.DAL;

import easv.mrs.BE.Movie;
import easv.mrs.util.MRSException;

import java.util.List;

public interface IMovieDataAccess {

    public List<Movie> getAllMovies() throws MRSException;

    public Movie createMovie(Movie movie) throws MRSException;

    public void updateMovie(Movie movie) throws MRSException;

    public void deleteMovie(Movie movie) throws MRSException;
}
