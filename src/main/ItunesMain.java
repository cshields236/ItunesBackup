package main;


import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Parser.ItunesBackUpParse;

public class ItunesMain {

	
	public static void main(String [] args) throws IOException, SAXException, ParserConfigurationException {
		 ItunesBackUpParse backup = new ItunesBackUpParse();

		 ItunesBackUpParse.parse();
	}
}
