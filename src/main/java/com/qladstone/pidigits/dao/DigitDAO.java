package com.qladstone.pidigits.dao;

import com.qladstone.pidigits.representation.DigitOfPi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class DigitDAO {
    public DigitOfPi retrieveDigit(long position) throws DigitOfPiNotFoundException {
        int value = -1;
        if (position == 0) {
            value = 3;
        } else if (position == 1) {
            value = 1;
        } else if (position == 2) {
            value = 4;
        } else if (position > 2) {
            throw new DigitOfPiNotFoundException();
        }
        return new DigitOfPi(position, value);
    }

    public static class DigitOfPiNotFoundException extends Exception {}
}
