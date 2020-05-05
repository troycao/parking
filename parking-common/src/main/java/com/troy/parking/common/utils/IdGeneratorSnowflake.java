package com.troy.parking.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName IdGeneratorSnowflake
 * @Description TODO
 * @Author caoqiang
 * @Date 2020/4/13 14:03
 * @Version 1.0
 */
@Slf4j
@Component
public class IdGeneratorSnowflake {

    private long workerId = 0;
    private long datacenterId = 0;
    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    @PostConstruct
    public void init(){
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerId:{}", workerId);
        } catch (Exception e){
            e.printStackTrace();
            log.warn("当前机器的workerId获取失败,{}",e);
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long snowflakeId(){
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId,long datacenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    public synchronized String snowflakeIdStr(){
        return snowflake.nextId() + "";
    }

    public synchronized String snowflakeIdStr(long workerId,long datacenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId() + "";
    }

    public static void main(String[] args) {
        IdGeneratorSnowflake idGeneratorSnowflake = new IdGeneratorSnowflake();
        System.out.println(idGeneratorSnowflake.snowflakeId());
    }

}
