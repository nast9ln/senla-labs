package com.example.demo.service.impl;

import com.example.demo.repository.TopParamRepository;
import com.example.demo.service.SchedulingService;
import com.example.demo.service.TopParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
