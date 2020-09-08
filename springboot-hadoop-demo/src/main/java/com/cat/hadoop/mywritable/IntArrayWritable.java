package com.cat.hadoop.mywritable;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

/**
 * @author LZ
 * @date 2020/8/24 20:11
 **/
public class IntArrayWritable extends ArrayWritable {
    public IntArrayWritable() {
        super(IntWritable.class);
    }

    public IntArrayWritable(Class<? extends Writable> valueClass) {
        super(valueClass);
    }

    public IntArrayWritable(Class<? extends Writable> valueClass, Writable[] values) {
        super(valueClass, values);
    }

    public IntArrayWritable(String[] strings) {
        super(IntWritable.class);
        IntWritable[] ints = new IntWritable[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = new IntWritable(Integer.parseInt(strings[i]));
        }
        set(ints);
    }
}
