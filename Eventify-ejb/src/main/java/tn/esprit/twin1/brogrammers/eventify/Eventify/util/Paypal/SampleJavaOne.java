package tn.esprit.twin1.brogrammers.eventify.Eventify.util.Paypal;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.openidconnect.Session;
import com.paypal.api.openidconnect.Tokeninfo;
import com.paypal.api.openidconnect.Userinfo;
import com.paypal.api.payments.CreditCard;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.base.rest.PayPalResource;

public class SampleJavaOne {
	public static void main(String[] args) {
        // Replace these values with your clientId and secret. You can use these to get started right now.
		String clientId = "AQGxWUBbI81ZE1dxq5FqnXONOn9Nm8DfrHfLp4FbET-DUINjdC6jFmQNqAZcEI3HGLf-WlJmycABHdOz";
		String clientSecret = "EGktNNh3GDQCG6yrPYBWRJIAo8A5V96v27s4ybQN2JzZS6NBiqEcj50BmgqEwwhnfqExuWPe8V5880rk";

        // Create a Credit Card
        CreditCard card = new CreditCard()
                .setType("visa")
                .setNumber("4032032951266946")
                .setExpireMonth(10)
                .setExpireYear(2021)
                .setCvv2(012)
                .setFirstName("Testing")
                .setLastName("Firas");

        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            card.create(context);
            System.out.println(card);
            PayPalResource p ;
           // System.out.println(PayPalResource.getClientID());
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        
        //****************************************************
        
        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
        List<String> scopes = new ArrayList<String>() {{
            /**
            * 'openid'
            * 'profile'
            * 'address'
            * 'email'
            * 'phone'
            * 'https://uri.paypal.com/services/paypalattributes'
            * 'https://uri.paypal.com/services/expresscheckout'
            * 'https://uri.paypal.com/services/invoicing'
            */
            add("openid");
            add("profile");
            add("email");
        }};
        String redirectUrl = Session.getRedirectURL("UserConsent", scopes, context);
        System.out.println(redirectUrl);
        
        
        //***************************************************************
        
        Tokeninfo info=null;;
		try {
			info = Tokeninfo.createFromAuthorizationCode(context, "code");
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String accessToken = info.getAccessToken();
        String refreshToken = info.getRefreshToken();
        //*******************************************************
        
        APIContext userAPIContext = new APIContext(clientId, clientSecret, "sandbox").setRefreshToken("EC-8RS159066G993145E");

        Userinfo userinfo=null;
		try {
			userinfo = Userinfo.getUserinfo(userAPIContext);
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(userinfo);
        
    }
}
