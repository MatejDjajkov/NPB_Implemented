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
public class ConcertDetailsController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ConcertDetailsController(JdbcTemplate jdbcTemplate)
    {

        this.jdbcTemplate=jdbcTemplate;
    }

    @GetMapping("/details")
    public String ConcertDetails (@RequestParam(value = "id") Integer concertID, Model model)
    {
        String VenueDetailsSQL="select venuename,state,city,address " +
                "from concert " +
                "join concertlocation c on concert.concertlocationid = c.concertlocationid " +
                "where concertid=?;";
        List<Map<String,Object>> details=jdbcTemplate.queryForList(VenueDetailsSQL,concertID);
        model.addAttribute("venueDetails",details);

        String perFormerDetailsSQL="select groupname,stagename " +
                "from concertperformers " +
                "where concertid=?;";
        List<Map<String,Object>> performers=jdbcTemplate.queryForList(perFormerDetailsSQL,concertID);

        model.addAttribute("performers",performers);

        return "concertDetails";
    }

}
