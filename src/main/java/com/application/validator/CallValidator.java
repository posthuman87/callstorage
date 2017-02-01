package com.application.validator;

import com.application.dto.CallDTO;
import com.application.dto.ValidationErrorDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Call validator
 */
public class CallValidator {

    public static List<ValidationErrorDTO> validate(CallDTO callDTO) {
        List<ValidationErrorDTO> result = new ArrayList<>();

        //validate first and last name
        validateName(callDTO, result);

        //validate phoneNumber
        String phoneNumber = callDTO.getPhoneNumber();
        if (phoneNumber == null) {
            result.add(new ValidationErrorDTO("phoneNumber", "Mandatory field!"));
            return result;
        } else {
            //check parenthesis
            int index = 0;
            for (int i = 0; i < phoneNumber.length(); i++) {
                switch (phoneNumber.charAt(i)) {
                    case '(':
                        index++;
                        break;
                    case ')':
                        index--;
                        if (index < 0) { //')' closes unexisted '('
                            result.add(new ValidationErrorDTO("phoneNumber",
                                    "Wrong phoneNumber format! Example: +(420) 111 222 333"));
                            return result;
                        }
                        break;
                    default:
                        break;
                }
            }
            if (index > 0) {//count of '(' more then count of ')'
                result.add(new ValidationErrorDTO("phoneNumber",
                        "Wrong phoneNumber format! Example: +(420) 111 222 333"));
                return result;
            }

            //remove all ')','(','-' and spaces
            String formattedPhoneNumber = callDTO.getPhoneNumber().replaceAll("[)(\\-\\p{Space}]", "");

            //check the phoneNumber contains only numbers 0-9 and optionally starts from '+'
            if (!formattedPhoneNumber.matches("[+]?[0-9]+")) {
                result.add(new ValidationErrorDTO("phoneNumber",
                        "Wrong phoneNumber format! Example: +(420) 111 222 333"));
                return result;
            } else {
                //here formattedPhoneNumber contains only numbers 0-9 and optionally starts from '+'
                //so valid phoneNumber should have length 9 or 13 or 14
                switch (formattedPhoneNumber.length()) {
                    case 9: //xxx xxx xxx should NOT start from '+'
                        if (formattedPhoneNumber.startsWith("+")) {
                            result.add(new ValidationErrorDTO("phoneNumber",
                                    "Wrong phoneNumber format! Example: +(420) 111 222 333"));
                        }
                        break;
                    case 13: //+YYY xxx xxx xxx should start from '+'
                        if (!formattedPhoneNumber.startsWith("+")) {
                            result.add(new ValidationErrorDTO("phoneNumber",
                                    "Wrong phoneNumber format! Example: +(420) 111 222 333"));
                        }
                        break;
                    case 14: //00YYY xxx xxx xxx should NOT start from '+' and should start from '00'
                        if (!formattedPhoneNumber.startsWith("00")) {
                            result.add(new ValidationErrorDTO("phoneNumber",
                                    "Wrong phoneNumber format! Example: +(420) 111 222 333"));
                        }
                        break;
                    default:
                        result.add(new ValidationErrorDTO("phoneNumber",
                                "Wrong phoneNumber format! Example: +(420) 111 222 333"));
                }
            }
        }
        return result;
    }


    private static void validateName(CallDTO callDTO, List<ValidationErrorDTO> result) {
        //validate firstName
        if (callDTO.getFirstName() != null && callDTO.getFirstName().length() > 30) {
            result.add(new ValidationErrorDTO("firstName", "Max length 30 characters"));
        }

        //validate lastName
        if (callDTO.getLastName() == null) {
            result.add(new ValidationErrorDTO("lastName", "Mandatory field!"));
        } else
        if (callDTO.getLastName().length() > 30) {
            result.add(new ValidationErrorDTO("lastName", "Max length 30 characters"));
        }
    }
}
