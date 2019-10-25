package com.nazkord.siemajero.webClient;

import com.nazkord.siemajero.model.dto.footballData.Match;
import com.nazkord.siemajero.model.dto.footballData.MatchResponse;
import com.nazkord.siemajero.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
@EnableScheduling
public class FootballDataClient {

    private static final String datePattern = "YYYY-MM-dd";
    private static final String URL = "https://api.football-data.org/v2";
    private static final String TOKEN = "7d273acc9c9548c9885398f85780fe2e";

    private final WebClient webClient;
    private final DbService dbService;

    @Autowired
    public FootballDataClient(DbService dbService) {
        webClient = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader("X-Auth-Token", TOKEN)
                .build();
        this.dbService = dbService;
    }

    //TODO: think out of better idea (now downloading every matches for 2 weeks)

    //TODO: find out where and how throw JsonParseException

    @Scheduled(fixedDelay = 20000)
    public void loadAllTodayMatches() {
        System.out.println("CHECKING");

        MatchResponse matchesInResponse = webClient.get()
                .uri("/matches?" + creatorDateString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MatchResponse.class)
                .block();

        List<Match> matches = Optional.ofNullable(matchesInResponse)
                .map(MatchResponse::getMatches)
                .orElseThrow(() -> new RuntimeException("add description here"));

        System.out.println("matches = " + matches);

        dbService.sync(matches);
    }

    public String creatorDateString() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        currentDate.format(formatter);
        String currentDateString = currentDate.toString();

        String nextDateString = currentDate.plusDays(10).toString();

        return "dateFrom=" + currentDateString + "&dateTo=" + nextDateString;
    }
}
