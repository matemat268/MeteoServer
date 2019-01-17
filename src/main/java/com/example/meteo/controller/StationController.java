package com.example.meteo.controller;

import com.example.meteo.entity.DailyMeasurement;
import com.example.meteo.entity.Station;
import com.example.meteo.entity.StationDetails;
import com.example.meteo.repository.DailyMeasurementsRepository;
import com.example.meteo.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.*;

@RestController
public class StationController {

    StationRepository stationRepository;
    DailyMeasurementsRepository dailyMeasurementsRepository;

    @Autowired
    public StationController(StationRepository stationRepository,
                             DailyMeasurementsRepository dailyMeasurementsRepository) {
        this.stationRepository = stationRepository;
        this.dailyMeasurementsRepository = dailyMeasurementsRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/stations")
    public List<Station> getStations(){
        return stationRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/stations")
    public Station addStations(@RequestBody ArrayList<Station> stationList) {
        for (Station s:stationList) {
            if(stationRepository.findById(s.getId_stacji()).equals(Optional.empty())) {
                stationRepository.save(s);
            }
        }
        return new Station("OK","1");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/stationDetails/{stationId}")
    public StationDetails getStationDetails(@PathVariable Long stationId) {

        StationDetails stationDetails = new StationDetails();
        Station s = stationRepository.findById(stationId).get();

        Date dataAkt = new Date();
        Date prevMonth = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        Date prevYear = Date.from(ZonedDateTime.now().minusMonths(12).toInstant());

        stationDetails.setFirstMeasurement(dailyMeasurementsRepository.getFirstMeasurement(stationId));
        stationDetails.setAvgTYmax(dailyMeasurementsRepository.getAvgTYmax(stationId, prevYear, dataAkt));
        stationDetails.setAvgTMmax(dailyMeasurementsRepository.getAvgTMmax(stationId, prevMonth, dataAkt));
        stationDetails.setAvgTYmin(dailyMeasurementsRepository.getAvgTYmin(stationId, prevYear, dataAkt));
        stationDetails.setAvgTMmin(dailyMeasurementsRepository.getAvgTMmin(stationId, prevMonth, dataAkt));
        stationDetails.setAvgTYavg(dailyMeasurementsRepository.getAvgTYavg(stationId, prevYear, dataAkt));
        stationDetails.setAvgTMavg(dailyMeasurementsRepository.getAvgTMavg(stationId, prevMonth, dataAkt));
        stationDetails.setSumRainfallY(dailyMeasurementsRepository.getSumRainfallY(stationId, prevYear, dataAkt));
        stationDetails.setSumRainfallM(dailyMeasurementsRepository.getSumRainfallM(stationId, prevMonth, dataAkt));
        stationDetails.setAvgHumY(dailyMeasurementsRepository.getAvgHumY(stationId, prevYear, dataAkt));
        stationDetails.setAvgHumM(dailyMeasurementsRepository.getAvgHumM(stationId, prevMonth, dataAkt));
        stationDetails.setAvgWindSpeedY(dailyMeasurementsRepository.getAvgWindSpeedY(stationId, prevYear, dataAkt));
        stationDetails.setAvgWindSpeedM(dailyMeasurementsRepository.getAvgWindSpeedM(stationId, prevMonth, dataAkt));

        DailyMeasurement maxT = dailyMeasurementsRepository.getDailyMeasurementOrderBymaxTtDesc(stationId);
        if (maxT != null ) {
            stationDetails.setMaxTValue(maxT.getMax_t());
            stationDetails.setMaxTDay(maxT.getDzien());
        }

        DailyMeasurement minT = dailyMeasurementsRepository.getDailyMeasurementByStacjaOrderBymintAsc(stationId);
        if (minT != null ) {
            stationDetails.setMinTValue(minT.getMin_t());
            stationDetails.setMinTDay(minT.getDzien());
        }

        DailyMeasurement maxWindSpeed = dailyMeasurementsRepository.getDailyMeasurementByStacjaOrderBysrprwiatrDesc(stationId);
        if (maxWindSpeed != null ) {
            stationDetails.setMaxWindSpeedValue(maxWindSpeed.getSr_pr_wiatr());
            stationDetails.setMaxWindSpeedDay(maxWindSpeed.getDzien());
        }

        DailyMeasurement maxOpad = dailyMeasurementsRepository.getDailyMeasurementByStacjaOrderBySumopadDesc(stationId);
        if (maxOpad != null ) {
            stationDetails.setMaxRainfallValue(maxOpad.getSum_opad());
            stationDetails.setMaxRainfallDay(maxOpad.getDzien());
        }

        DailyMeasurement lastMeasurement = dailyMeasurementsRepository.getDailyMeasurementByStacjaOrderByDzienDesc(stationId);
        if (lastMeasurement != null ) {
            lastMeasurement.setGrupaPomiarow(null);
            stationDetails.setLastMeasurement(lastMeasurement);
        }

        return stationDetails;
    }
}
