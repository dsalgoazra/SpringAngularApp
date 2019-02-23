package com.azra.demo.controller;

import com.azra.demo.domain.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api")
public class BaseController<T> {
    Logger logger = LoggerFactory.getLogger(BaseController.class);

    public ServerResponse handleSuccessResponse( ){

        return new ServerResponse(true);
    }

    public ServerResponse handleSuccessResponse(T result){
        ServerResponse<T> res = new ServerResponse<>(true);
        res.setResult(result);
        return res;
    }

    public ServerResponse handleFailedResponse(){
        return new ServerResponse(false);
    }
}
