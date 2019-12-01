package entites;

import java.util.ArrayList;

import javax.persistence.*;

import antlr.collections.List;

public class Playlist {

	int id;

	String name;

	private List tracks = (List) new ArrayList<Track>();

	public Playlist() {

	}

	public Playlist(int id, String name, List tracks) {

		this.id = id;
		this.name = name;
		this.tracks = tracks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List getTracks() {
		return tracks;
	}

	public void setTracks(List tracks) {
		this.tracks = tracks;
	}

}
