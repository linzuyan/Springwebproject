package priv.zwc.Controller;

import priv.zwc.Annotation.Log;
import priv.zwc.Common.AuthUser;
import priv.zwc.Common.Constants;
import priv.zwc.Service.IMember;
import priv.zwc.Module.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/3/8.
 */
@Controller
@RequestMapping(value = "/Login")
public class LoginController extends BaseController {
    @Autowired
    private IMember iMember;
    @Log(name = "aaaaaaaaaaaaaaaa")
    @RequestMapping(value = "/login",method = {RequestMethod.GET})
    public ModelAndView login(String returnurl, HttpServletRequest req){
        ModelAndView modelAndView=new ModelAndView("login");
        modelAndView.addObject("rurl",returnurl);
        return modelAndView;
    }

    @RequestMapping(value = "/httplogin",method = {RequestMethod.POST})
    public ModelAndView postlogin(String username, String password,String rurl, HttpServletRequest request, HttpServletResponse response)throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        if (username!=null&&username!=""){
            Member member= iMember.loginQuery(username);
            if (member==null){
                modelAndView.addObject("msg","用户名无效");
                return modelAndView;
            }else {
                if (password.equals(member.getPassword())){
                    String userinfo="";
                    userinfo+=""+member.getId()+","+member.getPhone()+","+member.getUserName()+","+member.getRole()+"";
                    setCookie(response,userinfo);
                    AuthUser authUser=new AuthUser();
                    authUser.setId(member.getId());
                    authUser.setUserName(member.getUserName());
                    authUser.setRole(member.getRole());
                    request.getSession().setAttribute(Constants.SESSION_USER_KEY,authUser);
                    if (rurl!=null&&rurl!=""){
                        return new ModelAndView("redirect:"+rurl+"");
                    }
                    return new ModelAndView("redirect:/Member/index");
                }else {
                    modelAndView.addObject("msg","密码无效");
                    return modelAndView;
                }
            }
        }
        else {
            modelAndView.addObject("msg","用户名无效");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/loginout",method = {RequestMethod.GET})
    public ModelAndView Loginout(HttpServletResponse response) throws Exception{
        Cookie cookie=new Cookie("curruser", URLEncoder.encode("", "UTF-8"));
        cookie.setMaxAge(0);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ModelAndView("redirect:/Login/login");
    }
}
