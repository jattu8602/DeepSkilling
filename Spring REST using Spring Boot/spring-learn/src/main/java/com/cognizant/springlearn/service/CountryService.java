package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.CountryDao;
import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryDao countryDao;

    public Country getCountry(String code) {
        LOGGER.debug("Start");
        List<Country> countries = countryDao.getAllCountries();
        Country matched = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
        LOGGER.debug("End");
        if (matched == null) {
            throw new CountryNotFoundException("Country not found for code: " + code);
        }
        return matched;
    }

    public List<Country> getAllCountries() {
        return countryDao.getAllCountries();
    }
}
