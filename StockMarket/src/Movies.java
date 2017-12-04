
import java.util.List;
import java.util.Scanner;

public class Movies {

	public static void movieInit() {
		int control = 1;
		Scanner scanner = new Scanner(System.in);

		while (control == 1) {
			System.out.println("Movie Info (0)\n" + "Top Movies (1)\n" + "Reviews (2)\n" + "Exit (3)\n");
			int choice = scanner.nextInt();

			switch (choice) {
			case (0):
				System.out.println("Enter the id of the movie: ");
				int id = scanner.nextInt();
				movieInfo(id);
				break;
			case (1):
				System.out.println("Enter lower bound of year: ");
				int lower_year = scanner.nextInt();
				System.out.println("Enter upper bound of year: ");
				int upper_year = scanner.nextInt();
				topMovieInfo(lower_year, upper_year);
				break;
			case (2):
				System.out.println("Enter the id of the movie: ");
				int movie_id = scanner.nextInt();
				movieReviewInfo(movie_id);
				break;
			case (3):
				control = 0;
				break;
			default:
				System.out.println("Invalid Choice: ");
				break;
			}
		}
	}

	private static void movieInfo(int id) {
		System.out.println("----------------------------");
		Movie movie = MovieCommunications.getMovie(id);
		System.out.println("Movie ID: " + movie.getMovieID());
		System.out.println("Title: " + movie.getTitle());
		System.out.println("Production Year: " + movie.getProductionYear());
		System.out.println("Rating: " + movie.getRating());
		System.out.println("----------------------------");
	}

	private static void topMovieInfo(int lower_year, int upper_year) {
		List<Movie> top_movie = MovieCommunications.getTopMovieInfo(lower_year, upper_year);
		System.out.println("----------------------------");
		for (int i = 0; i < top_movie.size(); i++) {
			System.out.println("Movie ID: " + top_movie.get(i).getMovieID());
			System.out.println("Title: " + top_movie.get(i).getTitle());
			System.out.println("Production Year: " + top_movie.get(i).getProductionYear());
			System.out.println("Rating: " + top_movie.get(i).getRating());
			System.out.println("----------------------------");
		}
	}

	private static void movieReviewInfo(int movie_id) {
		List<MovieReview> movie_reviews = MovieCommunications.getMovieReview(movie_id);
		System.out.println("----------------------------");
		for (int i = 0; i < movie_reviews.size(); i++) {
			System.out.println("Movie Review ID: " + movie_reviews.get(i).getMovieReviewID());
			System.out.println("Movie ID: " + movie_reviews.get(i).getMovieID());
			System.out.println("Author: " + movie_reviews.get(i).getAuthor());
			System.out.println("Review: " + movie_reviews.get(i).getReview());
			System.out.println("----------------------------");
		}

	}
}
