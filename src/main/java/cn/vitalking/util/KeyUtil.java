package cn.vitalking.util;

import java.util.Random;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 生成唯一主键的工具类
 * @date 2018-08-21 23:13
 **/
public class KeyUtil {


    /**
     *生成策略
     * 毫秒+随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        int number = random.nextInt(1000000)+9000000;
        return System.currentTimeMillis()+number+"";
    }
}
