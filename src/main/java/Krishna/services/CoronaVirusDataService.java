package Krishna.services;

import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import Krishna.models.LocationStats;
@Service
public class CoronaVirusDataService {
	

	
	private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<LocationStats> allStats=new ArrayList<>();
	
	public List<LocationStats> getAllStats() {
		return allStats;
	}

	public void setAllStats(List<LocationStats> allStats) {
		this.allStats = allStats;
	}

	@PostConstruct
	@Scheduled(cron ="* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException
	{   
		 List<LocationStats> newStats=new ArrayList<>();

			/*
			 * HttpClient client = HttpClient.newHttpClient(); HttpRequest request =
			 * HttpRequest.newBuilder() .uri(URI.create(VIRUS_DATA_URL)) .build();
			 * HttpResponse<String>
			 * httpresponse=client.send(request,HttpResponse.BodyHandlers.ofString());
			 */
		 RestTemplate restTemplate=new RestTemplate();
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	   		 
		 
		StringReader csvBodyReader=new StringReader(restTemplate.exchange(VIRUS_DATA_URL, HttpMethod.GET, entity, String.class).getBody());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		for (CSVRecord record : records) {
			LocationStats locationStat = new LocationStats();
			locationStat.setState(record.get("Province/State"));
			locationStat.setCountry(record.get("Country/Region"));
			locationStat.setCountry(record.get("Country/Region"));
			int latestcases=Integer.parseInt(record.get(record.size() -1));
			int previousdaycases=Integer.parseInt(record.get(record.size() -2));
			locationStat.setLatesttotalcases(latestcases);
			int b=latestcases-previousdaycases;
			locationStat.setDifffromprevday(b);
		    newStats.add(locationStat);
		   
		}
		 this.allStats=newStats;
	}

}
