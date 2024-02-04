package easv.mrs.GUI.Model;

import easv.mrs.BE.Movie;
import easv.mrs.BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class MovieModel {

    private ObservableList<Movie> moviesToBeViewed;

    private MovieManager movieManager;


    public MovieModel() {
        try {
            movieManager = new MovieManager();
            moviesToBeViewed = FXCollections.observableArrayList();
            moviesToBeViewed.addAll(movieManager.getAllMovies());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Movie> getObservableMovies() {
        return moviesToBeViewed;
    }


    public void searchMovie(String query) throws Exception {
        List<Movie> searchResults = movieManager.searchMovies(query);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(searchResults);
    }


    public void createNewMovie(Movie newMovie)  {
        Movie m = null;
        try {
            m = movieManager.createNewMovie(newMovie);
            moviesToBeViewed.add(m); // update list
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void updateMovie(Movie updatedMovie) throws Exception {
        // update movie in DAL layer (through the layers)
        movieManager.updateMovie(updatedMovie);

        // update observable list (and UI)
        Movie m = moviesToBeViewed.get(moviesToBeViewed.indexOf(updatedMovie));
        m.setTitle(updatedMovie.getTitle());
        m.setYear(updatedMovie.getYear());
    }


    public void deleteMovie(Movie selectedMovie) throws Exception {
        // delete movie in DAL layer (through the layers)
        movieManager.deleteMovie(selectedMovie);

        // remove from observable list (and UI)
        moviesToBeViewed.remove(selectedMovie);
    }
}
