package com.financesbucket.financialservicemanage.infrastructure.rest.controller.transfer.helper;


import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.domain.transfer.transfermodel.Transfer;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.controllermessage.StateMessage;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.transfer.request.TransferRequest;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.transfer.response.TransferResponse;
import org.springframework.stereotype.Service;


@Service
public class TransferCreationHelper {
    public Transfer convertRequestToTransfer(TransferRequest request) {
        // validar si el requeste es nulo
        var transfer = Transfer.builder()
                .idCuentaEnvio(request.getIdCuentaEnvio())
                .idCuentaDestino(request.getIdCuentaRecepcion())
                .monto(request.getMonto())
                .descripcion(request.getDescripcion())
                .build();
        transfer.validTransfer();
        return transfer;
    }
    public StateMessage getStateMessages(BussinessException exc) {
        return StateMessage.builder()
                .status(exc.getStatus())
                .detail(exc.getDetail())
                .statusCode(Integer.parseInt(exc.getCode()))
                .build();
    }
    public TransferResponse<Transfer> createResponse(Transfer transfer) {
        TransferResponse<Transfer> transferResponse = new TransferResponse<>();
        StateMessage stateMessage = StateMessage.builder()
                .statusCode(200)
                .status("OK")
                .title("Success")
                .detail("Transfer Operation completed successfully")
                .build();
        transferResponse.setTransfer(transfer);
        transferResponse.setStateMessage(stateMessage);
        return transferResponse;
    }
}
