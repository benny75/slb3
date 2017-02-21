package com.slb;

import com.slb.backtest.Backtest;
import com.slb.data.UsdJpy;
import com.slb.strategy.MeanReversal1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@EnableAutoConfiguration
@Component
@ComponentScan
public class Main {

//    @Bean
//    public DataLoadDao dataLoadDao(){
//        return new Mt4CsvDataLoadDao();
//    }

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder in) {
//        in.application().setAdditionalProfiles(getSpeingActiveProfile());
//        return in.sources(com.slb.Main.class);
//    }

    public static void main(String[] args) {
//        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);
//        Main mainObj = ctx.getBean(Main.class);
//        mainObj.init();
        new Main().init();
    }
//
//    public String getSpeingActiveProfile(){
//        String dtap = System.getProperty(DTAP);
//        return dtap;
//    }
    public void init(){

        try {
            new Backtest(new UsdJpy(5), new MeanReversal1());
        } catch (Throwable throwable) {
            logger.error("Error occurred", throwable);
        }
    }

}
