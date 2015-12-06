package com.saienko.controller.adminController;

import com.saienko.model.Bank;
import com.saienko.service.bankService.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller provides work with REST api and controlls methods for banks.
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    BankService bankService;


    /**
     * This method returns all banks.
     * @return
     */
    @RequestMapping(value = {"/bank"}, method = RequestMethod.GET)
    public ResponseEntity<List<Bank>> getAllBanks(){
        List<Bank> banks = bankService.findAllBanks();
        return new ResponseEntity<List<Bank>>(banks, HttpStatus.OK);
    }

    //This is JSON view for the Banks. It uses jsonClasses
    //    @JsonView(Views.Public.class)
//    @RequestMapping(value = {"/bank/"}, method = RequestMethod.GET)
//    public JsonResponseBody getBanks(){
//        JsonResponseBody json = new JsonResponseBody();
//        List<Bank> list = bankService.findAllBanks();
//        json.setResult(list);
//        json.setCode("200");
//        json.setMsg("OK");
//        return json;
//    }


}
