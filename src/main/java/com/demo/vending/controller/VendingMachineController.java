package com.demo.vending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.vending.model.VendingMachineDTO;
import com.demo.vending.service.VendingMachineService;

@RestController
@RequestMapping("/vendingMachine")
public class VendingMachineController {
   
   @Autowired
   private VendingMachineService vendingMachineService;
   
   @CrossOrigin
   @GetMapping("/totalSoda")
   public int getTotalSodaInventory() {
      return vendingMachineService.getTotalSoda();
   }
   
   @CrossOrigin
   @GetMapping("/current")
   public VendingMachineDTO getCurrentVendingMachineDTO() {
      return vendingMachineService.getCurrentVendingMachineDTO();
   }
   
   @CrossOrigin
   @GetMapping("/purchaseHistory")
   public List<VendingMachineDTO> getPurchaseHistory(@RequestParam String order) {
      return vendingMachineService.getPurchaseHistory(order);
   }
   
   @CrossOrigin
   @PutMapping("")
   public VendingMachineDTO processRequest(@RequestBody VendingMachineDTO request) {
      return vendingMachineService.processSerive( request );
   }
}
