package com.ami.cards.service;

import com.ami.cards.dto.CardsDTO;

public interface CardsService {
    void createCard(String mobileNumber);
    CardsDTO fetchCard(String mobileNumber);
    boolean updateCard(CardsDTO cardsDTO);
    boolean deleteCard(String mobileNumber);
}
