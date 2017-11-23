package priv.zwc.Controller;

import priv.zwc.Common.JsonResult;
import priv.zwc.Service.IRole;
import priv.zwc.Module.Role;
import priv.zwc.Module.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
@Controller
@RequestMapping(value = "/Role")
public class RoleController extends BaseController {
    @Autowired
    private IRole iRole;
    @RequestMapping(value = "/index",method = {RequestMethod.GET})
    public ModelAndView Index(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("rolelist");
        List<Role> list=new ArrayList<Role>();
        list= iRole.queryList();
        UserInfo userInfo= getUserInfo(request);
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.addObject("role",list);
        return modelAndView;
    }

    @RequestMapping(value = "/Add",method = {RequestMethod.GET})
    public ModelAndView Create(@RequestParam(value = "rid",required = true,defaultValue = "0") int rid){
        ModelAndView modelAndView=new ModelAndView("roleAdd");
        Role role=new Role();
        if (rid>0){      //修改
            role=iRole.queryById(rid);
        }else {          //新增

        }
        modelAndView.addObject("role",role);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/addoredit",method = {RequestMethod.POST})
    public JsonResult AddOrEdit(Role role){
        try {
            if (role.getId()>0){     //编辑
                iRole.updateById(role);
            }else {                    //新增
                iRole.addRole(role);
            }
            return jsonResult(1,"成功");
        }catch (Exception e){
            return jsonResult(-1,"出错");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = {RequestMethod.POST})
    public JsonResult Delete(@RequestParam(value = "rid",required = true,defaultValue = "0") int rid){
        try {
            if (rid>0){
                iRole.deleteById(rid);
                return jsonResult(1,"成功");
            }
            return jsonResult(-1,"出错");
        }catch (Exception e){
            return jsonResult(-1,"出错");
        }
    }
}
