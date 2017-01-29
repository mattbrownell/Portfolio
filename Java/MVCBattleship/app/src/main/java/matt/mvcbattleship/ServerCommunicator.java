package matt.mvcbattleship;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Matt on 11/12/2016.
 */
public class ServerCommunicator {

    public static ServerCommunicator getInstance() {
        return null;
    }

    public ServerCommunicator() {

    }

    public String getData(String request, String requestMethod, JSONObject jsonObject) throws IOException {
        HttpURLConnection co = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            URL url = new URL("http://battleship.pixio.com" + request);
            co = (HttpURLConnection) url.openConnection();
            co.setRequestMethod(requestMethod);
            co.setDoInput(true);
            if (!requestMethod.equals("GET")) {
                co.setDoOutput(true);
                co.setRequestProperty("Content-Type", "application/json" );
                co.setRequestProperty("Accept", "application/json");
            }
            co.connect();

            if (!requestMethod.equals("GET")) {
                try (OutputStreamWriter wr = new OutputStreamWriter(co.getOutputStream(), "UTF-8")) {
                    String sent = jsonObject.toString();
                    wr.write(sent);
                }
            }

            int responseCode = co.getResponseCode();
            if (responseCode >= 400 && responseCode <= 499) {
                InputStream error = co.getErrorStream();
            }
            else {
                is = co.getInputStream();
            }

            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
            co.disconnect();

            return sb.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (is != null) {
                is.close();
            }
            else if (os != null) {
                os.close();
            }
        }
        return null;
    }
}
