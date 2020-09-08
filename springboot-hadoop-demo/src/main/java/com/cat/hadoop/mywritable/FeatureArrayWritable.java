package com.cat.hadoop.mywritable;

import com.cat.hadoop.bean.Feature;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

/**
 * @author LZ
 * @date 2020/8/24 18:56
 **/
public class FeatureArrayWritable extends ArrayWritable {
    public FeatureArrayWritable() {
        super(Feature.class);
    }

    public FeatureArrayWritable(Class<? extends Writable> valueClass) {
        super(valueClass);
    }

    public FeatureArrayWritable(Class<? extends Writable> valueClass, Writable[] values) {
        super(valueClass, values);
    }

    public FeatureArrayWritable(String[] strings) {
        super(Feature.class);
    }

}
