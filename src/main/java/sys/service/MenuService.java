package sys.service;


import sys.entity.RbacUri;

import java.util.List;

public interface MenuService
{
    String getMenuStr(List<RbacUri> menus, Integer index);
}
