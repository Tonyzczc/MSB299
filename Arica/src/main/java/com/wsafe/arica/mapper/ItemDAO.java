package com.wsafe.arica.mapper;

import com.wsafe.arica.entity.Item;
import com.wsafe.arica.entity.ItemExample;
import org.springframework.stereotype.Repository;

/**
 * ItemDAO继承基类
 */
@Repository
public interface ItemDAO extends MyBatisBaseDao<Item, Integer, ItemExample>{
}
