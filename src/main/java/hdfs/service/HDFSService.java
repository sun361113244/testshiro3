package hdfs.service;

import sys.entity.ETreeNode;

import java.io.IOException;
import java.util.List;

public interface HDFSService
{
    int isHadoopClusterEnabled(String url);

    ETreeNode listDirectoryStructs(String path) throws IOException;
}
