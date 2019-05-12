package com.ritmx.com.ritmx.engine;

import com.ritmx.model.RitmxConnexion;
import com.ritmx.parseur.IParseur;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is the core engine of the logger tracker
 *
 */


public class RitmxConnexionEngine {

    private IParseur parseur;

    private static final long HOUR_IN_MILLIS=3600000;

    public RitmxConnexionEngine(IParseur parseur) {
        this.parseur = parseur;
    }

    /**
     * This method returns all servers names that connected to given target host whithin one hour
     * @param startDateTimeInMs
     * @param targetHost
     * @param log
     * @return all source hosts that reach targetHost
     *
     * TODO: may be date instead of milis
     */

    public List<String> getSourceHostNameByHour(long  startDateTimeInMs, String targetHost, File log){

        long endDateTimeInMs = startDateTimeInMs + HOUR_IN_MILLIS;
        List<RitmxConnexion> ritmxConnexions = parseur.parse(log);
        //get all connexions within one hour given a target host
        List<RitmxConnexion> connexionsOfHour = ritmxConnexions.parallelStream().filter(ritmxConnexion -> ritmxConnexion.getTimestamp()>=startDateTimeInMs && ritmxConnexion.getTimestamp()<=endDateTimeInMs && ritmxConnexion.getTargetHost().equals(targetHost)).collect(Collectors.toList());

        return  connexionsOfHour.stream().map(connexion->connexion.getSourceHost()).collect(Collectors.toList());
    }

    /**
     * This method returns all target host that were reached by a given host within one hour
     * @param startDateTimeInMs
     * @param sourceHost
     * @param log
     * @return all targets that have been reached by sourceHost
     *
     */
    public List<String> getTargetHostNameByHour(long startDateTimeInMs, String sourceHost, File log){
        long endDateTimeInMs = startDateTimeInMs + HOUR_IN_MILLIS;
        List<RitmxConnexion> ritmxConnexions = parseur.parse(log);
        //get all connexions within one hour given a source host
        List<RitmxConnexion> connexionsOfHour = ritmxConnexions.parallelStream().filter(ritmxConnexion -> ritmxConnexion.getTimestamp()>=startDateTimeInMs && ritmxConnexion.getTimestamp()<=endDateTimeInMs && ritmxConnexion.getSourceHost().equals(sourceHost)).collect(Collectors.toList());

        return  connexionsOfHour.stream().map(connexion->connexion.getTargetHost()).collect(Collectors.toList());
    }



}
