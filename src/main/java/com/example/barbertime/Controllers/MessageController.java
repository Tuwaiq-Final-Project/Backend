package com.example.barbertime.Controllers;

import com.example.barbertime.Entities.Message;
import com.example.barbertime.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Message> getMessages()
    {
        return messageService.getMessages();
    }

    @GetMapping("/{id}")
    public Optional<Message> getMessage(@PathVariable String id)
    {
        return messageService.getMessage(id);
    }

    @PostMapping
    public ResponseEntity<?> saveMessage(@Valid  @RequestBody Message message)
    {
        return messageService.saveMessage(message);
    }
}
