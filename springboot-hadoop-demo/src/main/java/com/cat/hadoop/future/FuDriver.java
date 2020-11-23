package com.cat.hadoop.future;

import com.cat.hadoop.bean.FuBean;
import com.cat.hadoop.mywritable.FeatureArrayWritable;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author LZ
 * @date 2020/8/24 13:48
 **/
@Slf4j
public class FuDriver {

    private static String rootPath = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code" +
            "\\springboot-hadoop-demo\\src\\main\\resources\\";

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1、获取一个job实例
        Job job = Job.getInstance(new Configuration());

        // 2、设置我们的类路径（Classpath）
        job.setJarByClass(FuDriver.class);

        // 3、设置Mapper和Reducer
        job.setMapperClass(FuMapper.class);
        job.setReducerClass(FuReducer.class);

        // 4、设置Mapper和Reducer输出的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FuBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 5、设置输入和输出的数据路径
        FileInputFormat.setInputPaths(job, new Path(rootPath + "input\\forecast-data.txt"));

        Path outPath = new Path(rootPath + "output");
        FileSystem fileSystem = FileSystem.get(new Configuration());
        if (fileSystem.exists(outPath)) {
            fileSystem.delete(outPath, true);
        }

        FileOutputFormat.setOutputPath(job, outPath);

        // 6、提交我们的job
        long startTime = System.currentTimeMillis();
        boolean b = job.waitForCompletion(true);
        long endTime = System.currentTimeMillis();
        log.info("运行时间time= {} s", (endTime - startTime) / 1000);
        System.exit(b ? 0 : 1);
    }
}
