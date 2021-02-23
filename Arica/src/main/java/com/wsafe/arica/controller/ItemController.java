package com.wsafe.arica.controller;

import com.wsafe.arica.entity.Item;
import com.wsafe.arica.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author:Zhangchi
 * @date:2021-02-22 下午4:49
 **/
@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping("")
    public String index() {
        System.out.println("xxoo");
        return "arica";
    }

    @RequestMapping("addtor")
    public String addtor(){
        return "add";
    }

    @RequestMapping("add")
    public String add(Item item , Model model){
        Item itemRecord = itemService.insert(item);
        model.addAttribute("msg", "添加商品成功, <a href = 'view?id="
                +itemRecord.getId()+"' target='_blank' class=\"layui-btn\">预览一下</a>");
        return "success";
    }

    /**
     * 临时预览，动态
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("view")
    public String view(Model model,int id) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        return "view";
    }

    @RequestMapping("itemList")
    public String itemList(Model model){
        List<Item> itemList = itemService.findAll();
        model.addAttribute("items", itemList);
        return "item_list";
    }

    @RequestMapping("generate")
    public String generate(Model model , int id){
        itemService.generateHTML(id);

        String msg = "文件生成成功，<a href='item"+id+".html' target='_blank'>查看</a>";
        model.addAttribute("msg", msg);
        return "success";
    }

    @RequestMapping("templates")
    public String templates(){
        return "templates";
    }

    /**
     * 修改模板
     * @param model
     * @return
     */
    @RequestMapping("editTemplate")
    public String editTemplate(Model model)throws Exception {

        String tplStr = itemService.getFileTemplateString();
        model.addAttribute("tplStr",tplStr);
        return "edit_template";
    }

    /**
     * 保存模板
     * @param model
     * @param content
     * @return
     */
    @RequestMapping("saveTemplate")
    public String saveTemplate(Model model,String content)throws Exception {

        itemService.saveFileTemplateString(content);

        String msg = "保存成功。";
        model.addAttribute("msg", msg);
        return "success";
    }




}
