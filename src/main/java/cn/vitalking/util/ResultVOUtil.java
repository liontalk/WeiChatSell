package cn.vitalking.util;

import cn.vitalking.VO.ResultVO;

/**
 * @author ZhouZhe
 * @version 1.0
 * @description 返回数据对象
 * @date 2018-08-20 23:06
 **/
public class ResultVOUtil {


    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("成功");
        resultVO.setCode(1);
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(int code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return success(null);
    }

}
