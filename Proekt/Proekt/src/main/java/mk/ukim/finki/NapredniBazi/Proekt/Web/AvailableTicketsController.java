package mk.ukim.finki.NapredniBazi.Proekt.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class AvailableTicketsController {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AvailableTicketsController(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }

    @GetMapping("/availableTickets")
    public String getAvailableTickets(Model model)
    {
        return "availableTickets";
    }

    @GetMapping("/availableticketconcert")
    public String getAvailableTickets(
            @RequestParam("concertID") Integer concertID,
            Model model)
    {

        String sql="select ticketid,concertid,concertname,concertdate,price\n" +
                "from concertswithavailabletickets\n" +
                "where concertid=?\n" +
                "and physicalshopid IS NULL\n" +
                "and status='available'\n" +
                "LIMIT 100;";

        List<Map<String,Object>> results=jdbcTemplate.queryForList(sql,concertID.intValue());
        model.addAttribute("availableTickets",results);
        return "availableTickets";
    }

    @PostMapping("/buyavailableticket")
    public String buyAvailableTicket(
            @RequestParam("IDConcert") Integer concertID,
            @RequestParam("numberOfTickets") Integer numberOfTickets,
            @RequestParam("consumerPaymentMethod") String consumerPaymentMethod)
    {
        jdbcTemplate.execute(String.format("CALL BuyConcertTicket(%d,%d,%d,'%s');"
                ,concertID,numberOfTickets,45680,consumerPaymentMethod));
        return "redirect:/tickets";
    }

}
