package com.troy.parking.order.controller;

import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.R;
import com.troy.parking.order.entity.ParkingRouteEntity;
import com.troy.parking.order.service.ParkingRouteService;
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
@RequestMapping("order/parkingroute")
public class ParkingRouteController {
    @Autowired
    private ParkingRouteService parkingRouteService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:parkingroute:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = parkingRouteService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{routeId}")
    //@RequiresPermissions("order:parkingroute:info")
    public R info(@PathVariable("routeId") String routeId){
		ParkingRouteEntity parkingRoute = parkingRouteService.getById(routeId);

        return R.ok().put("parkingRoute", parkingRoute);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:parkingroute:save")
    public R save(@RequestBody ParkingRouteEntity parkingRoute){
		parkingRouteService.save(parkingRoute);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:parkingroute:update")
    public R update(@RequestBody ParkingRouteEntity parkingRoute){
		parkingRouteService.updateById(parkingRoute);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:parkingroute:delete")
    public R delete(@RequestBody String[] routeIds){
		parkingRouteService.removeByIds(Arrays.asList(routeIds));

        return R.ok();
    }

}
