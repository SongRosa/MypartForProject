package requestxml;

import java.io.BufferedInputStream;
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
			
			parser.setInput(text.openStream(), "utf-8");
			
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
			BufferedInputStream bis = new BufferedInputStream(text.openStream());
			parser.setInput(bis, "UTF-8");//
			
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
	
	public static ArrayList getXml_insert(InputStream is, String requestURL) {
		
		ArrayList list = new ArrayList();
		int s = 0;
		int idCount = 0;
		int nickCount = 0;
		int emailCount = 0;
		
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
			BufferedInputStream bis = new BufferedInputStream(text.openStream());
			parser.setInput(bis, "UTF-8");//
			
			int eventType = parser.getEventType();
			
			while( eventType != XmlPullParser.END_DOCUMENT) {
				switch(eventType) {
				
				case XmlPullParser.START_TAG:
					String startTag = parser.getName();
					if(startTag.equals("check")){
						s = Integer.parseInt(parser.nextText());
						Log.i("xxx", "check :::: " + s);
						
						list.add(0,s);
					}
					
					if(startTag.equals("checkID")){
						idCount = Integer.parseInt(parser.nextText());
						Log.i("xxx", "idCount:::: " + idCount);
						list.add(1,idCount);
					}

					if(startTag.equals("checkNick")){
						Log.i("xxxx", "id태그 찾음");
						nickCount = Integer.parseInt(parser.nextText());
						Log.i("xxx", "nickCount:::: " + nickCount);
						list.add(2,nickCount);
					}

					if(startTag.equals("checkEmail")){
						Log.i("xxxx", "id태그 찾음");
						emailCount = Integer.parseInt(parser.nextText());
						Log.i("xxx", "emailCount:::: " + emailCount);
						list.add(3,emailCount);
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
}
