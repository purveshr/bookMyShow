package com.backendlld.bookmyshowjan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private Double amount;
    private Date paymentDate;
    @Enumerated(value = EnumType.STRING)
    private PaymentGateway paymentGateway;
    @Enumerated(value = EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    @Enumerated(value = EnumType.STRING)
    private PaymentMode paymentMode;
    private String refNumber;
}
