package com.troy.parking.order.controller;

import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.R;
import com.troy.parking.order.entity.ParkingOrderEntity;
import com.troy.parking.order.service.ParkingOrderService;
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
@RequestMapping("order/parkingorder")
public class ParkingOrderController {
    @Autowired
    private ParkingOrderService parkingOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:parkingorder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = parkingOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:parkingorder:info")
    public R info(@PathVariable("id") String id){
		ParkingOrderEntity parkingOrder = parkingOrderService.getById(id);

        return R.ok().put("parkingOrder", parkingOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:parkingorder:save")
    public R save(@RequestBody ParkingOrderEntity parkingOrder){
		parkingOrderService.save(parkingOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:parkingorder:update")
    public R update(@RequestBody ParkingOrderEntity parkingOrder){
		parkingOrderService.updateById(parkingOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:parkingorder:delete")
    public R delete(@RequestBody String[] ids){
		parkingOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
