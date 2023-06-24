package mk.ukim.finki.NapredniBazi.Proekt.Web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RegistrationController (JdbcTemplate jdbcTemplate)
    {

        this.jdbcTemplate=jdbcTemplate;
    }

    @GetMapping("/register")
    public String getRegistration()
    {
        return "registrationPage";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName,
            @RequestParam("birthdate") String birthdate,
            @RequestParam("password") String password,
            @RequestParam("phone_number") String phoneNumber
    )
    {
        jdbcTemplate.execute(String.format("CALL InsertRegistration('%s','%s','%s','%s','%s','%s',NULL);",
                username,email,firstName,lastName,birthdate,password));
        return "redirect:/home";
    }
}
