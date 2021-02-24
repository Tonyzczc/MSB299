package com.wsafe.arica.service;

import com.jfinal.kit.Kv;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.jfinal.template.ext.spring.JFinalViewResolver;
import com.wsafe.arica.entity.Item;
import com.wsafe.arica.entity.ItemExample;
import com.wsafe.arica.entity.ItemHtml;
import com.wsafe.arica.mapper.ItemDAO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

/**
 * @Author:Zhangchi
 * @date:2021-02-22 下午5:07
 **/
@Service
public class ItemService {

    @Value("${nginx.html.root}")
    private String templePath;

    @Resource
    private ItemDAO itemDAO;

    public Item insert(Item item) {
        itemDAO.insert(item);
        return item;
    }

    /**
     * 查询所有页面信息
     * @return
     */
    public List<Item> findAll(){
        List<Item> items = itemDAO.selectByExample(new ItemExample());
        return items;
    }

    /**
     * ID查询
     * @param id
     * @return
     */
    public Item findById(int id) {
        return itemDAO.selectByPrimaryKey(id);
    }

    /**
     * ID查询数据，JFinal生成静态HTML文件
     * @param id
     */
    public void generateHTML(int id) {

        //初始化模板引擎
        Engine engine = Engine.use();
        engine.setDevMode(true);
        engine.setToClassPathSourceFactory();

        Item item = itemDAO.selectByPrimaryKey(id);

        //模板数据渲染
        Kv kv = Kv.by("item", item);
        String fileName = "item"+id+".html";
        String filePath = templePath;
        //设置文件路径+文件名
        File file = new File(filePath+fileName);
        //渲染输出文件
        Template template = engine.getTemplate("templates/item.html");
        template.render(kv, file);
    }

    public String getFileTemplateString() throws Exception {
        String file = ClassUtils.getDefaultClassLoader().getResource("templates/item.html").getFile();
        System.out.println(file);
        BufferedReader reader =  new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuffer fileReader = new StringBuffer();
        String lineStr = reader.readLine();
        while(lineStr != null){
            fileReader.append(lineStr).append("\n\r");
            lineStr = reader.readLine();
        }
        reader.close();
        return fileReader.toString();
    }

    public void saveFileTemplateString(String content) throws IOException {
        String file = ClassUtils.getDefaultClassLoader().getResource("templates/item.html").getFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.flush();
        writer.close();
    }

    /**
     * 生成item文件
     * @return
     */
    public List<ItemHtml> generateAll() {
        List<ItemHtml> itemHtmlList = itemDAO.selectAllByItemHtml();
        Engine engine = JFinalViewResolver.engine;

        String filePath = templePath;
        Template template = engine.getTemplate("item.html");

        for(ItemHtml itemHtml : itemHtmlList){
            Kv kv = Kv.by("item", itemHtml);
            String fileName = "item"+itemHtml.getId()+".html";
            File file = new File(filePath+fileName);

            try {
                template.render(kv, file);
                itemHtml.setHtmlStatus("ok");
                itemHtml.setLocation(filePath + fileName);
            }catch (Exception e){
                itemHtml.setHtmlStatus("error");
                continue;
            }
        }
        return itemHtmlList;
    }

    /**
     * 生成主页文件
     */
    public void generateMain() {
        // 初始化模板引擎
        Engine engine = JFinalViewResolver.engine;

        ItemExample example = new ItemExample();
        // 从数据源，获取数据
        List<Item> items = itemDAO.selectByExample(example );
        //c:/dev/uploads/

        // 前端模板用的键值对
        Kv kv = Kv.by("items", items);

        // 文件写入路径
        String fileName = "main.html";
        String filePath = templePath;
        // 路径 直接能被用户访问
        File file = new File(filePath+fileName);

        // 开始渲染 输出文件
        Template template = engine.getTemplate("item_main.html");
        template.render(kv, file);

    }
}
