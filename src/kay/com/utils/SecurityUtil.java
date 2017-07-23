package kay.com.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 主要用来处理密码
 * @author kay
 *
 */
public class SecurityUtil {

	//密码加密
    public static String encryptPassword(String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder=new BASE64Encoder();
        //md5加密之后可能会产生乱码，因为要存入数据库，所以外面还有加一层
        String result= base64Encoder.encode(md5.digest(pwd.getBytes("utf-8")));
        return result;
    }
	
    //密码验证
    /**
     * 
     * @param inputPwd 输入的密码
     * @param dbPwd    数据库查询出来的密码
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static boolean checkPassword(String inputPwd,String dbPwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String pwd=encryptPassword(inputPwd);
        if(!pwd.equals(dbPwd)){
            return false;
        }else {
            return true;
        }
    }
}
