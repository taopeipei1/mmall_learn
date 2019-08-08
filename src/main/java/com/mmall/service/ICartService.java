package com.mmall.service;

import com.mmall.common.ServiceResponse;
import com.mmall.vo.CartVo;
import org.springframework.stereotype.Service;


public interface ICartService {
    ServiceResponse<CartVo> add(Integer userId, Integer profuctId, Integer count);
    ServiceResponse<CartVo> update(Integer userId, Integer profuctId, Integer count);
    ServiceResponse<CartVo> deleteProduct(Integer userId,String productIds);
    ServiceResponse<CartVo> list(Integer userId);
    ServiceResponse<CartVo> selectOrUnSelectAll(Integer userId,Integer checked,Integer producrId);
    ServiceResponse<Integer> getCartProductCount(Integer userId);

}
