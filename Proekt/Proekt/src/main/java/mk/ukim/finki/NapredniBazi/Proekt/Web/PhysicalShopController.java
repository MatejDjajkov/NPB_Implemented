package mk.ukim.finki.NapredniBazi.Proekt.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Controller
public class PhysicalShopController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PhysicalShopController(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }



    @GetMapping("/physicalShop")
    public String getSpecificPage(@RequestParam(value = "id",required = false) Integer shopID, Model model)
    {


        if (shopID==null)
        {
            return "physicalShop";
        }

        String shopDetailsSql="select address,state,city,shopname,phonenumber " +
                "from physicalshop " +
                "where physicalshopid=?;";

        List<Map<String, Object>> shopDetails = jdbcTemplate.queryForList(shopDetailsSql,shopID);
        model.addAttribute("shopDetails",shopDetails);

        String shopTicketsSql="select ticketid,concertid,concertname,concertdate " +
                "from availableticketslocation " +
                "where physicalshopid=? " +
                "and status='available';";
        List<Map<String, Object>> shopTickets=jdbcTemplate.queryForList(shopTicketsSql,shopID);
        model.addAttribute("shopTickets",shopTickets);

        return "physicalShop";
    }
}
