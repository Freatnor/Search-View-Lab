package ly.generalassemb.drewmahrt.shoppinglistwithsearch;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ShoppingSQLiteOpenHelper extends SQLiteOpenHelper{
    private static final String TAG = ShoppingSQLiteOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String SHOPPING_LIST_TABLE_NAME = "SHOPPING_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_ITEM_NAME = "ITEM_NAME";
    public static final String COL_ITEM_PRICE = "PRICE";
    public static final String COL_ITEM_DESCRIPTION = "DESCRIPTION";
    public static final String COL_ITEM_TYPE = "TYPE";


    public static final String[] SHOPPING_COLUMNS = {COL_ID,COL_ITEM_NAME,COL_ITEM_DESCRIPTION,COL_ITEM_PRICE,COL_ITEM_TYPE};

    private static final String CREATE_SHOPPING_LIST_TABLE =
            "CREATE TABLE " + SHOPPING_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ITEM_NAME + " TEXT, " +
                    COL_ITEM_DESCRIPTION + " TEXT, " +
                    COL_ITEM_PRICE + " REAL, " +
                    COL_ITEM_TYPE + " TEXT )";

    private static ShoppingSQLiteOpenHelper instance;

    private ShoppingSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static ShoppingSQLiteOpenHelper getInstance(Context context){
        if(instance == null){
            instance = new ShoppingSQLiteOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SHOPPING_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SHOPPING_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public Cursor searchShoppingList(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        String arg = "%"+query+"%";
        String[] args = {arg, arg};

        String[] projection = {COL_ID, COL_ITEM_NAME, COL_ITEM_TYPE};

        Cursor cursor = db.query(SHOPPING_LIST_TABLE_NAME, // a. table
                projection, // b. column names
                COL_ITEM_NAME+" LIKE ? OR " + COL_ITEM_TYPE + " LIKE ?", // c. selections
                args, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }

    public Cursor searchShoppingListByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] args = {"%"+name+"%"};

        String[] projection = {COL_ID, COL_ITEM_NAME, COL_ITEM_TYPE};

        Cursor cursor = db.query(SHOPPING_LIST_TABLE_NAME, // a. table
                projection, // b. column names
                COL_ITEM_NAME+" LIKE ?", // c. selections
                args, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }

    public Cursor searchShoppingListByType(String type){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] args = {"%"+type+"%"};

        String[] projection = {COL_ID, COL_ITEM_NAME, COL_ITEM_TYPE};

        Cursor cursor = db.query(SHOPPING_LIST_TABLE_NAME, // a. table
                projection, // b. column names
                COL_ITEM_TYPE + " LIKE ?", // c. selections
                args, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }


}
