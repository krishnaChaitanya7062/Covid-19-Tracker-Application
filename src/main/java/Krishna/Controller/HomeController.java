package Krishna.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Krishna.models.LocationStats;
import Krishna.services.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronavirusdataservice; 
  @GetMapping("/")
  public String home(Model model) {
	  List<LocationStats> allStats= coronavirusdataservice.getAllStats();
	int totalReportedCases= allStats.stream().mapToInt(stat -> stat.getLatesttotalcases()).sum();
	int totalNewCases= allStats.stream().mapToInt(stat -> stat.getDifffromprevday()).sum();

	  model.addAttribute("LocationStats",allStats);
	  model.addAttribute("totalReportedCases",totalReportedCases);
	  model.addAttribute("totalNewCases",totalNewCases);


	  return "home";
	  
  }
}
