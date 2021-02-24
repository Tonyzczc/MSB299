package com.wsafe.arica;

import com.wsafe.arica.entity.Item;
import com.wsafe.arica.service.ItemService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AricaApplicationTests {

    @Autowired
    ItemService itemService;
	@Test
	void contextLoads() {
	}

	@Test
	void findAll(){
        List<Item> all = itemService.findAll();
        System.out.println(ToStringBuilder.reflectionToString(all));
    }

}
