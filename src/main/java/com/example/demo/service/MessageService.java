package com.example.demo.service;

import com.example.demo.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {
    Page<MessageDto> getDialog(Long advertisementId, Pageable pageable);

    void sendMessage(MessageDto message);
}
