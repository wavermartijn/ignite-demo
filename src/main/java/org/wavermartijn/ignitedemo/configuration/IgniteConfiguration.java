package org.wavermartijn.ignitedemo.configuration;

import lombok.extern.java.Log;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Created by pauw on 6/30/2016.
 */
@Log
@Configuration
public class IgniteConfiguration {

    private static final String IGNITE_CONFIGURATION = "/ignite-config.xml";

    @Bean(name = "ignite")
    public Ignite provideIgnite(){
        log.info("will start ignite from config location "+fromIgniteFileToString());
        return Ignition.start(fromIgniteFileToString());
    }

    private String fromIgniteFileToString(){
        return IgniteConfiguration.class.getResource(IGNITE_CONFIGURATION).getFile();
    }
}
