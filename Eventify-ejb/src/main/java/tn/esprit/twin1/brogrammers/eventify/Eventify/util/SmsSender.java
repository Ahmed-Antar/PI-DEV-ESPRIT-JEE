package tn.esprit.twin1.brogrammers.eventify.Eventify.util;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class SmsSender {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC148a7d9c0de70c787999c2410540fc0d";
    public static final String AUTH_TOKEN = "d2ed89755c588f4c32bbdc82aaaad732";

	public static String SendSMS(String numero,String body) {
    	TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        
        // Build the parameters
    	//DonT TEST Itttt Its limitedddd
        List params = new ArrayList();
        params.add(new BasicNameValuePair("To", numero));
        params.add(new BasicNameValuePair("From", "(617) 606-4593"));
        params.add(new BasicNameValuePair("Body", body));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message;
		try {
			message = messageFactory.create(params);
	        System.out.println(message.getSid());
	        return message.getSid();

		} catch (TwilioRestException e) {
			System.err.println("errooooooot" + e);
	        return null;
		}
        
        
    }

}
