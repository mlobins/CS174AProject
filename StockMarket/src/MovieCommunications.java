
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieCommunications {

	public static Movie getMovie(int id) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		Movie movie = null;
		String query = "SELECT * FROM Movies WHERE id = " + id + ";";
		try {
			connection = JDBCMySQLConnection.getConnectionMOV();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			if (rs.next()) {
				movie = new Movie();
				movie.setMovieID(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setRating(rs.getInt("rating"));
				movie.setProductionYear(rs.getString("production_year"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return movie;
	}

	public static List<Movie> getMovies() {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = null;
		String query = "SELECT * FROM Movies;";
		try {
			connection = JDBCMySQLConnection.getConnectionMOV();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				movie = new Movie();
				movie.setMovieID(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setRating(rs.getInt("rating"));
				movie.setProductionYear(rs.getString("production_year"));
				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return movies;
	}

	public static List<Movie> getTopMovieInfo(int lower_year, int upper_year) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = null;
		String query = "SELECT * FROM Movies WHERE production_year > " + lower_year + " AND production_year < "
				+ upper_year + ";";
		try {
			connection = JDBCMySQLConnection.getConnectionMOV();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				movie = new Movie();
				movie.setMovieID(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setRating(rs.getInt("rating"));
				movie.setProductionYear(rs.getString("production_year"));
				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return movies;
	}

	public static List<MovieReview> getMovieReview(int movie_id) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;

		List<MovieReview> movie_reviews = new ArrayList<MovieReview>();
		MovieReview movie_review = null;
		String query = "SELECT * FROM Reviews WHERE movie_id = " + movie_id + ";";
		try {
			connection = JDBCMySQLConnection.getConnectionMOV();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				movie_review = new MovieReview();
				movie_review.setMovieReviewID(rs.getInt("id"));
				movie_review.setMovieID(rs.getInt("movie_id"));
				movie_review.setAuthor(rs.getString("author"));
				movie_review.setReview(rs.getString("review"));
				movie_reviews.add(movie_review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return movie_reviews;
	}
}
