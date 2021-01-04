package cn.catguild.cfd;

/**
 * {
 *   "labelOne": "第 1 页\n第 2 页\n第 3 页\n第 4 页\n第 5 页\n第 6 页\n第 7 页\n第 8 页",
 *    "labelTwo":  {
 *     "1_1":  "第 1 页",
 *      "2_1":  "第 2 页",
 *      "3_1":  "第 3 页",
 *      "4_1":  "第 4 页",
 *      "5_1":  "第 5 页",
 *      "6_1":  "第 6 页",
 *      "7_1":  "第 7 页",
 *      "8_1":  "第 8 页"
 *   },
 *    "type":  "电气专业",
 *    "seg_1":  {
 *     "content":  "\r\n        \r\n    image_1\n",
 *      "images":  {
 *       "image_1":  "http://jianbiaoku.com/system/filehandle.aspx?398BE1EC6EBF889A-851455c9174ca2d5"
 *     },
 *      "labels":  []
 *   },
 *    "seg_2":  {
 *     "content":  "\r\n        \r\n    image_1\n",
 *      "images":  {
 *       "image_1":  "http://jianbiaoku.com/system/filehandle.aspx?398BE1EC6EBF889A-c3ae77e187ec57ae"
 *     },
 *      "labels":  []
 *   },
 *    "seg_3":  {
 *     "content":  "\r\n        \r\n    image_1\n",
 *      "images":  {
 *       "image_1":  "http://jianbiaoku.com/system/filehandle.aspx?398BE1EC6EBF889A-20341e76836b23d5"
 *     },
 *      "labels":  []
 *   },
 *    "seg_4":  {
 *     "content":  "\r\n        \r\n    image_1\n",
 *      "images":  {
 *       "image_1":  "http://jianbiaoku.com/system/filehandle.aspx?398BE1EC6EBF889A-afdd297f20ac3963"
 *     },
 *      "labels":  []
 *   },
 *    "seg_5":  {
 *     "content":  "\r\n        \r\n    image_1\n",
 *      "images":  {
 *       "image_1":  "http://jianbiaoku.com/system/filehandle.aspx?398BE1EC6EBF889A-a9ffd74d41f61315"
 *     },
 *      "labels":  []
 *   },
 *    "seg_6":  {
 *     "content":  "\r\n        \r\n    image_1\n",
 *      "images":  {
 *       "image_1":  "http://jianbiaoku.com/system/filehandle.aspx?398BE1EC6EBF889A-aea434b7daea1e0d"
 *     },
 *      "labels":  []
 *   },
 *    "seg_7":  {
 *     "content":  "\r\n        \r\n    image_1\n",
 *      "images":  {
 *       "image_1":  "http://jianbiaoku.com/system/filehandle.aspx?398BE1EC6EBF889A-ec9223e37f0460b5"
 *     },
 *      "labels":  []
 *   },
 *    "seg_8":  {
 *     "content":  "\r\n        \r\n    image_1\n",
 *      "images":  {
 *       "image_1":  "http://jianbiaoku.com/system/filehandle.aspx?398BE1EC6EBF889A-10c1a3a303814290"
 *     },
 *      "labels":  []
 *   },
 *    "title":  "1000kV交流系统电压和无功电力技术导则",
 *    "gbtitle":  "GB/Z 24847-2009"
 * }
 *
 * @author liu.zhi
 * @date 2020/12/28 15:39
 */
public class ReadMapDoc {

    //public void readDirectory(){
    //    List<DirectorSort> dirList = new ArrayList<>();
    //    Map<String, String> labelTwo = JSON.parseObject(map.get("labelTwo").toString(), Map.class);
    //    labelTwo.forEach(
    //            (k, v) -> {
    //                // 提取中文
    //                String s = RegularCheckUtil.extractChinese(v);
    //                // 如果只是页码则不保存
    //                if (!Pattern.compile("^第.*页$").matcher(s).matches()) {
    //                    // 有附录前缀则只保留后面的主体
    //                    if (s.contains("附录")) {
    //                        String[] split = s.split(" ");
    //                        if (split.length > 1) {
    //                            label.add(split[1].trim());
    //                            dRecordList.add(split[1].trim());
    //                        }
    //                    }
    //                    // 直接保存
    //                    label.add(s.trim());
    //                }
    //
    //                dRecordList.add(s.trim());
    //                String[] split = k.split("_");
    //                serialToNameMap.put(split[0], s.trim());
    //
    //                // 保留目录
    //                DirectorSort directorSort = new DirectorSort();
    //                directorSort.setSerial(Integer.parseInt(split[0]));
    //                directorSort.setHierarchy(Integer.parseInt(split[1]));
    //                directorSort.setName(v);
    //                dirList.add(directorSort);
    //            }
    //    );
    //}

}
