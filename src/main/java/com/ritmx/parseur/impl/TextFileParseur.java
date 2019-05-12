package com.ritmx.parseur.impl;

import com.ritmx.model.RitmxConnexion;
import com.ritmx.parseur.IParseur;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileParseur implements IParseur {

    /**
     *
     * TODO: close file after using
     * @param file
     * @return
     */

    public List<RitmxConnexion> parse(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            String logfileContent = "";
            while ((st = br.readLine()) != null){
                logfileContent+=st+"\n";
            }
            List<RitmxConnexion> ritmxConnexions = getConnexions(logfileContent);
            return ritmxConnexions;

        } catch (IOException e) {
            throw new RuntimeException("Log file not found",e);
        }
    }

    private List<RitmxConnexion> getConnexions(String fileContent) {
        List<RitmxConnexion> ritmxConnexions = new ArrayList<>();
        if(fileContent != null && fileContent.trim().length()>0) {
            String[] splitContent = fileContent.split("\n");
            for(String lineContent : splitContent){
                String [] splitLine = lineContent.split(" ");
                RitmxConnexion ritmxConnexion = new RitmxConnexion();
                ritmxConnexion.setTimestamp(Long.valueOf(splitLine[0]));
                ritmxConnexion.setSourceHost(splitLine[1]);
                ritmxConnexion.setTargetHost(splitLine[2]);
                ritmxConnexions.add(ritmxConnexion);
            }
            return ritmxConnexions;
        }
        throw new RuntimeException("Log file content is empty");
    }
}
