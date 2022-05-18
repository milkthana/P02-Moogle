// Name: Thanatorn Prangsrithong
// Student ID: 6188036
// Section: 2

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Movie {
	private int mid;
	private String title;
	private int year;
	private Set<String> tags;
	private Map<Integer, Rating> ratings;	//mapping userID -> rating
	private Double avgRating;
	//additional
	
	public Movie(int _mid, String _title, int _year){
		// YOUR CODE GOES HERE
			mid=_mid; 
			title=_title;
			year=_year;
			tags = new HashSet<String>();
			//no same value in Hashset
			ratings = new HashMap<Integer, Rating>();
			//Hashmap has both key and variable
			//Double value can not use 0 so use 0.0
			avgRating = 0.0;		
			//could be 0.00
	}
	
	public int getID() {
		
		// YOUR CODE GOES HERE
		//return the value
		return mid;
	}
	public String getTitle(){
		
		// YOUR CODE GOES HERE
		//return the value
		return title;
	}
	
	public Set<String> getTags() {
		
		// YOUR CODE GOES HERE
		//return the value
		return tags;
	}
	
	public void addTag(String tag){
		
		// YOUR CODE GOES HERE
		tags.add(tag); 
		//add tags to Set
	}
	
	public int getYear(){
		
		// YOUR CODE GOES HERE
		//return the value
		return year;
	}
	
	public String toString()
	{
		return "[mid: "+mid+":"+title+" ("+year+") "+tags+"] -> avg rating: " + avgRating;
	}
	
	
	public double calMeanRating(){
		
		// YOUR CODE GOES HER
		//Loop for Calculate
		avgRating = 0.0;
		for(Rating uid : ratings.values()) 
		{
			avgRating += uid.rating;
			//avgRating += rate.rating;
			//Solution to solve avg
		}
		//return the value and its solution
		//avgRating += ((Rating) this.ratings.get(uid)).getRating();
		//avgRating = avgRating / ratings.size();
		//System.out.println(avgRating);
		if(ratings.size()==0) {
			return -1.0;
		}
		else {
			avgRating = avgRating/ratings.size();
			return avgRating;
		}
		
	}
	
	public Double getMeanRating(){
		
		// YOUR CODE GOES HERE
		//return the value
		return avgRating;
	}
	
	public void addRating(User user, Movie movie, double rating, long timestamp) {
		// YOUR CODE GOES HERE
		Rating rate = new Rating(user, movie, rating, timestamp);
		//input new value to Rating class
		ratings.put(user.uid, rate);
		//insert a mapping into a map
		
	}
	
	public Map<Integer, Rating> getRating(){
		
		// YOUR CODE GOES HERE
		// Return to ratings
		return ratings;
	}
	
}
