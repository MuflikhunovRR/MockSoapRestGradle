package ru.gotoqa.mock.root.core.storages;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.gotoqa.mock.entity.TCurrency;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CountryCurrencyResponseStorage {

    @Getter
    private final Map<String, TCurrency> responseStorage = new HashMap<>();

    @Getter
    @Setter
    private boolean isNeedToClean = true; //flag for auto clear function (default=true)

    public void putInStorage(String countryISOCode, TCurrency tCurrency) {
        responseStorage.put(countryISOCode, tCurrency);
        log.info("Add CountryCurrency filter {} success", tCurrency);
    }

    public TCurrency getCountryCurrency(String isoCode) {
        log.info("Get CountryCurrency filter by ISOCode={}", isoCode);
        return responseStorage.get(isoCode);
    }

    public void clearResponseStorage() {
        responseStorage.clear();
        log.info("Clear all filters");
    }

    public void removeRuleById(String isoCode) {
        responseStorage.remove(isoCode);
        log.info("Clear CountryCurrency filters by countryISOCode");
    }
}
