package entites;


import javax.persistence.*;

@Entity
public class Track {
	
	@Id
	int id;
	String name;
	String artist;
	String album;
	
	
	public Track() {}
	
	public Track(int id, String name, String artist, String album) {
		
		this.id = id;
		this.name = name; 
		this.artist = artist;
		this.album = album;
		
	}
	@Override
	public String toString() {
		return "Track [id=" + id + ", name=" + name + ", artist=" + artist + ", album=" + album + "]";
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Track(String name) {
		this.name = name;
		}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
