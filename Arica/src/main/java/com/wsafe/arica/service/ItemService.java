package com.wsafe.arica.service;

import com.wsafe.arica.entity.Item;
import com.wsafe.arica.mapper.ItemDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:Zhangchi
 * @date:2021-02-22 下午5:07
 **/
@Service
public class ItemService {

    @Resource
    private ItemDAO itemDAO;

    public Item insert(Item item) {

        itemDAO.insert(item);
        return item;
    }

    public Item findById(int id) {
        return itemDAO.selectByPrimaryKey(id);
    }
}
