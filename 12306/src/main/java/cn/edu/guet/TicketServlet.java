package cn.edu.guet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TicketServlet", value = "/TicketServlet")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("余票查询");

        request.setCharacterEncoding("UTF-8");

        String startStation=request.getParameter("startStation");
        System.out.println(startStation);
        String endStation=request.getParameter("endStation");
        System.out.println(endStation);
        String departureDate=request.getParameter("departureDate");
        System.out.println(departureDate);

        String allTickets=TicketSearch.search(startStation,endStation,departureDate);

        System.out.println(allTickets);
        // 设置响应类型
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out= response.getWriter();
        out.print(allTickets);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
