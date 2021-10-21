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


public class UserDeleteServlet extends HttpServlet {


    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);

    }


    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        String sql = "delete from tb_user where id=?";

        int i = JdbcKit.executeUpdate(sql, id);

        renderJson(resp, i > 0 ? JSON.toJSONString(true) : JSON.toJSONString(false));

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