package android.azadbasis.com.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by azadidb on 2/15/2016.
 */
public class CustomAdapter extends ArrayAdapter {
    public CustomAdapter(Context context, String[] foods) {
        super(context, R.layout.custom_row,foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View customView=inflater.inflate(R.layout.custom_row, parent, false);

        String singleFoodItem= (String) getItem(position);
        TextView buckyText= (TextView) customView.findViewById(R.id.kathystextView);
        ImageView azImage= (ImageView) customView.findViewById(R.id.idbImage);

        buckyText.setText(singleFoodItem);
        azImage.setImageResource(R.mipmap.ls);
        return customView;
    }
}
