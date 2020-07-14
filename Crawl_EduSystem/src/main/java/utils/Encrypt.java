package utils;

import org.apache.commons.codec.digest.DigestUtils;

//账号和密码的加密算法
public class Encrypt {
    /**
     *
     * @param str 需要加密的字符串
     * @param alg 加密的算法名称
     * @return 加密后的得到的字符串
     */
    public static String digestString(String str, String alg){
        String newStr = null;
        if ("MD5".equals(alg)) {
            newStr = DigestUtils.md5Hex(str);
        } else if ("SHA_1".equals(alg)) {
            newStr = DigestUtils.sha1Hex(str);
        }
        return newStr;
    }
}
