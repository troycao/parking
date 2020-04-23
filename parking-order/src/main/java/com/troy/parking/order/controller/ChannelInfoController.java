package com.troy.parking.order.controller;

import com.troy.parking.common.utils.PageUtils;
import com.troy.parking.common.utils.R;
import com.troy.parking.order.entity.ChannelInfoEntity;
import com.troy.parking.order.service.ChannelInfoService;
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
@RequestMapping("order/channelinfo")
public class ChannelInfoController {
    @Autowired
    private ChannelInfoService channelInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:channelinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = channelInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:channelinfo:info")
    public R info(@PathVariable("id") Integer id){
		ChannelInfoEntity channelInfo = channelInfoService.getById(id);

        return R.ok().put("channelInfo", channelInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:channelinfo:save")
    public R save(@RequestBody ChannelInfoEntity channelInfo){
		channelInfoService.save(channelInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:channelinfo:update")
    public R update(@RequestBody ChannelInfoEntity channelInfo){
		channelInfoService.updateById(channelInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:channelinfo:delete")
    public R delete(@RequestBody Integer[] ids){
		channelInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
