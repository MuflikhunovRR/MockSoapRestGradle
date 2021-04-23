package ru.gotoqa.mock.soap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gotoqa.mock.entity.CapitalCity;
import ru.gotoqa.mock.entity.CapitalCityResponse;
import ru.gotoqa.mock.entity.CountryCurrency;
import ru.gotoqa.mock.entity.CountryCurrencyResponse;
import ru.gotoqa.mock.root.core.builders.CapitalCityResponseBuilder;
import ru.gotoqa.mock.root.core.builders.CountryCurrencyResponseBuilder;


/**
 * SOAP API Controller /mock-soap
 */
@Slf4j
@Endpoint
public class CountryInfoController {

    public static final String COUNTRY_INFO_NS = "http://www.oorsprong.org/websamples.countryinfo";

    private final CapitalCityResponseBuilder cityResponseBuilder;
    private final CountryCurrencyResponseBuilder currencyResponseBuilder;

    @Autowired
    public CountryInfoController(CapitalCityResponseBuilder cityResponseBuilder,
                                 CountryCurrencyResponseBuilder currencyResponseBuilder) {
        this.cityResponseBuilder = cityResponseBuilder;
        this.currencyResponseBuilder = currencyResponseBuilder;
    }

    @ResponsePayload
    @PayloadRoot(namespace = COUNTRY_INFO_NS, localPart = "CapitalCity")
    public CapitalCityResponse getCapitalCityByISOCode(@RequestPayload CapitalCity input) {
        log.info("CapitalCityResponse received for addition with input " + input);
        return cityResponseBuilder.createCountryCurrencyResponse(input);
    }

    @ResponsePayload
    @PayloadRoot(namespace = COUNTRY_INFO_NS, localPart = "CountryCurrency")
    public CountryCurrencyResponse getCountryCurrencyByISOCode(@RequestPayload CountryCurrency input) {
        log.info("CountryCurrencyResponse received for addition with input " + input);
        return currencyResponseBuilder.createCountryCurrencyResponse(input);
    }

}