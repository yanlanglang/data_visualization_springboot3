package com.ns4a.dv.service;



import com.ns4a.dv.vo.OptionalStockVo;

import java.util.List;

/**
 * @className: OptionalStockService
 * @author: Yan Lang
 * @date: 2022/11/28
 * @description:
 **/
public interface OptionalStockService {
    /**
     *
     * @param id
     * @return
     */
    List<OptionalStockVo> findOptionalStockVos(Long id) throws Exception;
}
