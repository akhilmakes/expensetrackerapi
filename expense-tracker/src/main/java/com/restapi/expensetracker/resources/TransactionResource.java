package com.restapi.expensetracker.resources;

import com.restapi.expensetracker.domain.Transaction;
import com.restapi.expensetracker.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionResource {

    @Autowired
    TransactionService transactionService;

    @PostMapping("")
    ResponseEntity<Transaction> addTransaction(HttpServletRequest request,
                                               @PathVariable("categoryId") Integer categoryId,
                                               @RequestBody Map<String, Object> transactionMap){
        int userId = (Integer) request.getAttribute("userId");

        Double amount = Double.valueOf(transactionMap.get("amount").toString());
        String note = (String) transactionMap.get("note");
        Long transactionDate = (Long)transactionMap.get("transactionDate");

        Transaction transaction = transactionService.createTransaction(userId, categoryId, amount, note,transactionDate);

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);

    }

}
