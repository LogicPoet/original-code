package com.cat.hadoop.hdfs;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URI;

/**
 * @author LZ
 * @date 2020/8/24 12:10
 **/
@Slf4j
public class HdftTest{

    private String rootPath = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code" +
            "\\springboot-hadoop-demo\\src\\test\\resources\\";

    private FileSystem fileSystem;

    /**
     * 之前执行
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        fileSystem = FileSystem.get(URI.create("hdfs://192.168.192.140:9000"),new Configuration(),"root");
    }

    /**
     * 之后执行
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        fileSystem.close();
    }

    @Test
    public void testFile(){
        String filePath = rootPath + "test_start.txt";
        File file = new File(filePath);
        if (file.exists()){
            log.info("文件存在");
        }else {
            log.info("文件不存在");
        }
    }

    @Test
    public void testConn() throws Exception{
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://192.168.192.140:9000"),new Configuration());
        fileSystem.close();
    }

    @Test
    public void testStart() throws Exception{
        String filePath = rootPath + "test_start.txt";
        // 获取一个HDFS的抽象封装对象
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://192.168.192.140:9000"),new Configuration());

        // 用这个对象操作文件系统
        fileSystem.copyFromLocalFile(new Path(filePath),(new Path("/")));

        // 关闭文件系统
        fileSystem.close();
    }

    /**
     * 上传文件到hdfs文件系统中
     *
     * @throws Exception
     */
    @Test
    public void put() throws Exception{
        String filePath = rootPath + "test_start.txt";
        fileSystem.copyFromLocalFile(new Path(filePath),(new Path("/")));
    }

    /**
     * 下载文件到本地
     *
     * @throws Exception
     */
    @Test
    public void get() throws Exception{
        fileSystem.copyToLocalFile(new Path("/test_start.txt"),(new Path(rootPath)));
    }
}
