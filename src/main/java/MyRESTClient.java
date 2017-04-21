/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author juena
 */


public class MyRESTClient {

    String getRequest(String urlZW, String requestMethod, String contentTyp) throws IOException, MalformedURLException
    {
        BufferedReader br = null;
        String output = "";
        String line;

        URL url = new URL(urlZW);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty("Accept", contentTyp);

        if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
        }

        br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        while ((line = br.readLine()) != null) {
            output += line + "\n";
	}
        
        if (br != null)
            br.close();
        conn.disconnect();               

        return output;
    }
}

