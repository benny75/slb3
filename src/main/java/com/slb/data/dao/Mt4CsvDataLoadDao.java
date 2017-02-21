package com.slb.data.dao;

import com.slb.Main;
import com.slb.data.model.Ticks;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.OffsetDateTime.now;

@Component
public class Mt4CsvDataLoadDao implements DataLoadDao {

    private static final Logger logger = LoggerFactory.getLogger(Mt4CsvDataLoadDao.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    public Mt4CsvDataLoadDao(){
        System.out.println("init Mt4CsvDataLoadDao ");
    }

    public List<Ticks> load(String insturment, int timeframe) throws Throwable {
        ArrayList<Ticks> result = new ArrayList<>();

        URL path = this.getClass().getClassLoader().getResource(insturment + timeframe + ".csv");
        now().format(formatter);
        try{
            CSVParser parser = CSVParser.parse(path, Charset.defaultCharset(), CSVFormat.RFC4180);
            for (CSVRecord csvRecord : parser) {
                result.add(Ticks.builder()
                        .datetime(LocalDateTime.parse(csvRecord.get(0)+" "+csvRecord.get(1)+":00", formatter))
                        .open(Double.parseDouble(csvRecord.get(2)))
                        .high(Double.parseDouble(csvRecord.get(3)))
                        .low(Double.parseDouble(csvRecord.get(4)))
                        .close(Double.parseDouble(csvRecord.get(5)))
                        .volume(Double.parseDouble(csvRecord.get(6)))
                        .build());
            }
            return result;
        } catch(FileNotFoundException ex){
            logger.error("File read error", ex);
            throw ex;
        }
        catch(Throwable ex){
            logger.error("Mt4CsvDataLoadDao error", ex);
            throw ex;

        }
    }
}
