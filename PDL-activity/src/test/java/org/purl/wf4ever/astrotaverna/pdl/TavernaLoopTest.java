package org.purl.wf4ever.astrotaverna.pdl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class TavernaLoopTest {

	@Test
	public void finishedTest(){
		String status = "running";
		String loop = "" + (status.toLowerCase().compareTo("running")==0 || status.toLowerCase().compareTo("pending")==0);
		System.out.println(loop);
		if ("true".matches(loop)) {
		   System.out.println("Go to sleep");
		}
		else{
		   System.out.println("Finish");	
		}
		
	}

}
