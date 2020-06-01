package it.unifi.micc.artguide;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class DownloadWtc extends AsyncTask<String,Void,Boolean> {

    private MainActivity mainActivity;
    private String fileName = null;

    public DownloadWtc(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println(">>>>STARTING DOWNLOADING FILE");
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        fileName = strings[0];

        try{
            URL u = new URL(String.format("%s/%s", mainActivity.getString(R.string.web_server_url), fileName));
            HttpURLConnection c = (HttpURLConnection) u.openConnection();

            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            FileOutputStream f = new FileOutputStream(new File(mainActivity.getExternalFilesDir(null), fileName));
            InputStream in = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len1;
            while ( (len1 = in.read(buffer)) > 0 ) {
                f.write(buffer,0, len1);
            }
            f.close();
        } catch (ProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        System.out.println(">>>>FINISHED"+result);
//        String s = "Aggiornamento completato";
//        if(!result)
//            s = "Aggiornamento fallito";
//        Toast.makeText(this.mainActivity,s, Toast.LENGTH_SHORT).show();
        if (!result)
            mainActivity.addError("Could not download " + fileName);
    }
}
