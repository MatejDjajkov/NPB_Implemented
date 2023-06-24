package mk.ukim.finki.NapredniBazi.Proekt.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class PaymentController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PaymentController (JdbcTemplate jdbcTemplate)
    {

        this.jdbcTemplate=jdbcTemplate;
    }

    @GetMapping("/payments")
    public String resaleListings(Model model)
    {
        String sql="Select paymentdate,paymentamount,paymentmethod,paymentstatus " +
                "from paymentuserdetails " +
                "where consumerid=? " +
                "order by paymentdate DESC ;";
        List<Map<String,Object>> results=jdbcTemplate.queryForList(sql,ConsumerID.currentUserID);
        model.addAttribute("payments",results);

        return "payments";
    }
}
