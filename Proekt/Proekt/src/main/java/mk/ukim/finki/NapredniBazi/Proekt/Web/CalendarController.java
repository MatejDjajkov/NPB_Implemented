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
public class CalendarController {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CalendarController(JdbcTemplate jdbcTemplate)
    {

        this.jdbcTemplate=jdbcTemplate;
    }

    @GetMapping("/calendar")
    public String getCalendar(@RequestParam(value = "concert_location_id", required = false) Integer concertLocationId,
                              @RequestParam(value = "calendar_date", required = false) String calendarDate,
                              Model model)
    {
        if(concertLocationId==null || calendarDate==null)
        {
            return "calendar";
        }
        String CalendarSQL="select concertlocationid,calendardate,timeslot,concertid,concertname\n" +
                "from calendarview\n" +
                "where concertlocationid=? and calendardate=TO_DATE(?, 'YYYY-MM-DD');";

        List<Map<String,Object>> results=jdbcTemplate.queryForList(CalendarSQL,concertLocationId,calendarDate);
        model.addAttribute("calendarInfo",results);

        String LocationInfoSQL="select venuename,address,state,city\n" +
                "from concertlocation\n" +
                "where concertlocationid=?;";
        List<Map<String,Object>> other=jdbcTemplate.queryForList(LocationInfoSQL,concertLocationId);
        model.addAttribute("locationInfo",other);
        return "calendar";
    }







}
