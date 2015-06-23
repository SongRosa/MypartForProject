package requestxml;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class getXML {
	
	public static int getXml(InputStream is, String requestURL) {
    	Log.i("xxx", "getXML start!");
    	URL text = null;

    	int dbCheck = 0;
		try {
			text = new URL(requestURL);
			Log.i("xxx", requestURL);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
    		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    		XmlPullParser parser = factory.newPullParser();
			
			parser.setInput(text.openStream(), "UTF-8");
			
			int eventType = parser.getEventType();
			
			while( eventType != XmlPullParser.END_DOCUMENT) {
				switch(eventType) {
				
				case XmlPullParser.START_TAG:
					String startTag = parser.getName();
					if(startTag.equals("check")){
						dbCheck = Integer.parseInt(parser.nextText());
						Log.i("xxx", "dbCheck :::: " + dbCheck); 
					}
					break;
				}				
				eventType = parser.next();
			}
			 
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
    	return dbCheck;
    }
	
	public static ArrayList getXml_search(InputStream is, String requestURL) {
	
		ArrayList list = new ArrayList();
		String id="";
		int s = 0;
		
    	Log.i("xxx", "getXML start!");
    	URL text = null;
    	
		try {
			text = new URL(requestURL);
			Log.i("xxx", requestURL);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
    		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    		XmlPullParser parser = factory.newPullParser();
			
			parser.setInput(text.openStream(), "UTF-8");
			
			int eventType = parser.getEventType();
			
			while( eventType != XmlPullParser.END_DOCUMENT) {
				switch(eventType) {
				
				case XmlPullParser.START_TAG:
					String startTag = parser.getName();
					if(startTag.equals("check")){
						Log.i("xxxx", "check태그 찾음");
						s = Integer.parseInt(parser.nextText());
						Log.i("xxx", "idCount :::: " + s);
						
						list.add(0,s);
					}
					
					if(startTag.equals("id")){
						Log.i("xxxx", "id태그 찾음");
						id = parser.nextText();
						Log.i("xxx", "id:::: " + id);
						list.add(1,id);
					}
					break;
				}								
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}

		Log.i("xxxx", "result 반환할꺼임.");
    	return list;
    }
	
	public static String getXml_updatePwd(String requestURL) {
		
		String id="";
		String s = "";
		
    	Log.i("xxx", "getXML start!");
    	URL text = null;
    	
		try {
			text = new URL(requestURL);
			Log.i("xxx", requestURL);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
    		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    		XmlPullParser parser = factory.newPullParser();
			
			parser.setInput(text.openStream(), "UTF-8");
			
			int eventType = parser.getEventType();
			
			while( eventType != XmlPullParser.END_DOCUMENT) {
				switch(eventType) {
				
				case XmlPullParser.START_TAG:
					String startTag = parser.getName();
					if(startTag.equals("check")){
						Log.i("xxxx", "check태그 찾음");
						Log.i("xxx", "idCount :::: " + s);
						
					}
					break;
				}								
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}

		Log.i("xxxx", "result 반환할꺼임.");
    	return s;
    }
}
