package hdfs.service;

import sys.entity.ETreeNode;

import java.io.IOException;
import java.util.List;

public interface HDFSService
{
    ETreeNode listDirectoryStructs() throws IOException;
}
