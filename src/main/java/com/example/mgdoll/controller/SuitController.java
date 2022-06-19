package com.example.mgdoll.controller;

import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.model.MgSuit;
import com.example.mgdoll.service.AccountTokenService;
import com.example.mgdoll.service.SuitService;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/suit")
public class SuitController {
    private Logger logger = LoggerFactory.getLogger(SuitController.class);

    @Autowired
    private SuitService suitService;
    @Autowired
    private AccountTokenService accountTokenService;

    @PostMapping("/insert")
    @ResponseBody
    @CrossOrigin
    public ApiResponse insert(@RequestBody MgSuit mgSuit, HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        accountTokenService.updateToken(request);
        final String token = request.getHeader("access_token");
        String userId = JwtUtil.getUserId(token);
        if(mgSuit != null){
            if(mgSuit.getSuitId() == null){
                mgSuit.setInsertBy(userId);
                suitService.insert(mgSuit);
            }else {
                suitService.update(mgSuit);
            }
        }

        apiResponse = ApiResponseUtil.getApiResponse(mgSuit);
        return apiResponse;
    }
}
