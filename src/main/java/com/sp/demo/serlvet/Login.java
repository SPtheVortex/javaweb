package com.sp.demo.serlvet;

import Utils.JdbcKit;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        System.out.println("调⽤doPost⽅法");
        login(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {
        String username = req.getParameter("username"); //从浏览器上得到⽤户名
        String password = req.getParameter("password"); //从浏览器上得到密码
        List<Map<String, Object>> maps = JdbcKit.executeQuery("select * from tb_user where username=? and password=?", username, password);
        setCookie(resp, "author", "xbb", 60);
        if (maps.size() == 0) {
            //客户端跳转
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", maps.get(0));
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }

    public void setCookie(HttpServletResponse response, String key, String value, int
            maxAgeInSeconds) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAgeInSeconds);
        response.addCookie(cookie);
    }
}
