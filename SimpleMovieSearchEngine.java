//Name: Thanatorn Prangsrithong
//Student ID: 6188036
//Section: 2

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleMovieSearchEngine implements BaseMovieSearchEngine {
	
	public Map<Integer, Movie> movies;
	Map<Integer,String> recommend = new HashMap<Integer,String>();
	@Override
	public Map<Integer, Movie> loadMovies(String movieFilename) {
		
		// YOUR CODE GOES HERE
		Map<Integer, Movie> movieMap = new HashMap<Integer, Movie>();
		String[] data;
		int year = 0;
		//Create pattern type 1 and 2 to read
		String pattern1 = "([0-9]+),(.*)\\(([0-9]+)\\),(.*)";				
		String pattern2 = "([0-9]+),\\\"(.*)\\(([0-9]+)\\)\\\",(.*)";
		Pattern read1 = Pattern.compile(pattern1);							
		Pattern read2 = Pattern.compile(pattern2);
		try {
			String sp;
			//FileReader read = new FileReader(ratingFilename);
			String movieF = movieFilename;
			BufferedReader movieB = new BufferedReader(new FileReader(movieF));
			movieB.readLine();
			while((sp=movieB.readLine())!=null) {
				//Reader.readLine() not equal to null;
				data = sp.split(",");
				//split by add , 
				Pattern yearP = Pattern.compile("\\(\\d{4}\\)");
				//year Pattern
				Matcher yearMatch = yearP.matcher(data[1]);
				//long TS = Long.parseUnsignedLong(data[2]);
				
	            if (data.length == 3) {
	                if (yearMatch.find()) {
	                    year = Integer.parseInt(data[1].substring(yearMatch.start() + 1, yearMatch.end() - 1));
	                }
	            }
	                else {
	                    data = sp.split("\""); 
	                    yearMatch = yearP.matcher(data[1]);
	                    if (yearMatch.find()) {
	                        year = Integer.parseInt(data[1].substring(yearMatch.start() + 1, yearMatch.end() - 1));
	                    }
	                    
	                    data[0] = data[0].replace(",", "");
	                    data[2] = data[2].replace(",", "");
	                   // System.out.println(data[2]);
	                }
	            Movie movies = new Movie(Integer.parseInt(data[0]), data[1].substring(0, data[1].length()-7), year);
	            String[] tags = data[2].split("\\|"); 
	            for (String tag : tags) {
	            	movies.addTag(tag); 
	                //System.out.println(tag);
	            }
	            movieMap.put(movies.getID(), movies);
	        }
			movieB.close();
	            
			 
		}catch(IOException e) {
			return null;
		}
		return movieMap;
	}

	@Override
	public void loadRating(String ratingFilename) {

		// YOUR CODE GOES HERE
		String[] sp;
		try {
			FileReader read = new FileReader(ratingFilename);
			BufferedReader movieB = new BufferedReader(read);
			String line;
			movieB.readLine();
			while((line=movieB.readLine())!=null) {
				
				 sp = line.split(","); 
				 //split by add ,
				 int uid = Integer.parseInt(sp[0]);
				 User u = new User(uid);
				  
				 int mid = Integer.parseInt(sp[1]);
				 double rating = Double.parseDouble(sp[2]);
				 long TS = Long.parseUnsignedLong(sp[3]); 
		            
		          if(movies.containsKey(mid)) movies.get(mid).addRating(u, movies.get(mid), rating, TS);
		          for(Integer movieID: movies.keySet()) movies.get(movieID).calMeanRating();
		        }
	        
			 movieB.close();
	            
			 
		}catch(IOException e) {
			return;
		}
	
	
	}

	@Override
	public void loadData(String movieFilename, String ratingFilename) {
		
		// YOUR CODE GOES HERE
		movies = loadMovies(movieFilename);
		loadRating(ratingFilename);
		//System.out.println(movies);
	}

	@Override
	public Map<Integer, Movie> getAllMovies() {

		// YOUR CODE GOES HERE 
		   if (movies != null) {
			   //if not equal to null return movies
	            return movies;
	        }
	        return new TreeMap<Integer, Movie>();
	}

	@Override
	public List<Movie> searchByTitle(String title, boolean exactMatch) {

		// YOUR CODE GOES HERE
		List<Movie> searchtitle= new ArrayList<Movie>();
		//Create ArrayList of Movie for search Title
		for(Integer num:movies.keySet()) {
			String _title=movies.get(num).getTitle().toLowerCase();
			if(exactMatch==true) {
				if(_title.matches(title)) searchtitle.add(movies.get(num));
			}
			else if(exactMatch==false) {
					if(_title.contains(title)) {
						searchtitle.add(movies.get(num));
					}
			}
		}
		return searchtitle;
	}

	@Override
	public List<Movie> searchByTag(String tag) {

		List<Movie> searchTag= new ArrayList<Movie>();
		for(Integer num:movies.keySet()) {
			Set<String> _tags=movies.get(num).getTags();
					for(String Tag:_tags) {
						if(tag.contains(Tag)) {
							searchTag.add(movies.get(num));}
						}
					}
		return searchTag;
	}

	@Override
	public List<Movie>searchByYear(int year) {

		// YOUR CODE GOES HERE
		List<Movie> searchYear = new ArrayList<Movie>();
		for(Integer num:movies.keySet()) {
			int _Year = movies.get(num).getYear();
			//Test
			//System.out.println(_Year);
			int cop = Integer.compare(_Year, year);
			if(cop==0) {
				searchYear.add(movies.get(num));
			}
		}

		return searchYear;
	}
	
	public List<Movie>searchByYeartoYear(int year1,int year2) {

		// YOUR CODE GOES HERE
		List<Movie> searchYear2 = new ArrayList<Movie>();
		for(Integer num:movies.keySet()) {
			int _Year = movies.get(num).getYear();
			//Test
			//System.out.println(_Year);
			int cop = Integer.compare(_Year, year1);
			//if(cop==0) { searchYear2.add(movies.get(key)); }
			if(_Year>=year1&&_Year<=year2) {
				searchYear2.add(movies.get(num));
			}
		}

		return searchYear2;
	}

	@Override
	public List<Movie> advanceSearch(String title, String tag, int year) {

		// YOUR CODE GOES HERE
		List<Movie> searchAd= new ArrayList<Movie>();
		for(Integer num:movies.keySet()) {
			Set<String> _tags=movies.get(num).getTags();
			String _title=movies.get(num).getTitle().toLowerCase();
			int _Year = movies.get(num).getYear();
			int cop = Integer.compare(_Year, year);
			int checkTag = 0;
			boolean checkYear = true; 
			//check whether year is null or not
				if(year>0) {
					checkYear = false;
				}
					for(String Tag:_tags) {
						if(Tag.contains(tag)) checkTag = 1;
					}
					if(title==null) {
						if(tag!=null) {
							if(checkTag==1) { 
								if(cop==0) searchAd.add(movies.get(num));
							}
						}
						//if(checkTag==1&&cp==0) {byTitaY.add(movies.get(key))};
					}
					else if(title!=null) {
						if(_title.contains(title)) 
						{
							if(tag!=null&&checkTag==1&&checkYear==false&&cop==0) {
								searchAd.add(movies.get(num));
							}
							else if(tag!=null&&checkTag==1&&checkYear==true) {
								searchAd.add(movies.get(num));
							}
							else if(tag==null&&checkYear==false&&cop==0) {
								searchAd.add(movies.get(num));
							}
						}								
					}				
		}
		return searchAd;		
	}

	@Override
	public List<Movie> sortByTitle(List<Movie> unsortedMovies, boolean asc) {

		// YOUR CODE GOES HERE
		List<Movie> sortTitle= new ArrayList<Movie>();
		for(Movie list : unsortedMovies) sortTitle.add(list);{
		Collections.sort(sortTitle, new Comparator<Movie>() {
			@Override
			public int compare(Movie list1 , Movie list2) {
				String id = ((Movie) list1).getTitle();
				String id1 = ((Movie) list2).getTitle();
				//sortTitle.sort(Comparator.comparing(Movie::getTitle));
				 if(asc==true) {
					 return id.compareTo(id1);
				 }
				 else {
					 return id1.compareTo(id);
				 }
			}
		});
		}
			return sortTitle;
	}

	@Override
	public List<Movie> sortByRating(List<Movie> unsortedMovies, boolean asc) {
		// TODO Auto-generated method stub
		List<Movie> sortRating = new ArrayList<>();
		for(Movie list : unsortedMovies) sortRating.add(list);
		Collections.sort(sortRating, new Comparator<Movie>() {
			@Override
			public int compare(Movie list1, Movie list2) {
				Double id = ((Movie) list1).getMeanRating();
				Double id1 = ((Movie) list2).getMeanRating();

				 if(asc==true) {
					 return id.compareTo(id1);
				 }

				 else {
					 return id1.compareTo(id);
				 }
			}	 
		});
		return sortRating;
	}
}
