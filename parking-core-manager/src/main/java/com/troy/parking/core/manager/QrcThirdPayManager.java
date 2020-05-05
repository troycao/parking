package com.troy.parking.core.manager;

import com.troy.parking.core.manager.dto.AppletPayRequestDto;
import com.troy.parking.core.manager.dto.AppletPayResponseDto;
import com.troy.parking.core.manager.dto.TableCardScanPayRequestDto;

import java.util.Optional;

/**
 * @ClassName QrcThirdPayManager
 * @Description 第三方公众号和小程序支付管理类
 * @Author troy
 * @Date 2020/3/ 15:57
 * @Version 1.0
 */
public interface QrcThirdPayManager {

    /****
     * 台牌支付
     * @param tableCardScanPayRequestDto
     * @return
     */
    public Optional<String> tableCardScanPay(TableCardScanPayRequestDto tableCardScanPayRequestDto);

    /**
     * 小程序支付
     * @param appletPayRequestDto
     * @return
     */
    public Optional<AppletPayResponseDto> appletPay(AppletPayRequestDto appletPayRequestDto);

}