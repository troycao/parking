package com.troy.parking.order.controller;

import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.R;
import com.troy.parking.order.entity.InvoiceInfoEntity;
import com.troy.parking.order.service.InvoiceInfoService;
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
@RequestMapping("order/invoiceinfo")
public class InvoiceInfoController {
    @Autowired
    private InvoiceInfoService invoiceInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:invoiceinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = invoiceInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:invoiceinfo:info")
    public R info(@PathVariable("id") String id){
		InvoiceInfoEntity invoiceInfo = invoiceInfoService.getById(id);

        return R.ok().put("invoiceInfo", invoiceInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:invoiceinfo:save")
    public R save(@RequestBody InvoiceInfoEntity invoiceInfo){
		invoiceInfoService.save(invoiceInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:invoiceinfo:update")
    public R update(@RequestBody InvoiceInfoEntity invoiceInfo){
		invoiceInfoService.updateById(invoiceInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:invoiceinfo:delete")
    public R delete(@RequestBody String[] ids){
		invoiceInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
