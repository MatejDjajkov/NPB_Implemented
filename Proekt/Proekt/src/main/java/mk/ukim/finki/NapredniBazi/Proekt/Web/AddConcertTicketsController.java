package mk.ukim.finki.NapredniBazi.Proekt.Web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class AddConcertTicketsController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AddConcertTicketsController(JdbcTemplate jdbcTemplate)
    {

        this.jdbcTemplate=jdbcTemplate;
    }

    @GetMapping("/addtickets")
    public String AddTicketsHomePage()
    {
        return "addTicket";
    }

    @GetMapping("physicaltickets")
    public String addPhysicalTicketsHomePage()
    {
        return "addphysicaltickets";
    }

    @PostMapping("/insertPhysicalTicket")
    public String addPhysicalLocationTickets(
            @RequestParam("concertIDArgument") Integer concertID,
            @RequestParam("physicalLocationID") Integer physicalLocationID,
            @RequestParam("concertOrganisationCompany") Integer concertOrganisationCompany,
            @RequestParam("ticketPrice") BigDecimal ticketPrice,
            @RequestParam("numberOfTickets") Integer numberOfTickets
    )
    {
        jdbcTemplate.execute(String.format("select insert_physical_ticket_function(%d,%d,%d,%s,%d);",
                concertID,physicalLocationID,concertOrganisationCompany,ticketPrice,numberOfTickets));

        return "redirect:/admin";
    }


    @PostMapping("/addconcerttickets")
    public String addTicketsToConcer(
            @RequestParam("concertIDArgument") int concertIDArgument,
            @RequestParam("concertOrganisationCompany") int concertOrganisationCompany,
            @RequestParam("ticketPrice") BigDecimal ticketPrice,
            @RequestParam("numberOfTickets") int numberOfTickets) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SELECT insert_ticket_function(%d, %d, %s, %d);",
                concertIDArgument, concertOrganisationCompany, ticketPrice, numberOfTickets));
        String sqlStatement = sb.toString();

        jdbcTemplate.execute(sqlStatement);

        return "redirect:/admin";


    }



}
