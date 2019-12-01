package Parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dao.ItunesDAO;
import entites.Playlist;
import entites.Track;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ItunesBackUpParse {

	public ItunesBackUpParse() {
	}

	public static void parse() throws IOException, SAXException, ParserConfigurationException {
		File xmlFile = new File("MusicLibrary1.xml");
		Track t1 = new Track();
		//Playlist p1 = new Playlist();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		ArrayList<String> artists = new ArrayList<>();
		ArrayList<Track> songs = new ArrayList<>();
		ArrayList<Track> playListTracks = new ArrayList<>();
		doc.getDocumentElement().normalize();

		// ItunesDAO itunes = new ItunesDAO();

		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

		NodeList tracks = doc.getElementsByTagName("dict");
		for (int i = 0; i < tracks.getLength(); i++) {
			Node track = tracks.item(i);
			// System.out.println(track.getNodeName());

			NodeList ts = (NodeList) tracks.item(i);

			for (int j = 0; j < ts.getLength(); j++) {

				Node t = ts.item(j);
				NodeList tw = (NodeList) ts.item(j);

				for (int p = 0; p < tw.getLength(); p++) {
					if (tw.item(p) != null) {
						Node z = tw.item(p);

						// Cycle through node to extract details for songs

						switch (z.getTextContent()) {
						case "Track ID":
							if (tw.item(p).getNextSibling() != null) {
								Node pID = tw.item(p).getNextSibling();
								// System.out.println(pID.getTextContent().strip());
								t1.setId(Integer.parseInt(pID.getTextContent().strip()));
							}
							break;

						case "Name":
							if (tw.item(p).getNextSibling() != null && !tw.item(p).getPreviousSibling()
									.getPreviousSibling().getTextContent().matches("\"[0-9]+\"")) {

								Node songName = tw.item(p).getNextSibling();
								t1 = new Track(songName.getTextContent().strip());
								songs.add(t1);

								t1.setName(songName.getTextContent());
							}
							break;

						case "Artist":
							if (tw.item(p).getNextSibling() != null) {
								Node artist = tw.item(p).getNextSibling();
								artists.add(artist.getTextContent().strip());
								t1.setArtist(artist.getTextContent());
							}
							break;

						case "Album":
							if (tw.item(p).getNextSibling() != null) {
								Node album = tw.item(p).getNextSibling();
								// System.out.println(album.getTextContent().strip());
								t1.setAlbum(album.getTextContent());
							}
							break;

						case "Playlists":
							break;

						}

					}

				}
				if (t1.getId() != 0 && t1.getName() != null && t1.getArtist() != null && t1.getAlbum() != null) {

					songs.add(t1);
				}
			}

		}

//		System.out.println(artists);
//		 System.out.println(songs);

			for (Track s: songs) {
				ItunesDAO.persist(s);

			}
//	

		NodeList playlists = doc.getElementsByTagName("dict");
		
			for (int i = 0; i < playlists.getLength(); i++) {
				Node pl = tracks.item(i);
				// System.out.println(track.getNodeName());

				NodeList ts = (NodeList) playlists.item(i);
				System.out.println(ts);
				for (int j = 0; j < ts.getLength(); j++) {
					if (ts.item(j) != null) {
						Node t = ts.item(j);
						NodeList tw = (NodeList) ts.item(j);
							
						for (int p = 0; p < tw.getLength(); p++) {
							if (tw.item(p) != null) {
								Node z = tw.item(p);
								Node w = (Node) z.getParentNode().getParentNode().getParentNode();

								NodeList po = (NodeList) tw.item(p);

								for (int pi = 0; pi < po.getLength(); pi++) {
									Node ll = po.item(pi);
									
									 

									if (ll != null && ll.getTextContent().equals("Playlist ID")) {
										Node playListID = ll.getNextSibling();
										// System.out.println(playListID.getPreviousSibling().getPreviousSibling().getPreviousSibling().getTextContent());

										Node playListName = playListID.getPreviousSibling().getPreviousSibling()
												.getPreviousSibling();

										 //System.out.println(playListID.getTextContent());
										 //System.out.println(po.item(pi).getTextContent());
									}
									if (ll != null && ll.getTextContent().equals("Playlist Items")) 
									 {
											NodeList plTracks = (NodeList) po.item(pi);

											//System.out.println(plTracks.item(i).getTextContent());
											
											for (int counter = 0; counter < plTracks.getLength(); counter++)
											{
												
												Node plt = plTracks.item(counter);
												System.out.println(plt.getNextSibling().getChildNodes());
												
											}
										} 
								

									}
								}

							}
						}
					}
				}
			
		
	}
}
