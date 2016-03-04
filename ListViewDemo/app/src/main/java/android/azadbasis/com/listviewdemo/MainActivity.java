package android.azadbasis.com.listviewdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] foods = {"rice", "potato", "meat", "fish", "vegetables", "carrot"};
        //ListAdapter buckysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foods);

        ListAdapter buckysAdapter = new CustomAdapter(this, foods);
        ListView foodList = (ListView) findViewById(R.id.buckysListView);
        foodList.setAdapter(buckysAdapter);

        foodList.setOnItemClickListener(
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this,"I like "+food,Toast.LENGTH_LONG).show();
                    }
                }

        );

    }
}