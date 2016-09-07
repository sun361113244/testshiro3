package util;

import org.apache.hadoop.conf.Configuration;

public class HadoopCommonUtil
{
    private static Configuration conf;

    /**
     * 获得Configuration对象
     * @return
     */
    public static Configuration getConf()
    {
        if(conf != null)
        {
            return conf;
        }
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        conf = new Configuration();

//        conf.set("mapreduce.job.jar" , "/Users/charles/Desktop/datarecommend/out/artifacts/datarecommend_jar/datarecommend.jar");

        return  conf;
    }
}
