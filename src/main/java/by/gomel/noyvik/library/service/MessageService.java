package by.gomel.noyvik.library.service;

import by.gomel.noyvik.library.model.Message;

import java.util.List;

public interface MessageService {


    List<Message> findAll();

    void save(Message message);

    List<Message> findAllByUserId(Long userId);
}
