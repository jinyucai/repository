package com.atguigu.gmall.user.controller;

import com.atguigu.gmall.user.bean.UmsMemberReceiveAddress;
import com.atguigu.gmall.user.service.UmsMemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/umsMember")
public class UmsMemberReceiveAddressController {

    @Autowired
    private UmsMemberReceiveAddressService umsMemberReceiveAddressService;

    @RequestMapping("/detail/{memberId}")
    public List<UmsMemberReceiveAddress> getDetail(@PathVariable String memberId){
        return umsMemberReceiveAddressService.getDetail(memberId);
    }
}
