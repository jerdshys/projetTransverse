package sql;

import android.provider.BaseColumns;

/**
 * Created by jerome on 30/10/2017.
 */
public class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class Servers implements BaseColumns {
        public static final int ROW_ID = 0;
        public static final String TABLE_NAME = "servers";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }
}


