package com.company.spacetrans.app;

import com.company.spacetrans.entity.Planet;
import io.jmix.core.DataManager;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.core.SaveContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

//        Меркурий;0,06;0,39;0,24;58,65;no
//        Венера;0,81;0,72;0,62;243,02;no
//        Земля;1;1;1;1;no
//        Марс;0,11;1,52;1,88;1,03;no
//        Юпитер;317,83;5,2;11,86;0,41;yes
//        Сатурн;95,16;9,54;29,45;0,44;yes
//        Уран;14,54;19,19;84,02;0,72;yes
//        Нептун;17,15;30,07;164,79;0,67;yes

@Component("st_PlanetsLoadService")
public class PlanetsLoadService {
    public final int NAME_COL = 0;
    public final int MASS_COL = 1;
    public final int AXIS_COL = 2;
    public final int ORBITAL_PERIOD_COL = 3;
    public final int ROTATION_PERIOD_COL = 4;
    public final int RINGS_COL = 5;

    @Autowired
    private FileStorage fileStorage;
    @Autowired
    private DataManager dataManager;

    public void importPlanetsFromFile(FileRef fileRef) {
        InputStream is = fileStorage.openStream(fileRef);
        List<String> lines = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().parallel().collect(Collectors.toList());
        SaveContext saveContext = new SaveContext();
        for (int rowInd = 1; rowInd < lines.size(); rowInd++) {
            String[] attrValues = lines.get(rowInd).split(";");
            String name = attrValues[NAME_COL].trim();
            Planet planet = getPlanetByName(name);
            planet.setMass(Double.valueOf(attrValues[MASS_COL].trim()));
            planet.setSemiMajorAxis(Double.valueOf(attrValues[AXIS_COL].trim()));
            planet.setOrbitalPeriod(Double.valueOf(attrValues[ORBITAL_PERIOD_COL].trim()));
            planet.setRotationPeriod(Double.valueOf(attrValues[ROTATION_PERIOD_COL].trim()));
            planet.setRings(attrValues[RINGS_COL].trim().equalsIgnoreCase("yes"));
            saveContext.saving(planet);
        }
        dataManager.save(saveContext);
//        fileStorage.removeFile(fileRef);
    }

    public Planet getPlanetByName(String name) {
        Planet planet = dataManager.load(Planet.class)
                .query("select p from st_Planet p where p.name = :name")
                .parameter("name", name)
                .optional()
                .orElse(null);
        if (planet == null) {
            planet = dataManager.create(Planet.class);
            planet.setName(name);
        }
        return planet;
    }
}