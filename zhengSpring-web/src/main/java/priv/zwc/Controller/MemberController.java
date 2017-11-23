package priv.zwc.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import priv.zwc.Annotation.Auth;
import priv.zwc.Common.JsonResult;
import priv.zwc.DataSource.DynamicDataSource;
import priv.zwc.Page.PageModel;
import priv.zwc.Service.IMember;
import priv.zwc.Service.IRole;
import priv.zwc.Module.Member;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
@Controller
@RequestMapping(value = "/Member")
public class MemberController extends BaseController {
    @Autowired
    private IMember iMember;
    @Autowired
    private IRole iRole;
    @Auth
    @RequestMapping(value = "/index",method = {RequestMethod.GET})
    public ModelAndView Index(HttpServletRequest request,@RequestParam(defaultValue = "1") int currpage,@RequestParam(defaultValue = "5") int pagesize){
        ModelAndView modelAndView=new ModelAndView("index");
        DynamicDataSource.changeDataSourceKey("d2");
        PageModel<Member> list=iMember.queryListPage("",currpage,pagesize);
        DynamicDataSource.changeDataSourceKey("d1");
        list=iMember.queryListPage("",currpage,pagesize);
        UserInfo userInfo= getUserInfo(request);
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.addObject("list",list);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxindex",method = RequestMethod.POST)
    public JsonResult AjaxIndex(HttpServletRequest request,String keyword,@RequestParam(defaultValue = "1") int currpage,@RequestParam(defaultValue = "5") int pagesize){
        PageModel<Member> list=iMember.queryListPage(keyword,currpage,pagesize);
        //JsonConfig jsonconfig=new JsonConfig();
        //jsonconfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
        //JSONArray object= JSONArray.fromObject(list,jsonconfig);
        return jsonResult(1,"",list);
    }

    @RequestMapping(value = "/AddOrEdit",method = {RequestMethod.GET})
    public ModelAndView Create(@RequestParam(value = "mid",required = true,defaultValue = "0") long mid){
        ModelAndView modelAndView=new ModelAndView();
        Member member=new Member();
        if (mid>0){      //修改
            member=iMember.queryById(mid);
            modelAndView.setViewName("Edit");
        }else {          //编辑
            modelAndView.setViewName("Add");
        }
        List<Role> rolelist= iRole.queryList();
        modelAndView.addObject("member",member);
        modelAndView.addObject("role",rolelist);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/addoredit",method = {RequestMethod.POST})
    public JsonResult AddOrEdit(HttpServletRequest request, Member member){
        try {
            if (member.getId()>0){     //编辑
                member.setUpdateTime(new Date());
                iMember.updateById(member);
            }else {                    //新增
                member.setCreateTime(new Date());
                member.setUpdateTime(new Date());
                iMember.addMember(member);
            }
            return jsonResult(1,"成功");
        }catch (Exception e){
            return jsonResult(-1,"出错");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = {RequestMethod.POST})
    public JsonResult Delete(@RequestParam(value = "mid",required = true,defaultValue = "0") long mid){
        try {
            if (mid>0){
                iMember.deleteById(mid);
                return jsonResult(1,"成功");
            }else
            {
                return jsonResult(-1,"出错");
            }
        }catch (Exception e){
            return jsonResult(-1,"出错");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/canadd",method = {RequestMethod.POST})
    public JsonResult CanAdd(String phone){
        if (iMember.canAdd(phone)){
            return jsonResult(1,"可以注册");
        }
        return jsonResult(-1,"手机号码已存在");
    }
}
