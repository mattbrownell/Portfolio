package matt.filmviewer;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Matt on 12/2/2016.
 */
public class MovieData extends AsyncTask<Integer, Void, JSONObject> {

    protected JSONObject doInBackground(Integer... params) {
        int id = (Integer) params[0];
        HttpURLConnection co = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/" + id + "?api_key=874fa90b77caae35dfd36fa9a57a1779&language=en-US");
            co = (HttpURLConnection) url.openConnection();
            co.setRequestMethod("GET");
            co.connect();

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
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            co.disconnect();

            JSONObject jsonObject = new JSONObject(sb.toString());

            return jsonObject;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
