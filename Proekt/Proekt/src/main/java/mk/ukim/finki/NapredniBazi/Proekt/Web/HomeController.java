package mk.ukim.finki.NapredniBazi.Proekt.Web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    //private final JdbcTemplate jdbcTemplate;

    //@Autowired
  // public HomeController(JdbcTemplate jdbcTemplate)
   //{
  //     this.jdbcTemplate=jdbcTemplate;
  // }

    @GetMapping(value = {"/", "/home"})
    public String defaultMapping() {

        return "home";
    }


}
