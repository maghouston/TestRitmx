package com.ritmx.engine;

import com.ritmx.com.ritmx.engine.RitmxConnexionEngine;
import com.ritmx.parseur.IParseur;
import com.ritmx.parseur.impl.TextFileParseur;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This test class aims to check nominated cases otherwise it will be nice to check some special cases
 * Like when file is not found. Is there an thrown exception?
 *
 * TODO: load test resources from test/resources instead of file systems
 */

public class RitmxConnexionEngineTest {

    private RitmxConnexionEngine engine;

    @Before
    public void init(){
        IParseur parseur = new TextFileParseur();
        engine = new RitmxConnexionEngine(parseur);
    }


    @Test
    public void testGetSourceHostNameByHourShouldNotBeEmpty(){
        //GIVEN
            File file = new File("D:\\tmp\\log.txt");
            String targetHost = "garak";

        //WHEN
        List<String> sourceHosts = engine.getSourceHostNameByHour(1557402948830L ,targetHost,file);
        //THEN
        assertThat(sourceHosts).isNotEmpty();
        assertThat(sourceHosts).hasSize(2);
    }

    @Test
    public void testGetTargetHostNameByHourShouldNotBeEmpty(){
        //GIVEN
        File file = new File("D:\\tmp\\log.txt");
        String sourceHost = "quark";

        //WHEN
        List<String> sourceHosts = engine.getTargetHostNameByHour(1557402948830L ,sourceHost,file);
        //THEN
        assertThat(sourceHosts).isNotEmpty();
    }


}
