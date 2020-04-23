package com.troy.parking.order.controller;

import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.R;
import com.troy.parking.order.entity.PaymentOrderEntity;
import com.troy.parking.order.service.PaymentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author troy
 * @email troy@gmail.com
 * @date 2020-04-23 14:59:53
 */
@RestController
@RequestMapping("order/paymentorder")
public class PaymentOrderController {
    @Autowired
    private PaymentOrderService paymentOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:paymentorder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paymentOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:paymentorder:info")
    public R info(@PathVariable("id") String id){
		PaymentOrderEntity paymentOrder = paymentOrderService.getById(id);

        return R.ok().put("paymentOrder", paymentOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:paymentorder:save")
    public R save(@RequestBody PaymentOrderEntity paymentOrder){
		paymentOrderService.save(paymentOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:paymentorder:update")
    public R update(@RequestBody PaymentOrderEntity paymentOrder){
		paymentOrderService.updateById(paymentOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:paymentorder:delete")
    public R delete(@RequestBody String[] ids){
		paymentOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
