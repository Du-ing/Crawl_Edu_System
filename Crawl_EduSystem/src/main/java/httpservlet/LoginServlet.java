package httpservlet;

import httpclient.LoginHttpClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理页面的请求信息，获取输入的用户信息
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
//        System.out.println("账号：" + userName);
//        System.out.println("密码：" + passWord);

        //用获取的账号和密码进行模拟登陆和爬虫
        LoginHttpClient httpClient = new LoginHttpClient();
        String webData = httpClient.loginSystem(userName,passWord);
        //处理爬取的信息
        //登录成功
        response.setContentType("text/html;charset=utf-8");//设置响应的编码
        PrintWriter pw = response.getWriter();//获取响应的字符输出流
        pw.write(webData);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
