package ru.gotoqa.mock.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gotoqa.mock.entity.CountryCurrency;
import ru.gotoqa.mock.entity.TCurrency;
import ru.gotoqa.mock.root.core.storages.CountryCurrencyRequestStorage;
import ru.gotoqa.mock.root.core.storages.CountryCurrencyResponseStorage;

import java.util.List;

/**
 * Mock manager settings for CountryCurrency
 */
@RestController
@RequestMapping("/filter/countryCurrency")
@Api(tags = "Mock manager settings for CountryCurrency")
@RequiredArgsConstructor
public class CountryCurrencyFilterController {

    private final CountryCurrencyRequestStorage countryCurrencyRequestStorage;
    private final CountryCurrencyResponseStorage countryCurrencyResponseStorage;

    @ApiOperation(value = "Add new rule CountryCurrencyResponse by countryISOCode")
    @PostMapping("/createRule/{code}")
    public void createRule(@PathVariable("code") String code, @RequestBody TCurrency tCurrency) {
        countryCurrencyResponseStorage.putInStorage(code, tCurrency);
    }

    @ApiOperation(value = "Get all rules for CountryCurrencyResponse")
    @GetMapping("/showAllRulesCountryCurrencyResponse")
    public Object showAllCountryCurrencyResponse() {
        return countryCurrencyResponseStorage.getResponseStorage();
    }

    @ApiOperation(value = "Get all CountryCurrency requests from storage")
    @GetMapping("/showCountryCurrencyRequestStorage")
    public List<CountryCurrency> showCountryCurrencyRqStorage() {
        return countryCurrencyRequestStorage.getRequestList();
    }

    @ApiOperation(value = "Remove rules CountryCurrencyResponse by countryISOCode")
    @DeleteMapping("/clearStorageByCode/{countryISOCode}")
    public void clearStorageByCode(@PathVariable String countryISOCode) {
        countryCurrencyResponseStorage.removeRuleById(countryISOCode);
    }

    @ApiOperation(value = "Clear storage CountryCurrency (rules + requests)")
    @DeleteMapping("/clearStorage")
    public void clearStorage() {
        countryCurrencyRequestStorage.clearRequestStorage();
        countryCurrencyResponseStorage.clearResponseStorage();
    }

    @ApiOperation(value = "Switch for disable auto cleaner functionality (default=true)")
    @GetMapping("/autoClean/{isTurnOff}")
    public void autoCleanTurn(@PathVariable boolean isTurnOff) {
        countryCurrencyResponseStorage.setNeedToClean(isTurnOff);
    }

}