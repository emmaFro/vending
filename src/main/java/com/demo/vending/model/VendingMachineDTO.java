package com.demo.vending.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.demo.vending.model.enums.Action;
import com.demo.vending.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class VendingMachineDTO implements Serializable{
   
   private static final long serialVersionUID = 1L;
   private Action action;
   private int totalSoda;
   private Status status;
   private LocalDateTime purchaseDateTime;
   
   
   
}
