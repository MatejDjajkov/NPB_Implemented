package mk.ukim.finki.NapredniBazi.Proekt.Web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class ResellController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ResellController (JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }

    @GetMapping("/resales")
    public String resaleListings(Model model)
    {
        String sql="select listingid,concertid,concertname,concertdate,username,resellprice,exparationdate " +
                "from reselllistings" +
                " where status='available';";
        List<Map<String,Object>> results=jdbcTemplate.queryForList(sql);
        model.addAttribute("listings",results);

        return "resales";
    }

    @PostMapping("/resellPayment")
    public String ResellPayment(@RequestParam ("listingID") Integer listingID)
    {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("select BuyResellTicket(45680,%d);",listingID));

        jdbcTemplate.execute(sb.toString());


        return "redirect:/tickets";
    }

    @PostMapping("/addresellticket")
    public String AddResellTicket(
            @RequestParam ("price") BigDecimal price,
            @RequestParam("IDconsumer") Integer consumerID,
            @RequestParam("IDticket") Integer ticketID
            )
    {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("select addingTicketForResell(%s,%d,%d);",price,consumerID,ticketID));

        jdbcTemplate.execute(sb.toString());

        return "redirect:/resales";
    }

}
