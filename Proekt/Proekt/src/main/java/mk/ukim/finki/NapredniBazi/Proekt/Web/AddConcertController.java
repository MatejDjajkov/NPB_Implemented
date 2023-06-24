package mk.ukim.finki.NapredniBazi.Proekt.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AddConcertController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AddConcertController(JdbcTemplate jdbcTemplate)
    {

        this.jdbcTemplate=jdbcTemplate;
    }


    @GetMapping("/addconcert")
    public String addTicketsHome()
    {
        return "addConcert";
    }

    @PostMapping("/addconcert")
    public String addConcertHome(
            @RequestParam("concert_name") String concertName,
            @RequestParam("concert_location_id") int concertLocationId,
            @RequestParam("concert_date") String concertDate,
            @RequestParam("band_ids") String bandIds,
            @RequestParam(value = "solo_musician_id", required = false) Integer soloMusicianId) {

        String [] strings=bandIds.split("\\s");
        String stringNumbers="";
        for(String number : strings)
        {
            stringNumbers+=number;
            stringNumbers+=",";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SELECT add_concert ('%s',%d,'%s',ARRAY[%s],%d);"
                ,concertName.replace("'", "''"),concertLocationId
                ,concertDate,stringNumbers.substring(0,stringNumbers.length()-1)
                ,soloMusicianId));
        String SqlStatement=sb.toString();


        jdbcTemplate.execute(SqlStatement);

        return "redirect:/admin";
    }




}
