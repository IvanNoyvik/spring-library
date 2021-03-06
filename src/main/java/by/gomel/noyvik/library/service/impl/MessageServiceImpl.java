package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.model.Message;
import by.gomel.noyvik.library.persistence.repository.MessageRepository;
import by.gomel.noyvik.library.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;


    @Override
    public List<Message> findAll() {
        return messageRepository.findAllWithUserLink();
    }

    @Override
    public void save(Message message) {

        message.setDateSent(LocalDate.now());

        messageRepository.save(message);
    }

    @Override
    public List<Message> findAllByUserId(Long userId) {
        return messageRepository.findAllByUserId(userId);
    }
}
