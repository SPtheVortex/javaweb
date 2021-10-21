package com.sp.demo.serlvet;

import com.alibaba.fastjson.JSON;
import Utils.JdbcKit;
import com.jfinal.render.RenderException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserSaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);

    }


    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");

        String password = req.getParameter("password");

        String realname = req.getParameter("realname");

        String id = req.getParameter("id");

        if ("".equals(id) || null == id) {

            String sql = "insert into tb_user(id,username,password,realname) " +

                    "values(?,?,?,?)";

            int i = JdbcKit.executeUpdate(sql, UUID.randomUUID().toString(), username, password, realname);

            renderJson(resp, i > 0 ? JSON.toJSONString(true) : JSON.toJSONString(false));

        } else {

            String sql = "update tb_user set username=?,password=?,realname=? where id=?";

            int i = JdbcKit.executeUpdate(sql, username, password, realname, id);

            renderJson(resp, i > 0 ? JSON.toJSONString(true) : JSON.toJSONString(false));

        }

    }


    public void renderJson(HttpServletResponse resp, String jsonText) {

        PrintWriter writer = null;


        try {

            resp.setContentType("application/json; charset=UTF-8");

            writer = resp.getWriter();

            writer.write(jsonText);

            writer.flush();

        } catch (IOException var3) {

            throw new RenderException(var3);

        }

    }


}