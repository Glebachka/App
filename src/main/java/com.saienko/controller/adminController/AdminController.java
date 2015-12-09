package com.saienko.controller.adminController;

import com.saienko.model.Bank;
import com.saienko.service.bankService.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
     *
     * @return
     */
    @RequestMapping(value = {"/bank"}, method = RequestMethod.GET)
    public ResponseEntity<List<Bank>> getAllBanks() {
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

    /**
     * Add  new Bank
     *
     * @param bank
     * @param builder
     * @return
     */
    @RequestMapping(value = {"/bank/"}, method = RequestMethod.POST)
    public ResponseEntity<Void> newBank(Bank bank, UriComponentsBuilder builder) {

        bankService.saveBank(bank);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/bank/{bankId}").buildAndExpand(bank.getBankId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * Get one Bank.
     *
     * @param bankId
     * @return
     */
    @RequestMapping(value = {"/bank/{bankId}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bank> findBank(@PathVariable("bankId") Integer bankId) {
        Bank bank = bankService.findBankById(bankId);
        return new ResponseEntity<Bank>(bank, HttpStatus.OK);
    }


    /**
     * Delete bank
     *
     * @param bankId
     */
    @RequestMapping(value = {"/bank/{bankId}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Bank> deleteBank(@PathVariable("bankId") Integer bankId) {
        Bank bankTodDelete = bankService.findBankById(bankId);
        if (bankTodDelete == null) {
            return new ResponseEntity<Bank>(HttpStatus.NOT_FOUND);
        }
        bankService.deleteBank(bankId);
        return new ResponseEntity<Bank>(HttpStatus.NO_CONTENT);
    }


}

