package com.wsafe.arica.mapper;

import com.wsafe.arica.entity.Item;
import com.wsafe.arica.entity.ItemExample;
import com.wsafe.arica.entity.ItemHtml;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ItemDAO继承基类
 */
@Repository
public interface ItemDAO extends MyBatisBaseDao<Item, Integer, ItemExample>{
    @Select("select * from item")
    List<ItemHtml> selectAllByItemHtml();
}
