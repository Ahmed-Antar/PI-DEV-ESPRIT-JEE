package tn.esprit.twin1.brogrammers.eventify.Eventify.util;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

public class FaceCognitive {


	public static void CreateFaceList(String nameOfTheFaceList) {
		HttpClient httpclient = HttpClients.createDefault();

		try {
			nameOfTheFaceList = "faceuniqueuser" + nameOfTheFaceList;
			String url = "https://api.projectoxford.ai/face/v1.0/facelists/" + nameOfTheFaceList.toLowerCase();
			URIBuilder builder = new URIBuilder(url);

			URI uri = builder.build();
			HttpPut request = new HttpPut(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", "4989f9c4e5904d938429f00f2e9481c3");

			// Request body
			StringEntity reqEntity = new StringEntity(
					"{'name':'sample_list','userData':'User-provided data attached to the face list'}");
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
			}
		} catch (

		Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public static boolean AddFaceToList(String urlImage,String nameOfTheFaceList) {
		HttpClient httpclient = HttpClients.createDefault();

		try {
			nameOfTheFaceList = "faceuniqueuser" + nameOfTheFaceList;
			String url = "https://api.projectoxford.ai/face/v1.0/facelists/"+nameOfTheFaceList+"/persistedFaces";
			URIBuilder builder = new URIBuilder(url);

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", "4989f9c4e5904d938429f00f2e9481c3");

			// Request body
			StringEntity reqEntity = new StringEntity("{'url':'"+urlImage+"'}");
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
				return true;
			}
			return false;
		} catch (

		Exception e) {
			System.out.println("Adding Face To List Problem");
			return false;

		}
	
	}
	
	
	
	public static String DetectImageId(String urlImageToTest) {
		HttpClient httpclient = HttpClients.createDefault();

		try {
			String url = "https://api.projectoxford.ai/face/v1.0/detect?returnFaceId=true";
			URIBuilder builder = new URIBuilder(url);

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", "4989f9c4e5904d938429f00f2e9481c3");

			// Request body
			StringEntity reqEntity = new StringEntity("{'url':'"+urlImageToTest+"'}");//TOCHANGE
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
				//JSONObject obj = new JSONObject("aa");
				
				return EntityUtils.toString(entity);
			}
			return null;
		} catch (

		Exception e) {
			System.out.println(e.getMessage());
			return null;

		}
		
	}
	
	
	public static void Findsimilars(String faceId,String nameOfTheFaceList) {
		HttpClient httpclient = HttpClients.createDefault();

		try {
			nameOfTheFaceList = "faceuniqueuser" + nameOfTheFaceList;
			String url = "https://api.projectoxford.ai/face/v1.0/findsimilars";
			URIBuilder builder = new URIBuilder(url);

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", "4989f9c4e5904d938429f00f2e9481c3");

			// Request body
			StringEntity reqEntity = new StringEntity("{'faceId':'"+faceId+"','faceListId':'"+nameOfTheFaceList+"','maxNumOfCandidatesReturned':10,'mode':'matchPerson'}");//TOCHANGE
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
			}
		} catch (

		Exception e) {
			System.out.println(e.getMessage());

		}
	}
	
	

}
