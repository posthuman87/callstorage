package com.application.dao;

import com.application.dto.CallDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Call Data Access Object
 */
@Repository
public class CallDAO {

    /**
     * Time pattern
     */
    private static final String TIME_PATTERN = "HH:mm:ss";

    /**
     * Encoding for writing data to file
     */
    private static final String ENCODING = "UTF-8";

    /**
     * Call storage folder
     */
    @Value("${storage.path}")
    private String STORAGE_PATH;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);

    public void save(CallDTO callDTO) throws FileNotFoundException, UnsupportedEncodingException {
        String firstName = callDTO.getFirstName();
        String fileName;
        if (firstName != null) {
            fileName = firstName.toUpperCase() + "_" + callDTO.getLastName().toUpperCase() + ".txt";
        } else {
            fileName = "_" + callDTO.getLastName().toUpperCase() + ".txt";
        }
        PrintWriter writer = new PrintWriter(STORAGE_PATH + fileName, ENCODING);
        writer.println(callDTO.getFormattedPhoneNumber());
        writer.println(formatter.format(LocalTime.now()));

        writer.flush();
        writer.close();
    }

}
