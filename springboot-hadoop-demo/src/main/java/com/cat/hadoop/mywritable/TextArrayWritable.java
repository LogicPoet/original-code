package com.cat.hadoop.mywritable;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.util.HashMap;

/**
 * @author LZ
 * @date 2020/8/24 17:04
 **/
public class TextArrayWritable extends ArrayWritable {
    public TextArrayWritable() {
        super(Text.class);
    }

    public TextArrayWritable(Class<? extends Writable> valueClass) {
        super(Text.class);
    }

    public TextArrayWritable(Class<? extends Writable> valueClass, Writable[] values) {
        super(Text.class, values);
    }

    public TextArrayWritable(String[] strings) {
        super(Text.class);
        Text[] texts = new Text[strings.length];
        for (int i = 0; i < strings.length; i++) {
            texts[i] = new Text(strings[i]);
        }
        set(texts);
    }

    /**
     *
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
