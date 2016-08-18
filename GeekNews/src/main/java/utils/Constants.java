package utils;

/**
 * 常量类
 * Created by zdr on 16-8-8.
 */
public class Constants {
    public static final String
            API_KEY = "6b68bca2d6662442fb1103b2427b4fed";

    public static final String SP_FILE = "settings";

    public static final class NetNewsDB {
        public static final String TAB_NAME = "news";
        public static final String COL_ID = "ID";
        public static final String COL_TITLE = "TITLE";
        public static final String COL_URL = "URL";
        public static final String COL_URL_PIC = "PICURL";
        public static final String COL_CTIME = "CTIME";
        public static final String COL_DESCRIPTION = "DESCRIPTION";
        public static final String COL_TYPE = "TYPE";

        public static String getCreateDBSQL(){

            return "create table if not exists "+ TAB_NAME +" ( " +
                    COL_ID+" integer primary key autoincrement, " +
                    COL_TITLE+" varchar(200), " +
                    COL_CTIME+" varchar(50), " +
                    COL_URL_PIC+" varchar(200), " +
                    COL_DESCRIPTION+" text, " +
                    COL_URL+" varchar(200)," +
                    COL_TYPE+" varchar(50) ) ";
        }
    }
    public static final class NewsTypeDB{

        public static final String TAB_NAME = "type";
        public static final String COL_ID = "id";
        public static final String COL_TYPE = "type";
        public static final String COL_URL = "url";
        public static final String COL_FRAGMETTYPE = "fragment";
        public static final String COL_CHECKED = "checked";

        public static String getCreateDBSQL(){
            return "create table if not exists "+ TAB_NAME +" ( " +
                    COL_ID+" integer primary key autoincrement, " +
                    COL_TYPE+" varchar(200), " +
                    COL_URL+" varchar(200)," +
                    COL_CHECKED+" integer," +
                    COL_FRAGMETTYPE+" integer ) ";

        }

    }
}
