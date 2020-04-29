package com.corona.tracker.controller;

import com.corona.tracker.model.LocationState;
import com.corona.tracker.model.Search;
import com.corona.tracker.model.SearchValueDto;
import com.corona.tracker.service.CoronaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class CoronaDataController {

    @Autowired
    private CoronaDataService coronaDataService;

    @GetMapping("/home")
    public String home(Model model){
        List<LocationState> allState = coronaDataService.getAllState();
        int sumToday = allState.stream().mapToInt(state -> state.getCurrentTotalCases()).sum();
        int sumYesterday= allState.stream().mapToInt(diff -> diff.getYesterdayTotalCases()).sum();
        int diff= sumToday-sumYesterday;
        List<String> allCountryies = getAllCountryies(allState);
        model.addAttribute("locationStates",allState);
        model.addAttribute("totalReportedCases",sumToday);
        model.addAttribute("totalCasesYesterday",sumYesterday);
        model.addAttribute("difference",diff);
        model.addAttribute("localeDate", LocalDate.now());
        model.addAttribute("yesterday", LocalDate.now().minusDays(1));
        model.addAttribute("allCountry",allCountryies);
        model.addAttribute("search",new Search());
        return "home";
    }

    @PostMapping(value = "/home")
    public String save(@ModelAttribute Search search, Model model){
        List<LocationState> allState = coronaDataService.getAllState();
        System.out.println("post value "+ search.getValue());
        int sumToday = allState.stream().mapToInt(state -> state.getCurrentTotalCases()).sum();
        int sumYesterday= allState.stream().mapToInt(diff -> diff.getYesterdayTotalCases()).sum();
        int diff= sumToday-sumYesterday;
        List<String> allCountryies = getAllCountryies(allState);
        model.addAttribute("locationStates",allState);
        model.addAttribute("totalReportedCases",sumToday);
        model.addAttribute("totalCasesYesterday",sumYesterday);
        model.addAttribute("allCountry",allCountryies);
        model.addAttribute("difference",diff);
        model.addAttribute("localeDate", LocalDate.now());
        model.addAttribute("yesterday", LocalDate.now().minusDays(1));
        model.addAttribute("search",new Search());
        model.addAttribute("value",search.getValue());
        model.addAttribute("searchValue",getCountryValues(allState,search.getValue()));
        return "home";
    }

    private SearchValueDto getCountryValues(List<LocationState> allState, String value) {
        SearchValueDto searchValueDto = new SearchValueDto();
        List<LocationState> selecetedCountry = allState.stream().filter(country -> country.getCountry().equalsIgnoreCase(value)).collect(Collectors.toList());
        int totalCasesCountry=selecetedCountry.stream().mapToInt(country-> country.getCurrentTotalCases()).sum();
        int totalDifference=selecetedCountry.stream().mapToInt(country->country.getDifference()).sum();
        searchValueDto.setCountry(value);
        searchValueDto.setCurrentTotalCases(totalCasesCountry);
        searchValueDto.setDifference(totalDifference);
          return searchValueDto;
    }


    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
        return "result";
    }
    private List<String> getAllCountryies(List<LocationState> allState) {
        List<String> country = allState.stream().map(_country -> _country.getCountry()).distinct().collect(Collectors.toList());
        for (int i=1;i<country.toArray().length;i++){
            System.out.println(country);
        }
        return country;
    }

 class Greeting{
     private long id;
     private String content;

     public Greeting(){}
     public long getId() {
         return id;
     }

     public void setId(long id) {
         this.id = id;
     }

     public String getContent() {
         return content;
     }

     public void setContent(String content) {
         this.content = content;
     }
 }
}

