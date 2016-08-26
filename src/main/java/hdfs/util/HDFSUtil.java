package hdfs.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import util.Config;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class HDFSUtil
{
    private static boolean HDFS_ENABLED = Config.HADOOP_ENABLE == 1;

    private static String HDFS_URI = Config.HADOOP_HDFS_URI;

    private static String HADOOP_USER_NAME = Config.HADOOP_USER_NAME;

    private static Configuration conf;

    static
    {
        if (HDFS_ENABLED)
        {
            System.setProperty("HADOOP_USER_NAME", HADOOP_USER_NAME);

            conf = new Configuration();
            conf.set("fs.defaultFS" , HDFS_URI);
        }
    }

    /**
     * 判断路径是否存在
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static boolean exits(String path) throws IOException
    {
        FileSystem fs = FileSystem.get(conf);
        return fs.exists(new Path(path));
    }

    /**
     * 创建文件
     *
     * @param filePath
     * @param contents
     * @throws IOException
     */
    public static void createFile(String filePath, byte[] contents) throws IOException
    {
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(filePath);
        FSDataOutputStream outputStream = fs.create(path);
        outputStream.write(contents);
        outputStream.close();
        fs.close();
    }

    /**
     * 创建文件
     *
     * @param filePath
     * @param fileContent
     * @throws IOException
     */
    public static void createFile( String filePath, String fileContent) throws IOException
    {
        createFile(filePath, fileContent.getBytes());
    }

    /**
     * @param delSrc
     * @param overwrite
     * @param localFilePath
     * @param remoteFilePath
     * @throws IOException
     */
    public static void copyFromLocalFile(boolean delSrc, boolean overwrite,
                                         String localFilePath, String remoteFilePath) throws IOException
    {
        FileSystem fs = FileSystem.get(conf);
        Path localPath = new Path(localFilePath);
        Path remotePath = new Path(remoteFilePath);
        fs.copyFromLocalFile(delSrc, overwrite, localPath, remotePath);
        fs.close();
    }

    /**
     * 删除目录或文件
     *
     * @param remoteFilePath
     * @param recursive
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(String remoteFilePath, boolean recursive) throws IOException
    {
        FileSystem fs = FileSystem.get(conf);
        boolean result = fs.delete(new Path(remoteFilePath), recursive);
        fs.close();
        return result;
    }

    /**
     * 删除目录或文件(如果有子目录,则级联删除)
     *
     * @param remoteFilePath
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(String remoteFilePath) throws IOException
    {
        return deleteFile(remoteFilePath, true);
    }


    /**
     * 文件重命名
     *
     * @param oldFileName
     * @param newFileName
     * @return
     * @throws IOException
     */
    public static boolean renameFile(String oldFileName, String newFileName) throws IOException
    {
        FileSystem fs = FileSystem.get(conf);
        Path oldPath = new Path(oldFileName);
        Path newPath = new Path(newFileName);
        boolean result = fs.rename(oldPath, newPath);
        fs.close();
        return result;
    }

    /**
     * 创建目录
     *
     * @param dirName
     * @return
     * @throws IOException
     */
    public static boolean createDirectory(String dirName) throws IOException
    {
        FileSystem fs = FileSystem.get(conf);
        Path dir = new Path(dirName);
        boolean result = fs.mkdirs(dir);
        fs.close();
        return result;
    }

    /**
     * 列出指定路径下的所有文件(不包含目录)
     *
     * @param basePath
     * @param recursive
     */
    public static RemoteIterator<LocatedFileStatus> listFiles(String basePath, boolean recursive) throws IOException
    {
        FileSystem fs = FileSystem.get(conf);
        RemoteIterator<LocatedFileStatus> fileStatusRemoteIterator = fs.listFiles(new Path(basePath), recursive);

        return fileStatusRemoteIterator;
    }

    /**
     * 列出指定路径下的文件（非递归）
     *
     * @param basePath
     * @return
     * @throws IOException
     */
    public static RemoteIterator<LocatedFileStatus> listFiles( String basePath) throws IOException
    {
        FileSystem fs = FileSystem.get(conf);
        RemoteIterator<LocatedFileStatus> remoteIterator = fs.listFiles(new Path(basePath), false);
        fs.close();
        return remoteIterator;
    }

    /**
     * 列出指定目录下的文件\子目录信息（非递归）
     *
     * @param dirPath
     * @return
     * @throws IOException
     */
    public static FileStatus[] listStatus(String dirPath) throws IOException
    {
        FileSystem fs = FileSystem.get(conf);
        FileStatus[] fileStatuses = fs.listStatus(new Path(dirPath));
        fs.close();
        return fileStatuses;
    }


    /**
     * 读取文件内容
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String readFile(String filePath) throws IOException
    {
        FileSystem fs = FileSystem.get(conf);
        String fileContent = null;
        Path path = new Path(filePath);
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try
        {
            inputStream = fs.open(path);
            outputStream = new ByteArrayOutputStream(inputStream.available());
            IOUtils.copyBytes(inputStream, outputStream, conf);
            fileContent = outputStream.toString();
        } finally
        {
            IOUtils.closeStream(inputStream);
            IOUtils.closeStream(outputStream);
            fs.close();
        }
        return fileContent;
    }
}
