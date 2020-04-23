package com.troy.parking.order.controller;

import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.R;
import com.troy.parking.order.entity.MerInfoEntity;
import com.troy.parking.order.service.MerInfoService;
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
@RequestMapping("order/merinfo")
public class MerInfoController {
    @Autowired
    private MerInfoService merInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:merinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = merInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:merinfo:info")
    public R info(@PathVariable("id") String id){
		MerInfoEntity merInfo = merInfoService.getById(id);

        return R.ok().put("merInfo", merInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:merinfo:save")
    public R save(@RequestBody MerInfoEntity merInfo){
		merInfoService.save(merInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:merinfo:update")
    public R update(@RequestBody MerInfoEntity merInfo){
		merInfoService.updateById(merInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:merinfo:delete")
    public R delete(@RequestBody String[] ids){
		merInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
