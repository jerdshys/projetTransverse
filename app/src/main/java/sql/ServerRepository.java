package sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import sql.Models.Server;

/**
 * Created by jerome on 25/10/2017.
 */
@Module
public class ServerRepository {

    private final ServerDbHelper mDbHelper;


    @Singleton
    public ServerRepository(@NonNull Context context) {
        mDbHelper = new ServerDbHelper(context);
        // @todo : dynamic add
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.Servers.COLUMN_NAME_TITLE, "serveur1");
        values.put(FeedReaderContract.Servers.COLUMN_NAME_DESCRIPTION, "192.168.0.1");
        long newRowId = db.insert(FeedReaderContract.Servers.TABLE_NAME, null, values);
        values.put(FeedReaderContract.Servers.COLUMN_NAME_TITLE, "serveurZ");
        values.put(FeedReaderContract.Servers.COLUMN_NAME_DESCRIPTION, "192.168.0.Z");
        long newRowId2 = db.insert(FeedReaderContract.Servers.TABLE_NAME, null, values);


    }

    public ArrayList<Server> getAll() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<Server> servers= new ArrayList<Server>();
        Cursor result = db.rawQuery("select rowid, * from "+FeedReaderContract.Servers.TABLE_NAME , null);
        while(result.moveToNext()){
            servers.add( new Server( result.getString(result.getColumnIndex(FeedReaderContract.Servers.COLUMN_NAME_TITLE)), result.getString(result.getColumnIndex(FeedReaderContract.Servers.COLUMN_NAME_DESCRIPTION))));
        }
        return servers;
    }



    public void put() {

    }

    public void post(String name, String description) {
        // @todo : dynamic add
        SQLiteDatabase db = this.mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

    }

    public void delete(int id) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define 'where' part of query.
        String selection = FeedReaderContract.Servers.COLUMN_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { "*" };
        db.delete(FeedReaderContract.Servers.TABLE_NAME, selection, selectionArgs);
        //db.delete(FeedReaderContract.Servers.TABLE_NAME, COLUMN_ROWID +  "=" + id, null) > 0;


    }

}
