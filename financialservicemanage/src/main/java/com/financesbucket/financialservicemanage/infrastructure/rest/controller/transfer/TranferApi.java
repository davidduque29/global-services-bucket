package com.financesbucket.financialservicemanage.infrastructure.rest.controller.transfer;



import com.financesbucket.financialservicemanage.application.transfer.transactionservices.TransferService;
import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.domain.transfer.transfermodel.Transfer;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.transfer.helper.TransferCreationHelper;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.transfer.request.TransferRequest;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.transfer.response.TransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TranferApi {
    private Transfer transfer;
    private TransferResponse<Transfer> transferResponse;
    private final TransferService transferService;
    private final TransferCreationHelper transferCreationHelper;

    @PostMapping("/createtransfer")
    public ResponseEntity<TransferResponse> createTransfer(@RequestBody TransferRequest transferRequest) {
        transferResponse = new TransferResponse<>();
        try {
            transfer = transferService.createTransfer(transferCreationHelper.convertRequestToTransfer(transferRequest));
            transferResponse = transferCreationHelper.createResponse(transfer);
            return new ResponseEntity(transferResponse, HttpStatus.OK);
        } catch (BussinessException exc) {
            transferResponse = new TransferResponse<>();
            transferResponse.setStateMessage(transferCreationHelper.getStateMessages(exc));
            return new ResponseEntity<>(transferResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
