package com.example.meteo.repository;

import com.example.meteo.entity.DailyMeasurement;
import com.example.meteo.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DailyMeasurementsRepository extends JpaRepository<DailyMeasurement, Long> {

    //metoda bez własnego zapytania
    List<DailyMeasurement> findAllByDzienAfterAndDzienBeforeAndStacja(Date dataF, Date dataT, Station s);

    //metoda z własnym zapytaniem
    @Query(value = "select min(d.dzien) from daily_measurement d where d.id_stacji = :id_stacji", nativeQuery = true)
    Date getFirstMeasurement(@Param("id_stacji") Long id_stacji);

    @Query(value = "select avg(d.max_t) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getAvgTYmax(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select avg(d.max_t) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getAvgTMmax(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select avg(d.min_t) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getAvgTYmin(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select avg(d.min_t) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getAvgTMmin(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select avg(d.sr_t) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getAvgTYavg(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select avg(d.sr_t) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getAvgTMavg(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select sum(d.sum_opad) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getSumRainfallY(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select sum(d.sum_opad) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getSumRainfallM(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select avg(d.sr_wilg) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getAvgHumY(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select avg(d.sr_wilg) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getAvgHumM(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select avg(d.sr_pr_wiatr) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getAvgWindSpeedY(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select avg(d.sr_pr_wiatr) from daily_measurement d where d.id_stacji = :id_stacji and d.dzien between :dataOd and :dataDo", nativeQuery = true)
    Double getAvgWindSpeedM(@Param("id_stacji") Long id_stacji, @Param("dataOd") Date dataOd, @Param("dataDo") Date dataDo);

    @Query(value = "select * from daily_measurement d where d.id_stacji = :id_stacji order by d.max_t desc limit 1", nativeQuery = true)
    DailyMeasurement getDailyMeasurementOrderBymaxTtDesc(@Param("id_stacji") Long id_stacji);

    @Query(value = "select * from daily_measurement d where d.id_stacji = :id_stacji order by d.min_t asc limit 1", nativeQuery = true)
    DailyMeasurement getDailyMeasurementByStacjaOrderBymintAsc(@Param("id_stacji") Long id_stacji);

    @Query(value = "select * from daily_measurement d where d.id_stacji = :id_stacji order by d.sr_pr_wiatr desc limit 1", nativeQuery = true)
    DailyMeasurement getDailyMeasurementByStacjaOrderBysrprwiatrDesc(@Param("id_stacji") Long id_stacji);

    @Query(value = "select * from daily_measurement d where d.id_stacji = :id_stacji order by d.sum_opad desc limit 1", nativeQuery = true)
    DailyMeasurement getDailyMeasurementByStacjaOrderBySumopadDesc(@Param("id_stacji") Long id_stacji);

    @Query(value = "select * from daily_measurement d where d.id_stacji = :id_stacji order by d.dzien desc limit 1", nativeQuery = true)
    DailyMeasurement getDailyMeasurementByStacjaOrderByDzienDesc(@Param("id_stacji") Long id_stacji);

    @Query(value = "select min(d.min_t) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long minMinT(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select max(d.min_t) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long maxMinT(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select min(d.max_t) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long minMaxT(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select max(d.max_t) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long maxMaxT(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select min(d.sr_t) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long minSrT(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select max(d.sr_t) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long maxSrT(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select min(d.min_t_gr) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long minMinTG(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select max(d.min_t_gr) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long maxMinTG(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select min(d.sum_opad) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long minSumOpad(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select max(d.sum_opad) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long maxSumOpad(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select count(d.dzien) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac and rodzaj_opad is null", nativeQuery = true)
    Long opadNull(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select count(d.dzien) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac and rodzaj_opad = 'W'", nativeQuery = true)
    Long opadW(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select count(d.dzien) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac and rodzaj_opad = 'S'", nativeQuery = true)
    Long opadS(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select min(d.wys_po_sn) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long minWysPokS(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select max(d.wys_po_sn) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long maxWysPokS(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select min(d.sr_wilg) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long minSrWilg(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select max(d.sr_wilg) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long maxSrWilg(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select min(d.sr_pr_wiatr) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long minSrPredW(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select max(d.sr_pr_wiatr) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long maxSrPredW(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select min(d.uslonecz) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long minUslonecz(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    @Query(value = "select max(d.uslonecz) from daily_measurement d where d.id_stacji = :id_stacji and day(d.dzien) = :dzien and month (d.dzien) = :miesiac", nativeQuery = true)
    Long maxUslonecz(@Param("id_stacji") Long id_stacji, @Param("dzien") Long dzien, @Param("miesiac") Long miesiac);

    DailyMeasurement findByStacjaAndDzien(Station s, Date dzien);
}
