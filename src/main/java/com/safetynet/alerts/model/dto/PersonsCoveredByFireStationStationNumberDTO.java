package com.safetynet.alerts.model.dto;

import com.safetynet.alerts.model.Persons;
import lombok.Data;

import java.util.List;

/**
 * List of Person covered by a specific fire station
 */

/*
    http://localhost:8080/firestation?stationNumber=<station_number>

    Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
    Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste doit inclure
    les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus, elle doit fournir un décompte
    du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou moins) dans la zone desservie.

    */

@Data
public class PersonsCoveredByFireStationStationNumberDTO {

 List<Persons> persons;

 int count_child;
 int count_adult;

 public PersonsCoveredByFireStationStationNumberDTO(){
  this.count_adult = count_adult;
  this.count_child = count_child;
  this.persons = persons;
 }


} //END
