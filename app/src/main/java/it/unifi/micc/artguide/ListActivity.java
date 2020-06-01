package it.unifi.micc.artguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListActivity listActivity;
    private RequestQueue queue;
    private  ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        this.listActivity=this;
        List<ArtObj> list = new LinkedList<ArtObj>();

        queue = Volley.newRequestQueue(this);
        listView = (ListView)findViewById(R.id.listView);

        loadData(new SpannableStringBuilder(""));

        ((EditText)(findViewById(R.id.editText))).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                loadData(editable);
            }
        });

        }

    private void loadData(Editable editable){
        System.out.println(editable);
        queue.cancelAll(""); //FIXME TAG??
        String url = String.format("%s/api.php?search=%s&token=e9e_security", getString(R.string.web_server_url), editable.toString());

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<ArtObj> list = stringToArtObjs(response);
                        if(list==null){
                            list = new LinkedList<>();
                       }
                        CustomAdapter adapter = new CustomAdapter(listActivity, R.layout.listitem, list);
                        listView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(stringRequest);

    }

    public LinkedList<ArtObj> stringToArtObjs(String string){
        try {
            if(string.compareTo("")==0)
                return null;

            LinkedList<ArtObj> artObjsList = new LinkedList<ArtObj>();
            String[] artObjsStr = string.split("#;@");

            for(int i=0;i<artObjsStr.length;i++){
                JSONObject jsonObject = new JSONObject(artObjsStr[i]);
                artObjsList.add(new ArtObj(jsonObject.getString("nome"),jsonObject.getString("autore"),jsonObject.getString("descrizione"),jsonObject.getString("storia"),jsonObject.getString("luogo"),jsonObject.getString("anno_creazione"),jsonObject.getString("url_video"),jsonObject.getString("url_audio"),jsonObject.getString("url_image")));
            }

            return artObjsList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
