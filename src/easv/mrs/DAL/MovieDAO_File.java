package easv.mrs.DAL;

// Project imports
import easv.mrs.BE.Movie;
import easv.mrs.util.MRSException;

// Java imports
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.APPEND;


public class MovieDAO_File implements IMovieDataAccess {

    private static final String MOVIES_FILE = "data/movie_titles.txt";

    @Override
    public List<Movie> getAllMovies() throws MRSException {

        try
        {
            // Read all lines from file
            List<String> lines = Files.readAllLines(Path.of(MOVIES_FILE));
            List<Movie> movies = new ArrayList<>();

            // Parse each line as movie
            for (String line: lines) {
                String[] separatedLine = line.split(",");

                int id = Integer.parseInt(separatedLine[0]);
                int year = Integer.parseInt(separatedLine[1]);
                String title = separatedLine[2];
                if(separatedLine.length > 3)
                {
                    for(int i = 3; i < separatedLine.length; i++)
                    {
                        title += "," + separatedLine[i];
                    }
                }
                Movie movie = new Movie(id, year, title);
                movies.add(movie);
            }

            return movies;
        }
        catch (IOException ex) {
            // fixme: log to file,db etc.
            throw new MRSException("Could not read all movies from file.", ex);
        }
    }

    @Override
    public Movie createMovie(Movie movie) throws MRSException {
        try
        {
            List<String> movies = Files.readAllLines(Path.of(MOVIES_FILE));

            if (movies.size() > 0) {
                // get next id
                String[] separatedLine = movies.get(movies.size() - 1).split(",");
                int nextId = Integer.parseInt(separatedLine[0]) + 1;
                String newMovieLine = nextId + "," + movie.getYear() + "," + movie.getTitle();
                Files.write(Path.of(MOVIES_FILE), (newMovieLine + "\r\n").getBytes(), APPEND);

                return new Movie(nextId, movie.getYear(), movie.getTitle());
            }
            return null;
        }
        catch (IOException ex) {
            // fixme: log to file,db etc.
            throw new MRSException("Could not read all movies from file.", ex);
        }
    }

    @Override
    public void updateMovie(Movie movie) throws MRSException {
    }

    @Override
    public void deleteMovie(Movie movie) throws MRSException {
    }
}