package com.sp.demo.serlvet;

import com.alibaba.fastjson.JSON;
import Utils.JdbcKit;
import com.jfinal.render.RenderException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        List<Map<String, Object>> maps = JdbcKit.executeQuery("select * from tb_user");
        Map<String, Object> result = new HashMap<>();
        result.put("total", result.size());
        result.put("rows", maps);
        renderJson(resp, JSON.toJSONString(result));
    }
    public void renderJson(HttpServletResponse resp, String jsonText){
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
