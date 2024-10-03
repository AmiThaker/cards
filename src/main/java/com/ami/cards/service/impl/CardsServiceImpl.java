package com.ami.cards.service.impl;

import com.ami.cards.constants.CardsConstants;
import com.ami.cards.dto.CardsDTO;
import com.ami.cards.entity.Cards;
import com.ami.cards.exception.CardAlreadyExistsException;
import com.ami.cards.exception.ResourceNotFoundException;
import com.ami.cards.mapper.CardsMapper;
import com.ami.cards.repository.CardsRepository;
import com.ami.cards.service.CardsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardsServiceImpl implements CardsService {

    private CardsRepository cardsRepository;

    public CardsServiceImpl(CardsRepository cardsRepository){
        this.cardsRepository=cardsRepository;
    }

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> existingCard=cardsRepository.findByMobileNumber(mobileNumber);
        if(existingCard.isPresent()){
            throw new CardAlreadyExistsException("Card Already Registered With Given Mobile Number : "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    public Cards createNewCard(String mobileNumber){
        Cards newCard=new Cards();
        Long randomCardNumber=1000000000L + (long)(Math.random() * 9000000000L);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardsDTO fetchCard(String mobileNumber) {
        Cards cards=cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Card","cardNumber",mobileNumber));
        return CardsMapper.mapToCardsDTO(cards,new CardsDTO());
    }

    @Override
    public boolean updateCard(CardsDTO cardsDTO) {
        Cards cards=cardsRepository.findByCardNumber(cardsDTO.getCardNumber())
                .orElseThrow(()->new ResourceNotFoundException("Card","cardNumber",cardsDTO.getCardNumber()));
        CardsMapper.mapToCards(cardsDTO,cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards=cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Card","cardNumber",mobileNumber));
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
