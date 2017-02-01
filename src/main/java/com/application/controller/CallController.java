package com.application.controller;

import com.application.dto.CallDTO;
import com.application.dto.ValidationErrorDTO;
import com.application.service.CallService;
import com.application.validator.CallValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * REST Controller for servicing calls
 */
@Controller
@RequestMapping(value = "/calls")
public class CallController {

    @Autowired
    private CallService callService;

    /**
     * Validate and save call
     *
     * @param callDTO call data
     * @return List<ValidationErrorDTO> list of errors
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<ValidationErrorDTO>> addCall(@RequestBody CallDTO callDTO)
            throws FileNotFoundException, UnsupportedEncodingException {
        List<ValidationErrorDTO> errors = CallValidator.validate(callDTO);
        if (errors.size() > 0) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        callService.saveCall(callDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Exception handler for any unexpected internal exception
     *
     * @param e {@link Exception}
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleUnexpectedException(Exception e) {
        return e.getMessage();
    }

}
