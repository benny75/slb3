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
import java.util.List;

@Component
public class Mt4CsvDataLoadDao implements DataLoadDao {

    private static final Logger logger = LoggerFactory.getLogger(Mt4CsvDataLoadDao.class);

    public Mt4CsvDataLoadDao(){
        System.out.println("init Mt4CsvDataLoadDao ");
    }

    public Ticks load(String insturment, int timeframe) throws Throwable {
        URL path = this.getClass().getClassLoader().getResource(insturment + timeframe + ".csv");
//        File csvData = new File("resources/mt4/" + insturment + timeframe + ".csv");
        try{
            CSVParser parser = CSVParser.parse(path, Charset.defaultCharset(), CSVFormat.RFC4180);
            List<CSVRecord> list = parser.getRecords();
            list.get(0);
        } catch(FileNotFoundException ex){
            logger.error("File read error", ex);
            throw ex;
        }
        catch(Throwable ex){
            logger.error("Mt4CsvDataLoadDao error", ex);
            throw ex;

        }
        return null;
    }
}
