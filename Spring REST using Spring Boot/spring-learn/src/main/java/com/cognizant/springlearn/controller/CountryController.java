package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @GetMapping("/country")
    public Country getCountryIndia() {
        LOGGER.debug("Start");
        return countryService.getCountry("IN");
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.debug("Start");
        return countryService.getAllCountries();
    }

    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.debug("Start");
        return countryService.getCountry(code);
    }

    @PostMapping("/countries")
    public Country addCountry(@RequestBody @Valid Country country) {
        LOGGER.debug("Start");
        LOGGER.debug("Country: {}", country);
        LOGGER.debug("End");
        return country;
    }

    @PutMapping("/countries")
    public Country updateCountry(@RequestBody @Valid Country country) {
        LOGGER.debug("Start");
        LOGGER.debug("Country: {}", country);
        LOGGER.debug("End");
        return country;
    }

    @DeleteMapping("/countries/{code}")
    public void deleteCountry(@PathVariable String code) {
        LOGGER.debug("Start - Deleting country: {}", code);
        LOGGER.debug("End");
    }
}
