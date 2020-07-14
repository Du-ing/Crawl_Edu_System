package httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import utils.Encrypt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LoginHttpClient {
    //登录并爬取教务系统
    public String loginSystem(String userName,String passWord){
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post请求
        HttpPost httpPost = new HttpPost("http://xxxxxxxxxxxxxxxxxxxxx");
        //需要填入的表单数据
        String userName1 = Encrypt.digestString(userName,"MD5");//加密算法得到参数值
        String password1 = Encrypt.digestString(userName + passWord,"SHA_1");//加密算法得到参数值
        List<NameValuePair> nvpr = new ArrayList<NameValuePair>();
        nvpr.add(new BasicNameValuePair("MsgID",""));
        nvpr.add(new BasicNameValuePair("KeyID",""));
        nvpr.add(new BasicNameValuePair("UserName",""));
        nvpr.add(new BasicNameValuePair("Password",""));
        nvpr.add(new BasicNameValuePair("rnd","62432"));
        nvpr.add(new BasicNameValuePair("return_EncData",""));
        nvpr.add(new BasicNameValuePair("code","4186858246"));
        nvpr.add(new BasicNameValuePair("userName1",userName1));
        nvpr.add(new BasicNameValuePair("password1",password1));
        nvpr.add(new BasicNameValuePair("webfinger","39e2d636e49a05bf3f34d74b60a9e06b"));
        nvpr.add(new BasicNameValuePair("type","xs"));
        nvpr.add(new BasicNameValuePair("userName",userName));
        nvpr.add(new BasicNameValuePair("password",passWord));

        try {
            //设置请求体
            httpPost.setEntity(new UrlEncodedFormEntity(nvpr,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //响应
        CloseableHttpResponse response = null;
        //响应的数据
        String data = null;
        try {
            //发送请求，获取响应
            response = httpClient.execute(httpPost);
            //判断状态码
            if(response.getStatusLine().getStatusCode() == 200){
                //登录成功，获取响应信息
                HttpEntity entity = response.getEntity();
                //如果包含该字段也说明登录没有成功
                data = EntityUtils.toString(entity,"utf-8");
                if(data.indexOf("选择身份") != -1){
                    data = sendFailedData();
                }
            }else{
                //登录失败
                data = sendFailedData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭响应
                response.close();
                //关闭HttpClient
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    //返回登录失败的页面
    private String sendFailedData(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>登录失败</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>登录失败！</h1>\n" +
                "    <h2>请检查账号和密码是否正确</h2>\n" +
                "    <a href=\"login.html\">点击此处以重新登录</a>\n" +
                "</body>\n" +
                "</html>";
    }
}
