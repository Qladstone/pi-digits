package com.qladstone.pidigits;

import com.qladstone.pidigits.dao.DigitDAO;
import com.qladstone.pidigits.representation.DigitOfPi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;

@RestController
public class Controller {

	private final DigitDAO digitDAO;

	@Inject
	public Controller (DigitDAO digitDAO) {
		this.digitDAO = digitDAO;
	}

	@RequestMapping("/")
	public String index() {
		return "PI Digits is running.";
	}

	@GetMapping("/digits/{position}")
	public DigitOfPi getDigit(@PathVariable("position") long position) {
		if (position < 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"position must be non-negative! Given: " + position);
		}
		try {
			return digitDAO.retrieveDigit(position);
		} catch (DigitDAO.DigitOfPiNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Digit not available.");
		}
	}
}
