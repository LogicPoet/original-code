package com.cat.hadoop.bean;

import com.cat.hadoop.mywritable.TextArrayWritable;
import lombok.Data;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author chosen
 * <p>
 * 一个观测实例，包含事件和时间发生的环境
 * @date 2020/7/10 4:29 下午
 */
@Data
public class Instance implements Writable {

    /**
     * 结果标签
     */
    private String label;
    // private Text name = new Text();
    /**
     * 因素标签集合，如[Sunny, Happy]
     */
    private TextArrayWritable fieldList;

    public Instance() {
        this.fieldList = new TextArrayWritable();
    }

    public Instance(String label, TextArrayWritable fieldList) {
        this.label = label;
        this.fieldList = fieldList;
        // this.name = new Text(name);
        // this.age = new IntWritable(age);
        // this.sex = new Text(sex);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(label);
        fieldList.write(out);
    }


    @Override
    public void readFields(DataInput in) throws IOException {
        //如果使用Java数据类型，比如String name;
        //this.name = in.readUTF();只能使用这种类型。
        label = in.readUTF();
        fieldList.readFields(in);
    }

}
