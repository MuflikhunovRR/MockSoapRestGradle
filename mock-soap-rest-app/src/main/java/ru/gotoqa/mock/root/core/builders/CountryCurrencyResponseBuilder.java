package ru.gotoqa.mock.root.core.builders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.gotoqa.mock.entity.CountryCurrency;
import ru.gotoqa.mock.entity.CountryCurrencyResponse;
import ru.gotoqa.mock.entity.TCurrency;
import ru.gotoqa.mock.root.core.storages.CountryCurrencyRequestStorage;
import ru.gotoqa.mock.root.core.storages.CountryCurrencyResponseStorage;

@Slf4j
@Component
@RequiredArgsConstructor
public class CountryCurrencyResponseBuilder {

    private final CountryCurrencyRequestStorage requestStorage;
    private final CountryCurrencyResponseStorage responseStorage;

    public CountryCurrencyResponse createCountryCurrencyResponse(CountryCurrency countryCurrency) {
        log.info("Start generate CountryCurrencyResponse");
        String isoCode = countryCurrency.getSCountryISOCode();
        requestStorage.addRequestToStorage(countryCurrency);

        CountryCurrencyResponse profileResult = new CountryCurrencyResponse();
        TCurrency tCurrency = responseStorage.getResponseStorage().get(countryCurrency.getSCountryISOCode());
        profileResult.setCountryCurrencyResult(tCurrency);

        if (isoCode.equalsIgnoreCase("EXCEPTION")) {
            throw new RuntimeException("RuntimeException");
        }

        if (tCurrency == null) {
            return createDefaultResponse();
        }

        clearRuleAfterOneResponse();
        log.info("Finish generate CountryCurrencyResponse response");
        return profileResult;
    }

    private void clearRuleAfterOneResponse() {
        if (responseStorage.isNeedToClean()) {
            responseStorage.clearResponseStorage();
        }
    }

    private CountryCurrencyResponse createDefaultResponse() {
        log.info("Start generate Default - UpdateOutMessageStatusResponse");
        CountryCurrencyResponse countryCurrencyResponse = new CountryCurrencyResponse();
        TCurrency tCurrency = new TCurrency();
        tCurrency.setSName("Tugrik");
        tCurrency.setSISOCode("777");
        countryCurrencyResponse.setCountryCurrencyResult(tCurrency);
        log.info("Finish generate Default - UpdateOutMessageStatusResponse response");
        return countryCurrencyResponse;
    }

}