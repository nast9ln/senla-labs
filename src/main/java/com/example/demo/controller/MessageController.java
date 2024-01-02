package com.example.demo.controller;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.MessageDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.service.MessageService;
import com.example.demo.service.impl.MessageServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface MessageController {
    Page<MessageDto> getDialog(Long personId, Long advertisementId, Pageable pageable);
    void sendMessage(MessageDto messageDto);
}
