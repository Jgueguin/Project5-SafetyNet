package com.safetynet.alerts.model.dto;

import lombok.Data;

/**
 * Person fire dto
 */
@Data
public class PersonFireDTO {

    /**
     * Instantiates a new Person fire dto.
     */
    public PersonFireDTO() {
    }

    /**
     * Instantiates a new Person fire dto.
     *
     * @param stationNumber the station number
     * @param firstName     the first name
     * @param lastName      the last name
     * @param phone         the phone
     * @param age           the age
     * @param address       the address
     * // @param allergies     the allergies
     * // @param medications   the medications
     */
    public PersonFireDTO(Integer stationNumber, String firstName,
                         String lastName, String phone, int age, String address
    ) {
        this.stationNumber = stationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.address = address;
    }

    /**
     * The Station number.
     */
    private Integer stationNumber;
    /**
     * The First name.
     */
    private String firstName;
    /**
     * The Last name.
     */
    private String lastName;
    /**
     * The Phone.
     */
    private String phone;

    /**
     * The Age.
     */
    private int age;

    /**
     * The address
     */
    private String address;

} //END
