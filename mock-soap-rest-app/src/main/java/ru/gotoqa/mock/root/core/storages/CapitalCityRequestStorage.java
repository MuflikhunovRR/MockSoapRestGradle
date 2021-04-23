package ru.gotoqa.mock.root.core.storages;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.gotoqa.mock.entity.CapitalCity;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CapitalCityRequestStorage {
    /**
     * Storage for CapitalCity request
     */
    @Getter
    @Setter
    private List<CapitalCity> requestList = new ArrayList<>();

    public void addRequestToStorage(CapitalCity request) {
        requestList.add(request);
    }

    public void clearRequestStorage() {
        requestList.clear();
        log.info("Clear all CapitalCity filters");
    }

}