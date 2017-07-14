package stackoverflow.retrofit.demo.com.hocfindviewbyid;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lvTinhthanh;
    ArrayAdapter<String> adapterTinhthanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        lvTinhthanh = (ListView) findViewById(R.id.lvTinhthanh);
        adapterTinhthanh = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1
        );
        adapterTinhthanh.addAll(getResources().getStringArray(R.array.arrTinhthanh));
        lvTinhthanh.setAdapter(adapterTinhthanh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem menuSearch = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuSearch);
        //SearchView searchView = (SearchView) menuSearch.getActionView();

        MenuItemCompat.setOnActionExpandListener(menuSearch, new MenuItemCompat.OnActionExpandListener(){
           @Override
            public boolean onMenuItemActionExpand(MenuItem item){
               Toast.makeText(MainActivity.this, "Dang expand", Toast.LENGTH_SHORT).show();
               return false;
           }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(MainActivity.this, "Dang Collapse", Toast.LENGTH_SHORT).show();
                return false;
            }

        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapterTinhthanh.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
