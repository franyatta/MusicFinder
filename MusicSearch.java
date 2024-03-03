import java.io.*;
import java.util.*;

public class MusicSearch {
	List<Artist> artists = new ArrayList<>();
	List<Album> albums = new ArrayList<>();
	List<Song> songs = new ArrayList<>();

	void menu() {
		try (Scanner in = new Scanner(System.in)) {
			readFile();
			int response;

			do {
				System.out.println("1. Search for Album\n2. Search for Song\n0. Exit");
				response = in.nextInt();
				in.nextLine();

				switch (response) {
				case 1:
					System.out.println("Enter the name of the Album you're looking for: ");
					String albumName = in.nextLine();
					printAlbumInfo(findAlbum(albumName));
					break;
				case 2:
					System.out.println("Enter the name of the Song you're looking for:");
					String songName = in.nextLine();
					printSongInfo(songSearch(songName));
					break;
				case 0:
					System.out.println("Goodbye!");
					break;
				case 5:
					Collections.sort(songs);
					for (Song s : songs)
						System.out.println(s);
					break;
				default:
					System.err.println("Error with input");
				}
			} while (response != 0);
		} catch (InputMismatchException e) {
			System.err.println("Error with input. Please enter a valid number.");
		}
	}

	public Song songSearch(String songName) {
		// Uses binary search to find the song and its contents
		Collections.sort(songs);
		int left = 0;
		int right = songs.size() - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			Song midSong = songs.get(mid);

			int result = songName.compareTo(midSong.getName());

			if (result == 0) {
				return midSong;
			} else if (result < 0) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return null;
	}

	private Album findAlbum(String albumName) {
		// Uses sequential search algorithm to search for album name
		for (Album album : albums) {
			if (album.getName().equalsIgnoreCase(albumName)) {
				return album;
			}
		}
		return null;
	}

	public void printSongInfo(Song song) {
		System.out.println("Song Name: " + song.getName() + "\nDuration: " + formatDuration(song.getSongDuration())
				+ "\nArtist Name: " + song.getArtist() + "\nAlbum Name: " + song.getAlbum() + "\n");
	}

	private void printAlbumInfo(Album album) {
		System.out.println("Album Name: " + album.getName() + "\nArtist Name: " + album.getArtist().getName()
				+ "\nNumber of Songs: " + album.getNumOfSongs() + "\nTotal Duration: "
				+ formatDuration(album.getAlbumDuration()) + "\n");

	}

	public void readFile() {
		// Reads in Artists.txt file
		try (BufferedReader in = new BufferedReader(new FileReader("Artists.txt"))) {
			String line;
			while ((line = in.readLine()) != null) {
				String[] artistAndAlbums = line.split(",");
				if (artistAndAlbums.length >= 2) {
					String artistName = artistAndAlbums[0];
					Artist artist = new Artist(artistName);

					for (int i = 1; i < artistAndAlbums.length; i++) {
						String albumName = artistAndAlbums[i];
						Album album = new Album(albumName, artist, 0, 0, new ArrayList<Song>());
						albums.add(album);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Reads in Songs.txt file
		try (BufferedReader br = new BufferedReader(new FileReader("Songs.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] songData = line.split(",");
				if (songData.length == 4) {
					String songName = songData[0];
					double duration = Double.parseDouble(songData[1]);
					String artistName = songData[2];
					String albumName = songData[3];

					// Find the corresponding album for the song
					Album album = findAlbum(albumName);
					if (album != null) {
						Song song = new Song(songName, duration, artistName, albumName);
						album.getSongs().add(song);
						album.setNumOfSongs(album.getNumOfSongs() + 1);
						album.setAlbumDuration(album.getAlbumDuration() + duration);

						songs.add(song);
					}
				}
			}
		} catch (IOException | NumberFormatException e) {
			System.err.println("Error!");
		}
	}

	public double calculateTotalDuration() {
		double sum = 0.0;
		for (Song song : songs) {
			sum += song.getSongDuration();
		}
		return sum;
	}

	// Reformats the duration so it is more readable
	public String formatDuration(double duration) {
		int minutes = (int) duration;
		int seconds = (int) ((duration - minutes) * 60);
		return minutes + " minutes and " + seconds + " seconds";
	}
}
