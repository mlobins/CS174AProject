

public class Movie {

	private int movie_id;
	private String title;
	private float rating;
	private String production_year;
	
	public int getMovieID() {
		return movie_id;
	}
	
	public void setMovieID(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public float getRating() {
		return rating;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public String getProductionYear() {
		return production_year;
	}
	
	public void setProductionYear(String production_year) {
		this.production_year = production_year;
	}
	
	//toString()
	@Override
	public String toString() {
		return "Movie [movie_id=" + movie_id + ", title=" + title + ", rating="
				+ rating + ", production_year=" + production_year + "]";
	}		
}
