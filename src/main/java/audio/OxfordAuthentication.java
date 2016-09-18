package audio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

/*
     * This class demonstrates how to get a valid O-auth token from
     * Azure Data Market.
     */
public class OxfordAuthentication
{
    public static final String AccessTokenUri = "https://oxford-speech.cloudapp.net/token/issueToken";

    private String clientId;
    private String clientSecret;
    private String request;
    private OxfordAccessToken token;
    private Timer accessTokenRenewer;

    //Access token expires every 10 minutes. Renew it every 9 minutes only.
    private final int RefreshTokenDuration = 9 * 60 * 1000;
    private final String charsetName = "utf-8";
    private TimerTask nineMinitesTask = null;

    public OxfordAuthentication(String clientId, String clientSecret)
    {
        this.clientId = clientId;
        this.clientSecret = clientSecret;

            /*
             * If clientid or client secret has special characters, encode before sending request
             */
        try{
        this.request = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s&scope=%s",
                URLEncoder.encode(clientId,charsetName),
                URLEncoder.encode(clientSecret, charsetName),
                URLEncoder.encode("https://speech.platform.bing.com",charsetName));

        }catch (Exception e){
            e.printStackTrace();
        }
        this.token = HttpPost(AccessTokenUri, this.request);

        // renew the token every specified minutes
        accessTokenRenewer = new Timer();
        nineMinitesTask = new TimerTask(){
            public void run(){
                RenewAccessToken();
            }
        };

        accessTokenRenewer.schedule(nineMinitesTask, 0, RefreshTokenDuration);
    }

    public OxfordAccessToken GetAccessToken()
    {
        return this.token;
    }

    private void RenewAccessToken()
    {
        OxfordAccessToken newAccessToken = HttpPost(AccessTokenUri, this.request);
        //swap the new token with old one
        //Note: the swap is thread unsafe
        System.out.println("new access token: " + newAccessToken.access_token);
        this.token = newAccessToken;
    }

    private OxfordAccessToken HttpPost(String AccessTokenUri, String requestDetails)
    {
        InputStream inSt = null;
        HttpsURLConnection webRequest = null;

        //Prepare OAuth request
        try{
            webRequest = HttpsConnection.getHttpsConnection(AccessTokenUri);
            webRequest.setDoInput(true);
            webRequest.setDoOutput(true);
            webRequest.setConnectTimeout(5000);
            webRequest.setReadTimeout(5000);
            webRequest.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            webRequest.setRequestMethod("POST");

            byte[] bytes = requestDetails.getBytes();
            webRequest.setRequestProperty("content-length", String.valueOf(bytes.length));
            webRequest.connect();

            DataOutputStream dop = new DataOutputStream(webRequest.getOutputStream());
            dop.write(bytes);
            dop.flush();
            dop.close();

            inSt = webRequest.getInputStream();
            InputStreamReader in = new InputStreamReader(inSt);
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuffer strBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
            }

            bufferedReader.close();
            in.close();
            inSt.close();
            webRequest.disconnect();

            // parse the access token from the json format
            String result = strBuffer.toString();
            
            OxfordAccessToken token = new OxfordAccessToken();
            token.access_token = getJsonValue(result, "access_token");
            token.token_type = getJsonValue(result, "token_type");
            token.expires_in = getJsonValue(result, "expires_in");
            token.scope = getJsonValue(result, "scope");

            return token;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    private String getJsonValue(String text, String key) {
        int i = text.indexOf(key);
        String value = "";
        if (i > -1) {
            value = text.substring(text.indexOf(':', i) + 1).trim();
            value = (value.length() > 0 && value.charAt(0) == '"') ?
                value.substring(1, value.indexOf('"', 1)) :
                value.substring(0, Math.max(0, Math.min(Math.min(value.indexOf(","), value.indexOf("]")), value.indexOf("}"))));
        }
        return value;
    }
}
