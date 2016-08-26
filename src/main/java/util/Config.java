package util;

import java.util.ResourceBundle;

public class Config {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

    public static final String REDIS_HOST = bundle.getString("REDIS_HOST");

    public static final int REDIS_PORT = Integer.parseInt(bundle.getString("REDIS_PORT"));

    public static final String REDIS_AUTH = bundle.getString("REDIS_AUTH");

    // --------------------------------------------------------------------------------------------

    public static final int HADOOP_ENABLE = Integer.parseInt(bundle.getString("hadoop.enable"));

    public static final String HADOOP_USER_NAME = bundle.getString("hadoop.user.name");

    public static final String HADOOP_HDFS_URI = bundle.getString("hadoop.hdfs.uri");

    // --------------------------------------------------------------------------------------------

    public static final String TOOLS_AREA_BIGDATA_URL = bundle.getString("tools_area_bigdata_url");

}
