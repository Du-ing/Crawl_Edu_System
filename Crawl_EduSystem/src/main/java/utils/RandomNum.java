package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 生成随机参数对
 */
public class RandomNum {
    //存储随机码
    private static List<RndandCode> ranList = new ArrayList<RndandCode>();
    //添加随机码
    static{
        ranList.add(new RndandCode("39833","6145899192"));
        ranList.add(new RndandCode("43544","5729642922"));
        ranList.add(new RndandCode("49718","9835300799"));
        ranList.add(new RndandCode("2528","9662902678"));
        ranList.add(new RndandCode("14007","6657771320"));
        ranList.add(new RndandCode("64575","2633980660"));
        ranList.add(new RndandCode("51884","1835326347"));
        ranList.add(new RndandCode("42914","1359608899"));
        ranList.add(new RndandCode("20795","8645771812"));
        ranList.add(new RndandCode("20632","1588304956"));
    }
    //返回随机字符串
    public static String RandomStr(int index, String param){
        if(param.equals("rnd")){
            return ranList.get(index).rnd;
        }else {
            return ranList.get(index).code;
        }
    }

    //生成随机一个整数
    public static int RandomInt(){
        Random random = new Random();
        return random.nextInt(10);
    }
}

//随机参数
class RndandCode{
    public String rnd;
    public String code;

    public RndandCode(String rnd, String code) {
        this.rnd = rnd;
        this.code = code;
    }

    public String getRnd() {
        return rnd;
    }

    public String getCode() {
        return code;
    }
}
