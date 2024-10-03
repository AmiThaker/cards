package com.ami.cards.mapper;

import com.ami.cards.dto.CardsDTO;
import com.ami.cards.entity.Cards;

public class CardsMapper {

    public static Cards mapToCards(CardsDTO cardsDTO,Cards cards){
        cards.setCardNumber(cardsDTO.getCardNumber());
        cards.setCardType(cardsDTO.getCardType());
        cards.setMobileNumber(cardsDTO.getMobileNumber());
        cards.setTotalLimit(cardsDTO.getTotalLimit());
        cards.setAvailableAmount(cardsDTO.getAvailableAmount());
        cards.setAmountUsed(cardsDTO.getAmountUsed());
        return cards;
    }

    public static CardsDTO mapToCardsDTO(Cards cards, CardsDTO cardsDTO){
        cardsDTO.setCardNumber(cards.getCardNumber());
        cardsDTO.setCardType(cards.getCardType());
        cardsDTO.setMobileNumber(cards.getMobileNumber());
        cardsDTO.setTotalLimit(cards.getTotalLimit());
        cardsDTO.setAvailableAmount(cards.getAvailableAmount());
        cardsDTO.setAmountUsed(cards.getAmountUsed());
        return cardsDTO;
    }
}
