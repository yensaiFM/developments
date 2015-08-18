package com.seas.groupon_mvc_php.tools;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<ImageView, Void, Bitmap>
{
    ImageView imageView = null;

    @Override
    protected Bitmap doInBackground(ImageView... imageViews)
    {
        this.imageView = imageViews[0];
        return download_Image((String)imageView.getTag());
    }

    private Bitmap download_Image(String url)
    {
        Bitmap bmp=null;
        try
        {
            URL ulrn = new URL(url);
            HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
            InputStream instr = con.getInputStream();
            bmp = BitmapFactory.decodeStream(instr);
            instr.close();
        }
        catch (Exception ex){}
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap result)
    {
        imageView.setImageBitmap(result);
    }
}
