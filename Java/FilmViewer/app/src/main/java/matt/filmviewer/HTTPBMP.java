package matt.filmviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Matt on 12/2/2016.
 */
public class HTTPBMP extends AsyncTask<String, Void, Bitmap> {

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            URL url =  new URL("https://image.tmdb.org/t/p/w150" + params[0]);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options);
            return bmp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
