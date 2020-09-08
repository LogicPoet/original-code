package com.cat.hadoop.bean;

import com.cat.hadoop.mywritable.FeatureArrayWritable;
import lombok.Data;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 数据处理输出
 *
 * @author LZ
 * @date 2020/8/24 17:36
 **/
@Data
public class FuBean implements Writable {

    /**
     * 结果标签
     */
    private String resultLabel;

    /**
     * 样本数据
     * {结果标签:[因素标签1,因素标签2,因素标签3]}
     */
    private Instance instance;

    /**
     * 特征函数的list
     * <p>
     * 数据分块[结果标签，因素标签 x N]
     */
    private FeatureArrayWritable featureList;

    /**
     * 一个数据集合里有多少因素标签
     */
    private int labelCount;

    public FuBean() {
        this.resultLabel = null;
        this.instance = new Instance();
        this.featureList  = new FeatureArrayWritable();
        this.labelCount = 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        //private String resultLabel;
        //private Instance instance;
        //private List<Feature> featureList;
        //private List<Integer> featureCountList;
        //private int labelCount;
        out.writeUTF(resultLabel);
        instance.write(out);
        featureList.write(out);
        out.writeInt(labelCount);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        resultLabel = in.readUTF();
        instance.readFields(in);
        featureList.readFields(in);
        labelCount = in.readInt();
    }
}
