package com.mizu20040814.accountingsoftware.transaction;

import com.mizu20040814.accountingsoftware.transaction.dto.TransactionRequest;
import com.mizu20040814.accountingsoftware.transaction.dto.TransactionResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionResponse> getAll(){
        return transactionService.findAll()
                .stream()
                .map(TransactionResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public TransactionResponse getById(@PathVariable Long id){
        return TransactionResponse.from(transactionService.findById(id));
    }

    @PostMapping
    public TransactionResponse create(@RequestBody TransactionRequest transactionRequest){
        return TransactionResponse.from(transactionService.create(transactionRequest));
    }


}
