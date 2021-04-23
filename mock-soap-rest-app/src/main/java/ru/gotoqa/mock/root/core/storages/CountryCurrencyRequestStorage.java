package ru.gotoqa.mock.root.core.storages;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.gotoqa.mock.entity.CountryCurrency;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CountryCurrencyRequestStorage {
    /**
     * Storage for CountryCurrency request
     */
    @Getter
    @Setter
    private List<CountryCurrency> requestList = new ArrayList<>();

    public void addRequestToStorage(CountryCurrency request) {
        requestList.add(request);
    }

    public void clearRequestStorage() {
        requestList.clear();
        log.info("Clear all CountryCurrency filters");
    }

}