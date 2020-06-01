package it.unifi.micc.artguide;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<it.unifi.micc.artguide.ArtObj> {

    private Context listActivity;

    public CustomAdapter(Context context, int textViewResourceId, List<it.unifi.micc.artguide.ArtObj> objects) {

        super(context, textViewResourceId, objects);
        this.listActivity = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listitem, null);
        TextView text = (TextView)convertView.findViewById(R.id.textView);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        final it.unifi.micc.artguide.ArtObj artObj = getItem(position);

        ((RelativeLayout) convertView.findViewById(R.id.itemLayout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                it.unifi.micc.artguide.Api.artObj = artObj;
                Intent intent = new Intent(listActivity, ResultActivity.class);
                listActivity.startActivity(intent);

            }
        });

        text.setText(artObj.getNome());
        Picasso.get().load(artObj.getUrl_image()).into(imageView);
        return convertView;
    }

}