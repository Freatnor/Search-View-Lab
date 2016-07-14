package ly.generalassemb.drewmahrt.shoppinglistwithsearch;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistwithsearch.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    private CursorAdapter mCursorAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();


        mListView = (ListView)findViewById(R.id.list_view);

        processSearchIntent(getIntent());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this, this.getClass());
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        searchView.setQueryRefinementEnabled(true);

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {

        processSearchIntent(intent);
    }

    private void processSearchIntent(Intent intent){

        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor = ShoppingSQLiteOpenHelper.getInstance(this).searchShoppingList(query);

            mCursorAdapter = new CursorAdapter(this, cursor, android.support.v4.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
                @Override
                public View newView(Context context, Cursor cursor, ViewGroup parent) {
                    return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
                }

                @Override
                public void bindView(View view, Context context, Cursor cursor) {
                    TextView resultView = (TextView) view.findViewById(android.R.id.text1);
                    TextView typeView = (TextView) view.findViewById(android.R.id.text2);
                    resultView.setText(cursor.getString(cursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ITEM_NAME)));
                    typeView.setText(cursor.getString(cursor.getColumnIndex(ShoppingSQLiteOpenHelper.COL_ITEM_TYPE)));
                }
            };
            mListView.setAdapter(mCursorAdapter);
        }
    }
}
