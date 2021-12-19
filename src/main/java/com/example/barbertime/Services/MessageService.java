package com.example.barbertime.Services;

import com.example.barbertime.Entities.Message;
import com.example.barbertime.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;


    public List<Message> getMessages()
    {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessage(String id)
    {
        long message_id = Long.parseLong(id);

        return messageRepository.findById(message_id);
    }

    public ResponseEntity<?> saveMessage(Message message)
    {
        return ResponseEntity.ok().body(messageRepository.save(message));
    }
}
