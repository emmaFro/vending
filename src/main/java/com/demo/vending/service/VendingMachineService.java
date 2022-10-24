package com.demo.vending.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.vending.model.VendingMachineDTO;
import com.demo.vending.model.enums.Action;
import com.demo.vending.model.enums.Status;

@Service
public class VendingMachineService {

   private int totalSoda = 3;
   private Status currentStatus = Status.INITIAL;

   private boolean hasQuarter = false;
   private List<VendingMachineDTO> purchaseHistory = new ArrayList<VendingMachineDTO>();
   private VendingMachineDTO vendingMachineDTO = new VendingMachineDTO();
   
   public int getTotalSoda() {
      return totalSoda;
   }
   
   public VendingMachineDTO getCurrentVendingMachineDTO()
   {
	   vendingMachineDTO.setTotalSoda(totalSoda);
	   vendingMachineDTO.setStatus(currentStatus);
	   return vendingMachineDTO;
	   
   }
   public List<VendingMachineDTO> getPurchaseHistory(String order){
      if ("ASC".equalsIgnoreCase( order )) {
         purchaseHistory.sort(Comparator.comparing(VendingMachineDTO::getPurchaseDateTime));
      } else {
         purchaseHistory.sort(Comparator.comparing(VendingMachineDTO::getPurchaseDateTime).reversed());
      }
      return purchaseHistory;
   }

   public VendingMachineDTO processSerive( VendingMachineDTO input ) {

      var output = new VendingMachineDTO();
      
      if (totalSoda <=0) {
         output.setStatus( Status.OUT_OF_SODA );
         return output;
      }
      if ( input != null ) {
         if ( Action.INSERT_QUARTER.equals( input.getAction() ) ) {
            hasQuarter = true;
            output.setStatus( Status.QUARTER_INSERTED );
            currentStatus = Status.QUARTER_INSERTED;
         } else if ( Action.EJECTS_QUARTER.equals( input.getAction() )  ) {
            if (hasQuarter) {
            hasQuarter = false;
            output.setStatus( Status.QUARTER_EJECTED );
            currentStatus = Status.QUARTER_EJECTED;
            } else {
               output.setStatus( Status.ERROR );
            }
         } else if ( Action.BUY_SODA.equals( input.getAction() ) ) {
            if (Status.QUARTER_INSERTED.equals( currentStatus ) ) {
            if ( totalSoda > 0 ) {
               totalSoda--;
               hasQuarter = false;
				if (totalSoda == 0) {
					output.setStatus(Status.OUT_OF_SODA);
					currentStatus = Status.OUT_OF_SODA;
				} else {
					output.setStatus(Status.SODA_READY);
					currentStatus = Status.SODA_READY;
				}
               output.setPurchaseDateTime( LocalDateTime.now());
               
               purchaseHistory.add( output );
            } else {
            }}
            else {
               output.setStatus( Status.ERROR );
            }
         }
      }
      
      output.setTotalSoda( totalSoda );

      return output;
   }

}
