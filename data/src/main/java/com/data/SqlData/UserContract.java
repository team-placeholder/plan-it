package com.data.SqlData;

import android.provider.BaseColumns;

/**
 * Created by Antoan on 2/18/2017.
 */

public class UserContract {
    public static final class EmailEntry implements BaseColumns {
        public static final String TABLE_NAME = "emails";
        public static final String COLUMN_EMAIL = "email";
    }

    public static final class ResUserEntry implements BaseColumns{
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_EMAIL = "email";
        public  static final String COLUMN_USERNAME = "user";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_AUTHKEY = "authKey";
    }
}
