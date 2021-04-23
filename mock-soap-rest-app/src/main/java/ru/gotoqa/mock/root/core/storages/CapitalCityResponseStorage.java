package ru.gotoqa.mock.root.core.storages;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CapitalCityResponseStorage {

    @Getter
    private final Map<String, String> responseStorage = new HashMap<>();

    @Getter
    @Setter
    private boolean isNeedToClean = true; //flag for auto clear function (default=true)

    public void putInStorage(String countryISOCode, String capitalCity) {
        responseStorage.put(countryISOCode, capitalCity);
        log.info("Add filter {} success", capitalCity);
    }

    public String getCity(String isoCode) {
        log.info("Get filter by ISOCode={}", isoCode);
        return responseStorage.get(isoCode);
    }

    public void clearResponseStorage() {
        responseStorage.clear();
        log.info("Clear all filters");
    }

    public void removeRuleById(String isoCode) {
        responseStorage.remove(isoCode);
        log.info("Clear filters by countryISOCode");
    }
}
