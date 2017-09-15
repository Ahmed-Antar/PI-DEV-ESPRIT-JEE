package tn.esprit.twin1.brogrammers.eventify.Eventify.util;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SpellCheck {

	public static void checkWord(String word) {
		
		 HttpClient httpclient = HttpClients.createDefault();

	        try
	        {
	            URIBuilder builder = new URIBuilder("https://api.cognitive.microsoft.com/bing/v5.0/spellcheck/");

	            builder.setParameter("text", "Bill Gatas");
	            /*builder.setParameter("mode", "{string}");
	            builder.setParameter("preContextText", "{string}");
	            builder.setParameter("postContextText", "{string}");*/

	         
	            URI uri = builder.build();
	            HttpGet request = new HttpGet(uri);
	            request.setHeader("Ocp-Apim-Subscription-Key", "fdb14485716b43568974ea61d8c39ca1");


	            // Request body
	           // StringEntity reqEntity = new StringEntity("{body}");
	           // request.setEntity(reqEntity);

	            HttpResponse response = httpclient.execute(request);
	            HttpEntity entity = response.getEntity();

	            if (entity != null) 
	            {
	                System.out.println(EntityUtils.toString(entity));
	            }
	        }
	        catch (Exception e)
	        {
	            System.out.println(e.getMessage());
	        }
		
	}
	
		
}
