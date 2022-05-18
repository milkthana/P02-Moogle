
//Name: Thanatorn Prangsrithong
//Student ID: 6188036
//Section: 2
public class Rating {
	public Movie m;
	public User u;
	public double rating;	//rating can be [0, 5]
	public long timestamp;	//timestamp tells you the time this rating was recorded
	
	
	public Rating(User _u, Movie _m, double _rating, long _timestamp) {
		
		// YOUR CODE GOES HERE
		u = _u; 
		m = _m;
		if(rating>=0.0 && rating<=5.0) {
			rating = _rating;
		}
		timestamp = _timestamp;
	}
	
	
	public String toString() {
		return "[uid: " + u.getID() +" mid: "+m.getID() +" rating: "+rating+"/5 timestamp: "+timestamp+"]";
	}


	public Double getRating() {
		 //TODO Auto-generated method stub
		if(rating>=0.0 && rating<=5.0) return rating;
		else return -1.0;
	}
}
