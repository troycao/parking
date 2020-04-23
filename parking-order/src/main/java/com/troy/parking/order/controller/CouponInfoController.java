package com.troy.parking.order.controller;

import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.R;
import com.troy.parking.order.entity.CouponInfoEntity;
import com.troy.parking.order.service.CouponInfoService;
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
@RequestMapping("order/couponinfo")
public class CouponInfoController {
    @Autowired
    private CouponInfoService couponInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:couponinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:couponinfo:info")
    public R info(@PathVariable("id") String id){
		CouponInfoEntity couponInfo = couponInfoService.getById(id);

        return R.ok().put("couponInfo", couponInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:couponinfo:save")
    public R save(@RequestBody CouponInfoEntity couponInfo){
		couponInfoService.save(couponInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:couponinfo:update")
    public R update(@RequestBody CouponInfoEntity couponInfo){
		couponInfoService.updateById(couponInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:couponinfo:delete")
    public R delete(@RequestBody String[] ids){
		couponInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
