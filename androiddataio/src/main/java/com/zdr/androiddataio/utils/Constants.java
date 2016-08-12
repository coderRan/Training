package com.zdr.androiddataio.utils;

/**
 * 常量类
 * Created by zdr on 16-8-12.
 */
public class Constants {
    public static final class NetNewsDB {
        public static final String TBL_NAME = "news";
        public static final String COL_ID = "ID";
        public static final String COL_TITLE = "TITLE";
        public static final String COL_URL = "URL";
        public static final String COL_URL_PIC = "PICURL";
        public static final String COL_CTIME = "CTIME";
        public static final String COL_DESCRIPTION = "DESCRIPTION";

        public static String getCreateDBSQL(){
            String sql = "create table if not exists "+TBL_NAME+" ( " +
                    COL_ID+" integer primary key autoincrement, " +
                    COL_TITLE+" varchar(200), " +
                    COL_CTIME+" varchar(50), " +
                    COL_URL_PIC+" varchar(200), " +
                    COL_DESCRIPTION+" text, " +
                    COL_URL+" varchar(200) ) ";

            return sql;
        }

    }
}
