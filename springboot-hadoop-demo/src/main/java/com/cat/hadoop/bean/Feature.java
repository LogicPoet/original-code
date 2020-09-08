package com.cat.hadoop.bean;

import lombok.Data;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author chosen
 * @date 2020/7/10 4:30 下午
 * <p>
 * 特征(二值函数)
 */
@Data
public class Feature implements Writable {
    /**
     * 结果标签
     */
    private String label;
    /**
     * 因素标签
     */
    private String value;
    /**
     * 出现次数
     */
    private int count;

    public Feature() {

    }

    /**
     * 特征函数
     *
     * @param result 结果
     * @param value  环境
     */
    public Feature(String result, String value, int count) {
        this.label = result;
        this.value = value;
        this.count = count;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(label);
        out.writeUTF(value);
        out.writeInt(count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        label = in.readUTF();
        value = in.readUTF();
        count = in.readInt();
    }

    @Override
    public boolean equals(Object obj) {
        Feature feature = (Feature) obj;
        if (this.label.equals(feature.label)
                && this.value.equals(feature.value)
                && this.count == feature.count) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + label + ", " + value + "]";
    }

}
