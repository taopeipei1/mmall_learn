package com.mmall.service;

import com.mmall.common.ServiceResponse;

public interface IOrderService {
    public ServiceResponse pay(Long orderNo, Integer userId, String path);
}
