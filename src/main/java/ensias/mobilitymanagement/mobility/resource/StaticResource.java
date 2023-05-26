package ensias.mobilitymanagement.mobility.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticResource {
    @GetMapping("/HomePage.html")
    public String showHomePage() {
        return "HomePage";
    }
}
