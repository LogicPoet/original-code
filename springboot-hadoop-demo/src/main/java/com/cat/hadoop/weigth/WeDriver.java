package com.cat.hadoop.weigth;

import com.cat.hadoop.bean.FuBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author LZ
 * @date 2020/8/24 13:48
 **/
@Slf4j
@Component
public class WeDriver {

    private static String rootPath = "C:\\Users\\Liuzhi\\Desktop\\github_project\\original-code" +
            "\\springboot-hadoop-demo\\src\\main\\resources\\";

    public void jobStart() throws IOException, ClassNotFoundException, InterruptedException {
        // 1、获取一个job实例
        Configuration conf = new Configuration();
        conf.set("rootPath",rootPath);
        Job job = Job.getInstance(conf);

        // 2、设置我们的类路径（Classpath）
        job.setJarByClass(WeDriver.class);

        // 3、设置Mapper和Reducer
        job.setMapperClass(WeMapper.class);
        job.setReducerClass(WeReducer.class);

        // 4、设置Mapper和Reducer输出的类型
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        // 5、设置输入和输出的数据路径
        FileInputFormat.setInputPaths(job, new Path(rootPath + "input\\maxInt.txt"));

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
