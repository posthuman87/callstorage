package com.application.service;

import com.application.dao.CallDAO;
import com.application.dto.CallDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Call service
 */
@Service
public class CallService {

    /**
     * Call DAO
     */
    @Autowired
    private CallDAO callDAO;

    /**
     * format number and save
     *
     * @param callDTO
     * @return true when call is successfully saved otherwise false
     */
    public void saveCall(CallDTO callDTO) throws FileNotFoundException, UnsupportedEncodingException {
        String formattedPhoneNumber = callDTO.getPhoneNumber().replaceAll("[)(\\-\\p{Space}]", "");
        switch (formattedPhoneNumber.length()) {
            case 9: //xxx xxx xxx
                formattedPhoneNumber = "00420" + formattedPhoneNumber;
                break;
            case 13: //+YYY xxx xxx xxx
                formattedPhoneNumber = formattedPhoneNumber.replaceFirst("[+]", "00");
                break;
            case 14: //00YYY xxx xxx xxx
                //already formatted
                break;
            default:
                throw new IllegalStateException("Wrong phoneNumber format!");
        }
        callDTO.setFormattedPhoneNumber(formattedPhoneNumber);
        try {
            callDAO.save(callDTO);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Wrong storage path!" + e.getMessage());
        }
    }
}
