package mk.ukim.finki.NapredniBazi.Proekt.Web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class AnalyticsController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AnalyticsController(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }


    @GetMapping("/ticketsperconcert")
    public String TicketsPerConcert(Model model)
    {
        String sql="select concertid,concertname,concertdate,total_tickets,total_expired_tickets,total_sold_tickets,total_available_tickets " +
                "from ConcertTicketStatus;";

        List<Map<String,Object>> results=jdbcTemplate.queryForList(sql);
        model.addAttribute("ticketInfo",results);
        return "ticketsPerConcert";
    }

    @GetMapping("/shoprevenue")
    public String ShopRevenue(Model model)
    {
        String sql="select physicalshopid,shopname,concertid,concertname,revenue " +
                "from analytics.physicalshophistory;";

        List<Map<String,Object>> results=jdbcTemplate.queryForList(sql);
        model.addAttribute("revenuesShop",results);

        return "shopRevenue";
    }

    @GetMapping("/onlinerevenue")
    public String OnlineRevenue(Model model)
    {
        String sql="select year,month,concertid,concertname,revenue " +
                "from analytics.onlineorderhistory;";

        List<Map<String,Object>> results=jdbcTemplate.queryForList(sql);
        model.addAttribute("onlineRevenue",results);

        return "onlineRevenue";
    }

    @GetMapping("/historicresells")
    public String HistoricResells(Model model)
    {
        String sql="select year,month,concertid,concertname,resellnumbers\n" +
                "from analytics.historyreselltable;";

        List<Map<String,Object>> results=jdbcTemplate.queryForList(sql);
        model.addAttribute("historicResells",results);

        return "historicResells";
    }

    @GetMapping("/activity")
    public String ActivityAnalytics(@RequestParam("from") String from,
                                    @RequestParam("to") String to,Model model)
    {

        String sql="select date,registeredUsers,resales,onlinePayments\n" +
                "from activity\n" +
                "where Date>=TO_DATE(?, 'YYYY-MM-DD')\n" +
                "and Date <=TO_DATE(?, 'YYYY-MM-DD');";
        List<Map<String,Object>> results=jdbcTemplate.queryForList(sql,from,to);
        model.addAttribute("activities",results);
        return "activity";
    }

    @GetMapping("/paymentanalytics")
    public String PaymentAnalytics(@RequestParam("from") String from,
                                   @RequestParam("to") String to,Model model)
    {
        String sql="select paymentdate,username,total\n" +
                "from PaymentsPerDate\n" +
                "where paymentdate>=TO_DATE(?, 'YYYY-MM-DD')\n" +
                "and paymentdate<=TO_DATE(?, 'YYYY-MM-DD');";

        List<Map<String,Object>> results=jdbcTemplate.queryForList(sql,from,to);
        model.addAttribute("analytics",results);


        return "paymentanalytics";
    }
}
