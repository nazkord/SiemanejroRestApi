package com.nazkord.siemajero.dbBasedService;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertThat;

//TODO: QUESTION: should I check whole method getMatchesByCompetition()?
public class DbBasedMatchServiceTest {

    private DbBasedMatchService dbBasedMatchService;
    private List<LocalDate> dates;
    private List<String> datesInString = new ArrayList<>(Arrays.asList(
            "2019-11-11",
            "2019-11-20",
            "2019-11-25",
            "2019-11-29",
            "2019-12-08"
    ));

    @Before
    public void init() {
       dbBasedMatchService = new DbBasedMatchService();
       dates = new ArrayList<>();
        datesInString.forEach(s -> {
//            dates.add(dbBasedMatchService.getDateFromString(s));
        });
    }



    @Test
    public void getParsedMatchesWeekRangeTest() {
        List<LocalDate> afterCheckList = new ArrayList<>();
        dates.forEach(d -> {
//            if(dbBasedMatchService.isWithinRange(d)) {
//                afterCheckList.add(d);
//            }
        });

//        assertEquals(afterCheckList.size(), 3);
    }
}