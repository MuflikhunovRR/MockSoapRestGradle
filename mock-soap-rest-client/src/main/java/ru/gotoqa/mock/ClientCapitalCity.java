package ru.gotoqa.mock;


import org.springframework.web.client.RestTemplate;
import ru.gotoqa.mock.entity.CapitalCityResponse;

/**
 * Клиент для настройки заглушки ответов CapitalCityResponse
 */
public class ClientCapitalCity {

    private String mockServer;
    private final RestTemplate restTemplate;

    public ClientCapitalCity(String mockServerUrl, RestTemplate restTemplate) {
        this.mockServer = mockServerUrl;
        if (!this.mockServer.endsWith("/")) {
            this.mockServer += "/";
        }
        this.restTemplate = restTemplate;
    }

    public void createRule(String login, CapitalCityResponse response) {
        restTemplate.postForLocation(mockServer + "capitalCity/createRule/" +login, response);
    }

    public void clearStorageByCode(String countryISOCode) {
        String url = mockServer + "capitalCity/clearStorageByCode/" + countryISOCode;
        restTemplate.delete(url, Boolean.class);
    }

    public void clearStorage() {
        String url = mockServer + "capitalCity/clearStorage";
        restTemplate.delete(url, Boolean.class);
    }
}
