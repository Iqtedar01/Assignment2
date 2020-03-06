package assignmentwo;
import java.io.File;
import java.util.*;

public class Assignmentwo {

	public static void main(String[] args) throws Exception {
    String[] files = { "regional-global-weekly-2020-01-17--2020-01-24.csv" };
		LinkedList<Song> list = new LinkedList<>(); 
                // enchance for loop 
		for (String s : files) {
			readFile(list, s);
		}
                // making history object and playlist object
		PlayList playlist = new PlayList(list);
		History history = new History();
		
		// testing 
		System.out.println("Next Songs: ");
		Song currentSong = nextSong(history, playlist);
		System.out.println(currentSong);
		currentSong = nextSong(history, playlist);
		System.out.println(currentSong);
		currentSong = nextSong(history, playlist);
		System.out.println(currentSong);
		currentSong = nextSong(history, playlist);
		System.out.println(currentSong);
		currentSong = nextSong(history, playlist);
		System.out.println(currentSong);
		currentSong = nextSong(history, playlist);
		System.out.println(currentSong);
		
		// testing previous song/ history 
		System.out.println("\nPrevious Songs:");
		Song prevSong = getPreviousSong(history);
		System.out.println(prevSong);
		prevSong = getPreviousSong(history);
		System.out.println(prevSong);
		prevSong = getPreviousSong(history);
		System.out.println(prevSong);
		prevSong = getPreviousSong(history);
		System.out.println(prevSong);
		prevSong = getPreviousSong(history);
		System.out.println(prevSong);
		prevSong = getPreviousSong(history);
		System.out.println(prevSong);
		

	}
	// removing the first song from playlist and adding into to history 
	public static Song nextSong(History history, PlayList playlist) {
		Song s = playlist.removeSong();
		history.addSong(s);
		return s;
	}
	

	public static Song getPreviousSong(History history) {
		return history.removeSong();
	}
                
               // reading songs from file and puts it into a sorted array 
	public static void readFile(LinkedList<Song> list, String filename) throws Exception {
		File file = new File(filename);
                
		// Reads the file input
		Scanner reader = new Scanner(file);
		reader.nextLine(); // reading the next line
		reader.nextLine(); // reading the next line

		while (reader.hasNext()) {

			// stores the next line of the file
			String line = reader.nextLine();
			String[] track = line.split(","); // splits by comma
			String name = track[1];
			String artist = track[2];
			Song song = new Song(name, artist);
			list.add(song);
		}
                    // sorts the list 
		Collections.sort(list);

	}

}
   
class Song implements Comparable {

	// instance variables
	private String name, artist;

	public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
	}
             // getter methods for get name and artist 
	public String getName() {
		return name;
	}

	public String getArtist() {
		return artist;
	}
            // overiding campareTo method
	@Override
	public int compareTo(Object o) {
		return name.compareToIgnoreCase(((Song) o).name);
	}
            // overriding toString method
	@Override
	public String toString() {
		return name + " " + artist;
	}
	
}

class History {

	// A stack
	// Push - puts obj into statck
	// Pop - removes obj from stack
	Deque<Song> history;

	public History() {
		history = new LinkedList<>();
	}

	public Deque<Song> getHistory() {
		return history;
	}

	public void addSong(Song s) {
		history.push(s);
	}

	public Song removeSong() {
		return history.pop();
	}

}

class PlayList {

	// Offer - adds
	// Poll - remove
	Queue<Song> playlist;

	public PlayList(LinkedList<Song> list) {
		playlist = list;
	}

	public Queue<Song> getPlaylist() {
		return playlist;
	}

	public void addSong(Song s) {
		playlist.offer(s);
	}

	public Song removeSong() {
		return playlist.poll();
	}

}