
public class Song implements Comparable<Song> {

	String name;
	double songDuration;
	String artist;
	String album;

	public Song(String name, double songDuration, String artist, String album) {

		this.name = name;
		this.songDuration = songDuration;
		this.artist = artist;
		this.album = album;
	}

	@Override
	public int compareTo(Song other) {
		// Override the compareTo method to compare songs by ASCII value in order to
		// use Collections.sort on the list for binary search
		return this.getName().compareTo(other.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSongDuration() {
		return songDuration;
	}

	public void setSongDuration(int songDuration) {
		this.songDuration = songDuration;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

}
