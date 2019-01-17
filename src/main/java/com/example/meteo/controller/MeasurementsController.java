package com.example.meteo.controller;

import com.example.meteo.entity.DailyMeasurement;
import com.example.meteo.entity.Measurements;
import com.example.meteo.entity.Station;
import com.example.meteo.repository.DailyMeasurementsRepository;
import com.example.meteo.repository.MeasurementsRepository;
import com.example.meteo.repository.StationRepository;
import com.example.meteo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaSize;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class MeasurementsController {

    MeasurementsRepository measurementsRepository;
    UserRepository userRepository;
    StationRepository stationRepository;
    DailyMeasurementsRepository dailyMeasurementsRepository;

    @Autowired
    public MeasurementsController(MeasurementsRepository measurementsRepository,
                                  UserRepository userRepository,
                                  StationRepository stationRepository,
                                  DailyMeasurementsRepository dailyMeasurementsRepository) {
        this.measurementsRepository = measurementsRepository;
        this.userRepository = userRepository;
        this.stationRepository = stationRepository;
        this.dailyMeasurementsRepository = dailyMeasurementsRepository;
    }

    /**
     * Metoda zapisuje w bazie nową grupę pomiarów
     * @param measurementGroup - wstępnie utworzona grupa z wypełnionym polem użytkownik dodający
     * @return
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/measurementGroup")
    public Measurements addMeasurementGroup(@RequestBody Measurements measurementGroup) {
        //sprawdzenie czy dane użytkownika są poprawne
        if(userRepository.findById(measurementGroup.getUzyt().getId_uzyt()) != null) {
            //data dodania
            measurementGroup.setData_dod(new Date());
            measurementGroup.setTyp("H");
            //zapis do bazy
            return measurementsRepository.save(measurementGroup);
        }
        return null;
    }

    /**
     * Metoda zwraca grupy pomiarów historycznych
     * @return
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/measurementGroup")
    public List<Measurements> getMeasurementGroups() {
        //pobranie wszystkich pomiarów historycznych z bazy
        List<Measurements> mList =  measurementsRepository.getAllByTyp("H");
        for(Measurements m : mList) {
            //nie ma konieczności zwracania również pomiarów dziennych
            m.setPomiary(null);
        }
        return mList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/myMeasurementGroup")
    public Measurements addMyMeasurementGroup(@RequestBody Measurements measurementGroup) {
        if(userRepository.findById(measurementGroup.getUzyt().getId_uzyt()) != null) {
            measurementGroup.setData_dod(new Date());
            measurementGroup.setTyp("NH");
            return measurementsRepository.save(measurementGroup);
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/myMeasurementGroup/{userId}")
    public List<Measurements> getMyMeasurementGroups(@PathVariable Long userId) {
        List<Measurements> mList =  measurementsRepository.findAll();
        List<Measurements> toReturn = new ArrayList<Measurements>();
        for(Measurements m : mList) {
            m.setPomiary(null);
            if(m.getUzyt().getId_uzyt() == userId)
            {
                toReturn.add(m);
            }
        }
        return toReturn;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/exampleMeasurements/{dateFrom}/{dateTo}/{stationId}")
    public List<DailyMeasurement> getExaplmeMeasurements(@PathVariable String dateFrom, @PathVariable String dateTo, @PathVariable Long stationId) {
        List<DailyMeasurement> list = new ArrayList<DailyMeasurement>();
        Random random = new Random();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Station s = stationRepository.findById(stationId).get();
        try {
            Date dateF = dateFormat.parse(dateFrom);
            Date dateT = dateFormat.parse(dateTo);
            int diff = Period.between(dateF.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dateT.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getDays();
            for (int i = 0; i < diff; i++)
            {
                DailyMeasurement dailyMeasurement = new DailyMeasurement();
                Date newDate = new Date(dateF.getTime() + TimeUnit.DAYS.toMillis(i));

                Calendar cal = Calendar.getInstance();
                cal.setTime(newDate);

                dailyMeasurement.setDzien(newDate);
                dailyMeasurement.setStacja(s);

                Long d= new Long(cal.get(Calendar.DAY_OF_MONTH));
                Long m= new Long(cal.get(Calendar.MONTH)+1);

                Long minL= dailyMeasurementsRepository.minMinT(stationId, d, m);
                Long maxL = dailyMeasurementsRepository.maxMinT(stationId, d, m);
                if (minL != null && maxL != null) {
                    Double min = new Double(minL);
                    Double max = new Double(maxL);
                    Double minT = min + random.nextDouble() * (max - min);
                    dailyMeasurement.setMin_t(minT);
                }

                minL = dailyMeasurementsRepository.minMaxT(stationId, d, m);
                maxL = dailyMeasurementsRepository.maxMaxT(stationId, d, m);
                if (minL != null && maxL != null) {
                    Double min = new Double(minL);
                    Double max = new Double(maxL);
                    Double maxT = min + random.nextDouble() * (max - min);
                    dailyMeasurement.setMax_t(maxT);
                }

                minL = dailyMeasurementsRepository.minSrT(stationId, d, m);
                maxL = dailyMeasurementsRepository.maxSrT(stationId, d, m);
                if (minL != null && maxL != null) {
                    Double min = new Double(minL);
                    Double max = new Double(maxL);
                    Double srT = min + random.nextDouble() * (max - min);
                    dailyMeasurement.setSr_t(srT);
                }

                minL = dailyMeasurementsRepository.minMinTG(stationId, d, m);
                maxL = dailyMeasurementsRepository.maxMinTG(stationId, d, m);
                if (minL != null && maxL != null) {
                    Double min = new Double(minL);
                    Double max = new Double(maxL);
                    Double minTG = min + random.nextDouble() * (max - min);
                    dailyMeasurement.setMin_t_gr(minTG);
                }

                minL = dailyMeasurementsRepository.minSumOpad(stationId, d, m);
                maxL = dailyMeasurementsRepository.maxSumOpad(stationId, d, m);
                if (minL != null && maxL != null) {
                    Double min = new Double(minL);
                    Double max = new Double(maxL);
                    Double sumOpad = min + random.nextDouble() * (max - min);
                    dailyMeasurement.setSum_opad(sumOpad);
                }

                Long opadNull = dailyMeasurementsRepository.opadNull(stationId, d, m);
                Long opadW = dailyMeasurementsRepository.opadW(stationId, d, m);
                Long opadS = dailyMeasurementsRepository.opadS(stationId, d, m);
                if (opadW > opadS) {
                    if (opadW > opadNull) {
                        dailyMeasurement.setRodzaj_opad("W");
                    }
                } else {
                    if (opadS > opadNull) {
                        dailyMeasurement.setRodzaj_opad("S");
                    }
                }

                minL = dailyMeasurementsRepository.minWysPokS(stationId, d, m);
                maxL = dailyMeasurementsRepository.maxWysPokS(stationId, d, m);
                if (minL != null && maxL != null) {
                    Double min = new Double(minL);
                    Double max = new Double(maxL);
                    Double wysPokS = min + random.nextDouble() * (max - min);
                    dailyMeasurement.setWys_po_sn(wysPokS);
                }

                minL = dailyMeasurementsRepository.minSrWilg(stationId, d, m);
                maxL = dailyMeasurementsRepository.maxSrWilg(stationId, d, m);
                if (minL != null && maxL != null) {
                    Double min = new Double(minL);
                    Double max = new Double(maxL);
                    Double wilg = min + random.nextDouble() * (max - min);
                    dailyMeasurement.setSr_wilg(wilg);
                }

                minL = dailyMeasurementsRepository.minSrPredW(stationId, d, m);
                maxL = dailyMeasurementsRepository.maxSrPredW(stationId, d, m);
                if (minL != null && maxL != null) {
                    Double min = new Double(minL);
                    Double max = new Double(maxL);
                    Double srPredW = min + random.nextDouble() * (max - min);
                    dailyMeasurement.setSr_pr_wiatr(srPredW);
                }

                minL = dailyMeasurementsRepository.minUslonecz(stationId, d, m);
                maxL = dailyMeasurementsRepository.maxUslonecz(stationId, d, m);
                if (minL != null && maxL != null) {
                    Double min = new Double(minL);
                    Double max = new Double(maxL);
                    Double uslonecz = min + random.nextDouble() * (max - min);
                    dailyMeasurement.setUslonecz(uslonecz);
                }

                list.add(dailyMeasurement);
            }
        } catch (ParseException e) {
        }
        return list;
    }
}
