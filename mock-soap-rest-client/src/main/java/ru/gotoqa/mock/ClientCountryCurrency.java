package ru.gotoqa.mock;


import org.springframework.web.client.RestTemplate;
import ru.gotoqa.mock.entity.CapitalCityResponse;
import ru.gotoqa.mock.entity.CountryCurrencyResponse;

/**
 * Клиент для настройки заглушки ответов CapitalCityResponse
 */
public class ClientCountryCurrency {

    private String mockServer;
    private final RestTemplate restTemplate;

    public ClientCountryCurrency(String mockServerUrl, RestTemplate restTemplate) {
        this.mockServer = mockServerUrl;
        if (!this.mockServer.endsWith("/")) {
            this.mockServer += "/";
        }
        this.restTemplate = restTemplate;
    }

    public void createRule(String login, CountryCurrencyResponse response) {
        restTemplate.postForLocation(mockServer + "countryCurrency/createRule/" +login, response);
    }

    public void clearStorageByCode(String countryISOCode) {
        String url = mockServer + "countryCurrency/clearStorageByCode/" + countryISOCode;
        restTemplate.delete(url, Boolean.class);
    }

    public void clearStorage() {
        String url = mockServer + "countryCurrency/clearStorage";
        restTemplate.delete(url, Boolean.class);
    }
}
