package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryDao.class);

    private static List<Country> COUNTRY_LIST;

    public CountryDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        COUNTRY_LIST = (List<Country>) context.getBean("countryList");
        LOGGER.debug("Country List: {}", COUNTRY_LIST);
        ((ClassPathXmlApplicationContext) context).close();
    }

    public List<Country> getAllCountries() {
        return COUNTRY_LIST;
    }
}
