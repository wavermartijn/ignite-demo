package org.wavermartijn.ignitedemo.com.wavermartijn.ignitedemo.controller;

/**
 * Created by pauw on 6/30/2016.
 */

import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.extern.java.Log;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.lang.IgniteCallable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/status")
@Log
public class StatusController {

    @Inject
    Ignite ignite;

    @RequestMapping(method = RequestMethod.GET)
    public String showStatus(){

        log.info("Responding to status request");

        final IgniteCompute igniteCompute = ignite.compute(ignite.cluster());
        igniteCompute.broadcast(new IgniteCallable<Object>() {
            @Override
            public Object call() throws Exception {
                log.info("UUID of this cluster node is "+ignite.cluster().localNode().id());
                return true;
            }
        });

        return "Ignite = "+ignite+
                "REST status is ok";
    }
}
