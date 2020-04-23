package com.troy.parking.order.controller;

import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.R;
import com.troy.parking.order.entity.BoxErrorHistoryEntity;
import com.troy.parking.order.service.BoxErrorHistoryService;
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
@RequestMapping("order/boxerrorhistory")
public class BoxErrorHistoryController {
    @Autowired
    private BoxErrorHistoryService boxErrorHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:boxerrorhistory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = boxErrorHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{parkingName}")
    //@RequiresPermissions("order:boxerrorhistory:info")
    public R info(@PathVariable("parkingName") String parkingName){
		BoxErrorHistoryEntity boxErrorHistory = boxErrorHistoryService.getById(parkingName);

        return R.ok().put("boxErrorHistory", boxErrorHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:boxerrorhistory:save")
    public R save(@RequestBody BoxErrorHistoryEntity boxErrorHistory){
		boxErrorHistoryService.save(boxErrorHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:boxerrorhistory:update")
    public R update(@RequestBody BoxErrorHistoryEntity boxErrorHistory){
		boxErrorHistoryService.updateById(boxErrorHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:boxerrorhistory:delete")
    public R delete(@RequestBody String[] parkingNames){
		boxErrorHistoryService.removeByIds(Arrays.asList(parkingNames));

        return R.ok();
    }

}
