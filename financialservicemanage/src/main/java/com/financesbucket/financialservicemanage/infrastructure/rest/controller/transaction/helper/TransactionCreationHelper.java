package com.financesbucket.financialservicemanage.infrastructure.rest.controller.transaction.helper;


import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.domain.transaction.transactionmodel.Transaction;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.controllermessage.StateMessage;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.transaction.request.TransactionRequest;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.transaction.response.TransactionResponse;
import org.springframework.stereotype.Service;


@Service
public class TransactionCreationHelper {
    private Transaction transaction;
    private StateMessage stateMessage;
    private TransactionResponse transactionResponse;

    public Transaction convertRequestToTransaction(TransactionRequest request) {
        // validar si el requeste es nulo - pendiente
        transaction = Transaction.builder()
                .tipo(request.getTipo())
                .monto(request.getMonto())
                .fechaTransaccion(request.getFechaTransaccion())
                .cuentaId(request.getCuentaId())
                .build();
        transaction.validTransaction();
        return transaction;
    }

    public StateMessage getStateMessages(BussinessException exc) {
        return StateMessage.builder()
                .status(exc.getStatus())
                .detail(exc.getDetail())
                .statusCode(Integer.parseInt(exc.getCode()))
                .build();
    }

    //crear el productResponse de exito que va a devolver en el try
    public TransactionResponse createResponse(Transaction transaction ) {
        transactionResponse = new TransactionResponse<>();
        stateMessage = StateMessage.builder()
                .statusCode(200)
                .status("OK")
                .title("Success")
                .detail("Operation completed successfully")
                .build();
        transactionResponse.setTransaction(transaction);
        transactionResponse.setStateMessage(stateMessage);
        return transactionResponse;
    }
}
