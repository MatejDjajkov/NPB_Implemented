package mk.ukim.finki.NapredniBazi.Proekt.Web;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminController(JdbcTemplate jdbcTemplate)
    {

        this.jdbcTemplate=jdbcTemplate;
    }

    @GetMapping("/admin")
    public String AdminHome()
    {

        return "adminHome";
    }




}
