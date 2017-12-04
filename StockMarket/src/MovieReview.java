

public class MovieReview {

	private int movie_review_id;
	private int movie_id;
	private String author;
	private String review;
	
	public int getMovieReviewID() {
		return movie_review_id;
	}
	
	public void setMovieReviewID(int movie_review_id) {
		this.movie_review_id = movie_review_id;
	}
	
	public int getMovieID() {
		return movie_id;
	}
	
	public void setMovieID(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReview() {
		return review;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	//toString()
	@Override
	public String toString() {
		return "MovieReview [movie_review_id=" + movie_id + ", movie_id=" + movie_id + ", author="
				+ author + ", review=" + review + "]";
	}		
}
