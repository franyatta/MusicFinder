import java.util.ArrayList;

public class Album extends MusicSearch {
	
	String name;
	Artist artist;
	int numOfSongs;
	double albumDuration;
	ArrayList<Song> songs;
	

	public Album(String name, Artist artist, int numOfSongs, double albumDuration, ArrayList<Song> songs) {
		this.name = name;
		this.artist = artist;
		this.numOfSongs = numOfSongs;
		this.albumDuration = albumDuration;
		this.songs = songs;
        calculateTotalDuration();
	}
	
	
	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public int getNumOfSongs() {
		return numOfSongs;
	}

	public void setNumOfSongs(int numOfSongs) {
		this.numOfSongs = numOfSongs;
	}

	public double getAlbumDuration() {
		return albumDuration;
	}

	public void setAlbumDuration(double albumDuration) {
		this.albumDuration = albumDuration;
	}

	public ArrayList<Song> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}
	
	
}

