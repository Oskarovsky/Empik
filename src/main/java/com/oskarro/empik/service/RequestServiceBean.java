package com.oskarro.empik.service;

import com.oskarro.empik.model.Request;
import com.oskarro.empik.repository.RequestRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class RequestServiceBean implements RequestService {

    RequestRepo requestRepo;

    public RequestServiceBean(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    public void upsertRequest(final String login) {
        Optional<Request> request = requestRepo.findById(login);
        if (request.isPresent()) {
            requestRepo.incrementValue(login);
        } else {
            requestRepo.save(Request.builder().counter(1).login(login).build());
        }
    }

}
