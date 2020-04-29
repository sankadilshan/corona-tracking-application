package com.corona.tracker.service;

import com.corona.tracker.model.LocationState;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaDataService {

    @Value("${covid.data.url}")
    private String url;

    private List<LocationState> allState= new ArrayList<>();

    public List<LocationState> getAllState() {
        return allState;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1  * * *")
    public void CovidVirusDataService() throws IOException, InterruptedException {
        List<LocationState> newState= new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader reader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> csvRecords = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord csvRecord : csvRecords) {
            LocationState locationState = new LocationState();
            locationState.setState(csvRecord.get("Province/State"));
            locationState.setCountry(csvRecord.get("Country/Region"));
            locationState.setCurrentTotalCases(Integer.parseInt(csvRecord.get(csvRecord.size()-1)));
            locationState.setYesterdayTotalCases(Integer.parseInt(csvRecord.get(csvRecord.size()-2)));
            locationState.setDifference(locationState.getCurrentTotalCases()-locationState.getYesterdayTotalCases());
            System.out.println(locationState);
            newState.add(locationState);
        }
        this.allState=newState;

    }
}
