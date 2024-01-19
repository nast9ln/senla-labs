package ru.labs.coffer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.labs.coffer.service.SchedulingService;
import ru.labs.coffer.service.TopParamService;

@Service
@RequiredArgsConstructor
public class SchedulingServiceImpl implements SchedulingService {
    private final TopParamService topParamService;

    @Override
    @Scheduled(fixedRate = 60000)
    public void updateIsTopStatus() {
        topParamService.updateIsTop();
    }
}
