package com.troy.parking.order.controller;

import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.R;
import com.troy.parking.order.entity.PaymentChannelEntity;
import com.troy.parking.order.service.PaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author troy
 * @email troy@gmail.com
 * @date 2020-04-23 14:59:54
 */
@RestController
@RequestMapping("order/paymentchannel")
public class PaymentChannelController {
    @Autowired
    private PaymentChannelService paymentChannelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:paymentchannel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paymentChannelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:paymentchannel:info")
    public R info(@PathVariable("id") String id){
		PaymentChannelEntity paymentChannel = paymentChannelService.getById(id);

        return R.ok().put("paymentChannel", paymentChannel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:paymentchannel:save")
    public R save(@RequestBody PaymentChannelEntity paymentChannel){
		paymentChannelService.save(paymentChannel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:paymentchannel:update")
    public R update(@RequestBody PaymentChannelEntity paymentChannel){
		paymentChannelService.updateById(paymentChannel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:paymentchannel:delete")
    public R delete(@RequestBody String[] ids){
		paymentChannelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
