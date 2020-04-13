package com.nazkord.siemajero.webClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nazkord.siemajero.model.dto.footballData.Match;
import com.nazkord.siemajero.model.dto.footballData.MatchResponse;
import com.nazkord.siemajero.services.DbService;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FootballDataClientTest {

    private DbService dbService;
    FootballDataClient footballDataClient = new FootballDataClient(dbService);

    @Test
    public void testMatchJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream jsonStream = getClass().getResourceAsStream("/matches.json");

        MatchResponse match = mapper.readValue(jsonStream, MatchResponse.class);

        System.out.println("match = " + match);
    }

    private static final String URL = "https://api.football-data.org/v2";
    private static final String TOKEN = "7d273acc9c9548c9885398f85780fe2e";

    @Test
    public void testMatchJson2() throws IOException {
        WebClient webClient = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader("X-Auth-Token", TOKEN)
                .build();
        Mono<MatchResponse> todayMatchesJson = webClient.get()
                .uri("/matches")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MatchResponse.class);

        System.out.println(todayMatchesJson);

        ObjectMapper mapper = new ObjectMapper();

//        MatchResponse matchesInResponse = mapper.readValue(todayMatchesJson.toString(), MatchResponse.class);

//        List<Match> matches = matchesInResponse.getMatches();

//        matches.forEach(Match -> System.out.println(Match.toString()));
    }

    @Test
    public void creatorDateStringTest() {
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = localDate1.plusDays(10);
        assertEquals("dateFrom=" + localDate1.toString() + "&dateTo=" + localDate2.toString(), footballDataClient.creatorDateString());
    }
}