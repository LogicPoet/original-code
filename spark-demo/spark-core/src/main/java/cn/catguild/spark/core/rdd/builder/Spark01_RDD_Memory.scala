package cn.catguild.spark.core.rdd.builder

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark01_RDD_Memory {

  def main(args: Array[String]): Unit = {
    // TODO 准备环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Spark01_RDD_Memory")
    val sc = new SparkContext(sparkConf)
    // TODO 创建RDD
    // 从内存中创建RDD，将内存中集合的数据作为处理的数据源
    val ints = Seq[Int](1, 2, 3, 4)

    // parallelize : 并行
    val value: RDD[Int] = sc.parallelize(ints)
    // makeRDD方法在底层实现时其实就是调用了rdd对象的parallelize方法。
    val value1: RDD[Int] = sc.makeRDD(ints)
    // 启动计算并打印
    value.collect().foreach(println)
    value1.collect().foreach(println)

    // TODO 关闭环境
    sc.stop()
  }

}
