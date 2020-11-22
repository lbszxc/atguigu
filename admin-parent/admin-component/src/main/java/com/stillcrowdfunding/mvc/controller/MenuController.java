package com.stillcrowdfunding.mvc.controller;

import com.stillcrowdfunding.entity.Menu;
import com.stillcrowdfunding.service.api.MenuService;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020/7/16 12:01
 * @description
 **/
//@Controller
//@ResponseBody
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    //@ResponseBody
    @RequestMapping(value = "/menu/remove.json")
    public ResultEntity<String> removeMenu(@RequestParam("id") Integer id){

        menuService.removeMenu(id);

        return ResultEntity.successWithoutData();

    }

    //@ResponseBody
    @RequestMapping(value = "/menu/update.json")
    public ResultEntity<String> updateMenu(Menu menu){

        menuService.updateMenu(menu);

        return ResultEntity.successWithoutData();

    }

    //@ResponseBody
    @RequestMapping(value = "/menu/save.json")
    public ResultEntity<String> saveMenu(Menu menu){

        menuService.saveMenu(menu);

        return ResultEntity.successWithoutData();

    }

    //@ResponseBody
    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<Menu> getWholeTree(){

        // 1.查询全部的Menu对象
        List<Menu> menuList = menuService.getAll();

        // 2.声明一个变量用来存储找到的根节点
        Menu root = null;

        // 3..创建Map 对象用来存储id 和Menu 对象的对应关系便于查找父节点
        Map<Integer,Menu> menuMap = new HashMap<>();

        // 4.遍历menuList 填充menuMap
        for (Menu menu:menuList){

            Integer id = menu.getId();

            menuMap.put(id,menu);

        }

        // 5.再次遍历menuList 查找根节点、组装父子节点
        for (Menu menu:menuList){

            // 6.获取当前menu 对象的pid 属性值
            Integer pid = menu.getPid();

            // 7.判断pid是否为null，如果为空，判定为根节点
            if (pid==null){

                // 7.把当前正在遍历循环额Menu对象赋值给root
                root = menu;

                // 9.如果当前节点是根节点，那么肯定没有父节点，不必继续执行
                continue;

            }

            // 10.如果pid 不为null，说明当前节点有父节点，那么可以根据pid 到menuMap中查找对应的Menu对象
            Menu father =  menuMap.get(pid);

            // 11.将当前节点存入父节点的children 集合
            father.getChildren().add(menu);

        }

        return ResultEntity.successWithoutData(root);

    }
}
