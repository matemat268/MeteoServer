package com.example.meteo.controller;

import com.example.meteo.entity.DailyMeasurement;
import com.example.meteo.entity.Station;
import com.example.meteo.repository.DailyMeasurementsRepository;
import com.example.meteo.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@RestController
public class DailyMeasurementController {

    DailyMeasurementsRepository dailyMeasurementsRepository;
    StationRepository stationRepository;

    @Autowired
    public DailyMeasurementController(DailyMeasurementsRepository dailyMeasurementsRepository, StationRepository stationRepository) {
        this.dailyMeasurementsRepository = dailyMeasurementsRepository;
        this.stationRepository = stationRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/measurement")
    public ArrayList<DailyMeasurement> addDailyMeasurements(@RequestBody ArrayList<DailyMeasurement> measurementList) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (DailyMeasurement s:measurementList) {
            stationRepository.findById(s.getStacja().getId_stacji()).get();
            DailyMeasurement dm = dailyMeasurementsRepository.findByStacjaAndDzien(stationRepository.findById(s.getStacja().getId_stacji()).get(), s.getDzien());
            if(dm == null)
                dailyMeasurementsRepository.save(s);
            else {
                if(s.getMax_t() != null)
                    dm.setMax_t(s.getMax_t());
                if(s.getMin_t() != null)
                    dm.setMin_t(s.getMin_t());
                if(s.getSr_t() != null)
                    dm.setSr_t(s.getSr_t());
                if(s.getMin_t_gr() != null)
                    dm.setMin_t_gr(s.getMin_t_gr());
                if(s.getSum_opad() != null)
                    dm.setSum_opad(s.getSum_opad());
                if(s.getRodzaj_opad() != null)
                    dm.setRodzaj_opad(s.getRodzaj_opad());
                if(s.getWys_po_sn() != null)
                    dm.setWys_po_sn(s.getWys_po_sn());
                if(s.getSr_wilg() != null)
                    dm.setSr_wilg(s.getSr_wilg());
                if(s.getSr_pr_wiatr() != null)
                    dm.setSr_pr_wiatr(s.getSr_pr_wiatr());
                if(s.getSr_zachm() != null)
                    dm.setSr_zachm(s.getSr_zachm());
                if(s.getUslonecz() != null)
                    dm.setUslonecz(s.getUslonecz());
                dailyMeasurementsRepository.save(dm);
            }
        }
        return measurementList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/myMeasurement")
    public ArrayList<DailyMeasurement> addMyDailyMeasurements(@RequestBody ArrayList<DailyMeasurement> measurementList) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (DailyMeasurement s:measurementList) {
            dailyMeasurementsRepository.save(s);
        }
        return measurementList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/measurements/{agregation}/{dateFrom}/{dateTo}/{stationId}/{myMeasurement}")
    public List<DailyMeasurement> getMeasurementByKriteria(@PathVariable String agregation,
                                                           @PathVariable String dateFrom,
                                                           @PathVariable String dateTo,
                                                           @PathVariable Long stationId,
                                                           @PathVariable Boolean myMeasurement ) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<DailyMeasurement> l = new ArrayList<>();
        List<DailyMeasurement> toReturn = new ArrayList<>();
        try {
            Date dateF = dateFormat.parse(dateFrom);
            Date dateT = dateFormat.parse(dateTo);
            Station s = stationRepository.findById(stationId).get();
            l = dailyMeasurementsRepository.findAllByDzienAfterAndDzienBeforeAndStacja(dateF, dateT, s);
            for (DailyMeasurement dm : l) {
                dm.setGrupaPomiarow(null);
            }
            if (agregation.equals("M")) {
                int diff = Period.between(dateF.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dateT.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getMonths() +
                        Period.between(dateF.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dateT.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getYears() * 12;
                if (diff == 0)
                    diff = 1;
                ArrayList<Date> listaDatM = new ArrayList<>();
                listaDatM.add(dateF);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateF);
                cal.add(Calendar.MONTH, 1);
                for (int i = 0; i < diff; i++) {
                    cal.add(Calendar.MONTH, 1);
                    listaDatM.add(dateFormat.parse(Integer.toString(cal.get(Calendar.YEAR)) + "-" + Integer.toString(cal.get(Calendar.MONTH)) + "-" + Integer.toString(1)));

                }

                for (int i = 0; i < diff; i++) {
                    DailyMeasurement el = new DailyMeasurement();
                    cal.setTime(listaDatM.get(i));
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    el.setDzien(cal.getTime());
                    int licznik = 0;
                    for (DailyMeasurement dm : l) {
                        if (dm.getDzien().after(listaDatM.get(i)) && dm.getDzien().before(listaDatM.get(i+1)))
                        {
                            el.setMax_t((el.getMax_t() != null ? el.getMax_t() : 0) + dm.getMax_t());
                            el.setMin_t((el.getMin_t() != null ? el.getMin_t() : 0) + dm.getMin_t());
                            el.setSr_t((el.getSr_t() != null ? el.getSr_t() : 0) + dm.getSr_t());
                            el.setMin_t_gr((el.getMin_t_gr() != null ? el.getMin_t_gr() : 0) + dm.getMin_t_gr());
                            el.setSum_opad((el.getSum_opad() != null ? el.getSum_opad() : 0) + dm.getSum_opad());
                            el.setWys_po_sn((el.getWys_po_sn() != null ? el.getWys_po_sn() : 0) + dm.getWys_po_sn());
                            el.setSr_wilg((el.getSr_wilg() != null ? el.getSr_wilg() : 0) + (dm.getSr_wilg() != null ? dm.getSr_wilg() : 0));
                            el.setSr_pr_wiatr((el.getSr_pr_wiatr() != null ? el.getSr_pr_wiatr() : 0) + (dm.getSr_pr_wiatr() != null ? dm.getSr_pr_wiatr() : 0));
                            el.setSr_zachm((el.getSr_zachm() != null ? el.getSr_zachm() : 0) + (dm.getSr_zachm() != null ? dm.getSr_zachm() : 0));
                            el.setUslonecz((el.getUslonecz() != null ? el.getUslonecz() : 0) + (dm.getUslonecz() != null ? dm.getUslonecz() : 0));
                            licznik += 1;
                        }
                    }
                    if (licznik > 0) {
                        el.setMax_t(BigDecimal.valueOf(el.getMax_t() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setMin_t(BigDecimal.valueOf(el.getMin_t() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setSr_t(BigDecimal.valueOf(el.getSr_t() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setMin_t_gr(BigDecimal.valueOf(el.getMin_t_gr() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setSum_opad(BigDecimal.valueOf(el.getSum_opad() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setWys_po_sn(BigDecimal.valueOf(el.getWys_po_sn() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setSr_wilg(BigDecimal.valueOf(el.getSr_wilg() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setSr_pr_wiatr(BigDecimal.valueOf(el.getSr_pr_wiatr() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setSr_zachm(BigDecimal.valueOf(el.getSr_zachm() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setUslonecz(BigDecimal.valueOf(el.getUslonecz() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setStacja(s);
                        toReturn.add(el);
                    }
                }

            } else if (agregation.equals("R")) {
                int diff = Period.between(dateF.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dateT.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getYears();
                if (diff == 0)
                    diff = 1;
                ArrayList<Date> listaDatM = new ArrayList<>();
                listaDatM.add(dateF);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateF);
                cal.add(Calendar.YEAR, 1);
                for (int i = 0; i < diff; i++) {
                    cal.add(Calendar.YEAR, 1);
                    listaDatM.add(dateFormat.parse(Integer.toString(cal.get(Calendar.YEAR)) + "-" + Integer.toString(1) + "-" + Integer.toString(1)));

                }

                for (int i = 0; i < diff; i++) {
                    DailyMeasurement el = new DailyMeasurement();
                    cal.setTime(listaDatM.get(i));
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    el.setDzien(cal.getTime());
                    int licznik = 0;
                    for (DailyMeasurement dm : l) {
                        if (dm.getDzien().after(listaDatM.get(i)) && dm.getDzien().before(listaDatM.get(i+1)))
                        {
                            el.setMax_t((el.getMax_t() != null ? el.getMax_t() : 0) + dm.getMax_t());
                            el.setMin_t((el.getMin_t() != null ? el.getMin_t() : 0) + dm.getMin_t());
                            el.setSr_t((el.getSr_t() != null ? el.getSr_t() : 0) + dm.getSr_t());
                            el.setMin_t_gr((el.getMin_t_gr() != null ? el.getMin_t_gr() : 0) + dm.getMin_t_gr());
                            el.setSum_opad((el.getSum_opad() != null ? el.getSum_opad() : 0) + dm.getSum_opad());
                            el.setWys_po_sn((el.getWys_po_sn() != null ? el.getWys_po_sn() : 0) + dm.getWys_po_sn());
                            el.setSr_wilg((el.getSr_wilg() != null ? el.getSr_wilg() : 0) + (dm.getSr_wilg() != null ? dm.getSr_wilg() : 0));
                            el.setSr_pr_wiatr((el.getSr_pr_wiatr() != null ? el.getSr_pr_wiatr() : 0) + (dm.getSr_pr_wiatr() != null ? dm.getSr_pr_wiatr() : 0));
                            el.setSr_zachm((el.getSr_zachm() != null ? el.getSr_zachm() : 0) + (dm.getSr_zachm() != null ? dm.getSr_zachm() : 0));
                            el.setUslonecz((el.getUslonecz() != null ? el.getUslonecz() : 0) + (dm.getUslonecz() != null ? dm.getUslonecz() : 0));
                            licznik += 1;
                        }
                    }
                    if (licznik > 0) {
                        el.setMax_t(BigDecimal.valueOf(el.getMax_t() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setMin_t(BigDecimal.valueOf(el.getMin_t() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setSr_t(BigDecimal.valueOf(el.getSr_t() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setMin_t_gr(BigDecimal.valueOf(el.getMin_t_gr() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setSum_opad(BigDecimal.valueOf(el.getSum_opad() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setWys_po_sn(BigDecimal.valueOf(el.getWys_po_sn() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setSr_wilg(BigDecimal.valueOf(el.getSr_wilg() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setSr_pr_wiatr(BigDecimal.valueOf(el.getSr_pr_wiatr() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setSr_zachm(BigDecimal.valueOf(el.getSr_zachm() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setUslonecz(BigDecimal.valueOf(el.getUslonecz() / licznik).setScale(3, RoundingMode.HALF_UP).doubleValue());
                        el.setStacja(s);
                        toReturn.add(el);
                    }
                }

            } else
                toReturn = l;
        } catch (ParseException e) {
        }
        return toReturn;
    }

}
