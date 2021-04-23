package ru.gotoqa.mock.root.core.builders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.gotoqa.mock.entity.CapitalCity;
import ru.gotoqa.mock.entity.CapitalCityResponse;
import ru.gotoqa.mock.root.core.storages.CapitalCityRequestStorage;
import ru.gotoqa.mock.root.core.storages.CapitalCityResponseStorage;

@Slf4j
@Component
@RequiredArgsConstructor
public class CapitalCityResponseBuilder {

    private final CapitalCityRequestStorage requestStorage;
    private final CapitalCityResponseStorage responseStorage;

    public CapitalCityResponse createCountryCurrencyResponse(CapitalCity capitalCityRequest) {
        log.info("Start generate CapitalCityResponse");
        String isoCode = capitalCityRequest.getSCountryISOCode();
        requestStorage.addRequestToStorage(capitalCityRequest);

        CapitalCityResponse profileResult = new CapitalCityResponse();
        String city = responseStorage.getCity(capitalCityRequest.getSCountryISOCode());
        profileResult.setCapitalCityResult(city);

        if (isoCode.equalsIgnoreCase("EXCEPTION")) {
            throw new RuntimeException("RuntimeException");
        }

        if (city == null) {
            return createDefaultResponse();
        }

        clearRuleAfterOneResponse();
        log.info("Finish generate CapitalCityResponse response");
        return profileResult;
    }

    private void clearRuleAfterOneResponse() {
        if (responseStorage.isNeedToClean()) {
            responseStorage.clearResponseStorage();
        }
    }

    private CapitalCityResponse createDefaultResponse() {
        log.info("Start generate Default - UpdateOutMessageStatusResponse");
        CapitalCityResponse capitalCityResponse = new CapitalCityResponse();
        capitalCityResponse.setCapitalCityResult("Roma");
        log.info("Finish generate Default - UpdateOutMessageStatusResponse response");
        return capitalCityResponse;
    }

}