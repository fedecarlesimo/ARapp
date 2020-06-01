package it.unifi.micc.artguide;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Api {

    static ArtObj artObj;
    String myCheck;

    private Activity mainActivity;
    private ProgressDialog progressDialog;

    public Api(Activity mainActivity,String checkVal){
        this.mainActivity = mainActivity;
        this.myCheck=checkVal;
        this.progressDialog = new ProgressDialog(mainActivity);
        this.progressDialog.setMessage("Sto caricando..");
        this.progressDialog.setCancelable(false);
        this.progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
    }

    public void stopProgressDialog(){
        if(this.progressDialog!=null) {
            this.progressDialog.dismiss();
            this.progressDialog.cancel();
        }
    }

    public void request(String app_id){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.mainActivity);
        String url = String.format("%s/api.php?id_app=%s&token=e9e_security", mainActivity.getString(R.string.web_server_url), app_id);

// Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        artObj= stringToArtOb(response);
                        //qui parte la nuova activity

                        mainActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        });

                        Intent intent = new Intent(mainActivity,ResultActivity.class);
                        intent.putExtra("checkval",myCheck);
                        mainActivity.startActivity(intent);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

// Add the request to the RequestQueue.
        this.mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.show();
            }
        });

        queue.add(stringRequest);
    }

    public ArtObj stringToArtOb(String string){
        try {
            JSONObject jsonObject = new JSONObject(string);
            return new ArtObj(jsonObject.getString("nome"),jsonObject.getString("autore"),jsonObject.getString("descrizione"),jsonObject.getString("storia"),jsonObject.getString("luogo"),jsonObject.getString("anno_creazione"),jsonObject.getString("url_video"),jsonObject.getString("url_audio"),jsonObject.getString("url_image"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
