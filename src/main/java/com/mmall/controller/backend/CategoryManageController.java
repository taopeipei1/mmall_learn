package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServiceResponse;
import com.mmall.pojo.Category;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Set;


@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public ServiceResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId",defaultValue = "0") int parenId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iCategoryService.addCategory(categoryName,parenId);

        }else {
            return ServiceResponse.createByErrorMessage("无权限操作，需要操作员权限");
        }

    }
    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServiceResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //更新categoryName
            return iCategoryService.updateCategoryName(categoryId,categoryName);
        }else{
            return ServiceResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServiceResponse getChildrenParalleCategory(HttpSession session,@RequestParam(value = "category",defaultValue = "0") Integer category){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iCategoryService.getChildParalleCategory(category);
        }else{
            return ServiceResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServiceResponse getCategoryAndDeepChildrenCategory(HttpSession session,@RequestParam(value = "category",defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //查询当前节点的ID和递归子节点的ID
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        }else{
            return ServiceResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }

    }

}
