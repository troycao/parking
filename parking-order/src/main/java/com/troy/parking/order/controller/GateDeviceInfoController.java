package com.troy.parking.order.controller;

import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.R;
import com.troy.parking.order.entity.GateDeviceInfoEntity;
import com.troy.parking.order.service.GateDeviceInfoService;
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
@RequestMapping("order/gatedeviceinfo")
public class GateDeviceInfoController {
    @Autowired
    private GateDeviceInfoService gateDeviceInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:gatedeviceinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gateDeviceInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{serialNo}")
    //@RequiresPermissions("order:gatedeviceinfo:info")
    public R info(@PathVariable("serialNo") String serialNo){
		GateDeviceInfoEntity gateDeviceInfo = gateDeviceInfoService.getById(serialNo);

        return R.ok().put("gateDeviceInfo", gateDeviceInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:gatedeviceinfo:save")
    public R save(@RequestBody GateDeviceInfoEntity gateDeviceInfo){
		gateDeviceInfoService.save(gateDeviceInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:gatedeviceinfo:update")
    public R update(@RequestBody GateDeviceInfoEntity gateDeviceInfo){
		gateDeviceInfoService.updateById(gateDeviceInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:gatedeviceinfo:delete")
    public R delete(@RequestBody String[] serialNos){
		gateDeviceInfoService.removeByIds(Arrays.asList(serialNos));

        return R.ok();
    }

}
