package mk.ukim.finki.NapredniBazi.Proekt.Web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MyTicketController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MyTicketController(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }

    @GetMapping("/tickets")
    public String myTickets(Model model)
    {
        String sql="select ticketid,datefrom,concertid,concertname,concertdate from ticketsthatusersown where consumerid=? and validuntil >CURRENT_DATE and dateto IS NULL;";
        List<Map<String,Object>> results=jdbcTemplate.queryForList(sql,ConsumerID.currentUserID);
        model.addAttribute("tickets",results);

        return "myTickets";
    }
}
