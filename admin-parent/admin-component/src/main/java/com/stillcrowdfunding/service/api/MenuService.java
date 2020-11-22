package com.stillcrowdfunding.service.api;

import com.stillcrowdfunding.entity.Menu;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/7/16 11:57
 * @description
 **/
public interface MenuService {

    List<Menu> getAll();

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void removeMenu(Integer id);
}
