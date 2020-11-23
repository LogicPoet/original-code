package com.cat.hadoop.future;

import com.cat.hadoop.bean.FuBean;
import com.cat.hadoop.bean.Instance;
import com.cat.hadoop.mywritable.FeatureArrayWritable;
import com.cat.hadoop.mywritable.TextArrayWritable;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer<输入类型, 输入类型,输出类型,输出类型>
 * 输入类型：就是mapper输出的类型
 * 输出类型：框架并不知道，需要在job中设置类型
 *
 * @author LZ
 * @date 2020/8/24 13:48
 **/
@Slf4j
public class FuReducer extends Reducer<Text, FuBean, Text, Text> {

    private FeatureArrayWritable featureArrayWritable = new FeatureArrayWritable();
    private Text result = new Text();
    private StringBuilder sb = new StringBuilder();

    /**
     * 处理mapper输出的数据
     *
     * @param key     对mapper输出分组之后的键
     * @param values  对mapper输出分组之后的集合
     * @param context 上下文
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<FuBean> values, Context context) throws IOException, InterruptedException {
        values.forEach(f->{
            featureArrayWritable = f.getFeatureList();
        });
        String[] strings = featureArrayWritable.toStrings();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            if (i<strings.length-1){
                sb.append(",");
            }
        }
        result.set(sb.toString());
        context.write(key,result);
    }

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        super.cleanup(context);
    }
}
