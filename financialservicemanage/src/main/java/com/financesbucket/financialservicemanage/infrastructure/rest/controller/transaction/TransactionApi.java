package com.financesbucket.financialservicemanage.infrastructure.rest.controller.transaction;


import com.financesbucket.financialservicemanage.application.transaction.transactionservices.TransactionService;
import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.domain.transaction.transactionmodel.Transaction;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.transaction.helper.TransactionCreationHelper;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.transaction.request.TransactionRequest;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.transaction.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionApi {
    private Transaction transaction;
    private TransactionResponse<Transaction> transactionResponse;
    private final TransactionService transactionService;
    private final TransactionCreationHelper transactionCreationHelper;

    @PostMapping("/createtransaction")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        transactionResponse = new TransactionResponse<>();
        try {
            transaction = transactionService.createTransaction(transactionCreationHelper.convertRequestToTransaction(transactionRequest));
            transactionResponse = transactionCreationHelper.createResponse(transaction);
            return new ResponseEntity(transactionResponse, HttpStatus.OK);
        } catch (BussinessException exc) {
            transactionResponse = new TransactionResponse<>();
            transactionResponse.setStateMessage(transactionCreationHelper.getStateMessages(exc));
            return new ResponseEntity<>(transactionResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
