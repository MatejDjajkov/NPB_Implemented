package mk.ukim.finki.NapredniBazi.Proekt.Web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddMusicGroupController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AddMusicGroupController(JdbcTemplate jdbcTemplate)
    {

        this.jdbcTemplate=jdbcTemplate;
    }

    @GetMapping("/addmusicgroup")
    public String MusicGroup()
    {
        return "addMusicGroupNew";
    }

    @PostMapping("/addnewgroup")
    public String AddMusicGroup(
            @RequestParam("groupName") String groupName,
            @RequestParam("musicianIds") String musicianIds
    )
    {
        String [] strings=musicianIds.split("\\s");
        String stringNumbers="";
        for(String number : strings)
        {
            stringNumbers+=number;
            stringNumbers+=",";
        }
        jdbcTemplate.execute(String.format("CALL add_music_group('%s',ARRAY [%s]);",
                groupName.replace("'", "''"),
                stringNumbers.substring(0,stringNumbers.length()-1)));




        return "redirect:/admin";
    }
}
