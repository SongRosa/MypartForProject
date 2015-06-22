package requestxml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.example.hanandroidproject.dto.MemberDTO;

public class RequestXml_Member {
	public static InputStream requestGet(String requestURL){
		try{
			HttpClient client = new DefaultHttpClient();			
			HttpGet get = new HttpGet(requestURL);			
			HttpResponse response = client.execute(get);			
			HttpEntity entity=response.getEntity();			
			InputStream is = entity.getContent();
			return is;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void requestGet_memberInsert(String requestURL, String id, String pwd, String name, String nick, String email){
		try{
			
			HttpClient client = new DefaultHttpClient();
			
			System.out.println("requestURL :::::: " + requestURL);
			requestURL = requestURL + "?id="+id+"&pwd="+pwd+"&name="+name+"&nick"+nick+"&email"+email; 
			
			System.out.println(requestURL);
			
			HttpGet get = new HttpGet(requestURL);
			System.out.println("HttpGet");
			
			HttpResponse response = client.execute(get);
			System.out.println("execute");
			
			HttpEntity entity = response.getEntity();
			System.out.println("getEntity");
			
			InputStream is = entity.getContent();
			System.out.println("getContent");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
