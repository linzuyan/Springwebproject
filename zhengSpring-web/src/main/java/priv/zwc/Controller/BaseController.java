package priv.zwc.Controller;



import priv.zwc.Common.JsonResult;
import priv.zwc.Module.UserInfo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/3/11.
 */
public class  BaseController {
    protected UserInfo getUserInfo(HttpServletRequest request){
        UserInfo userInfo=new UserInfo();
        String info= URLDecoder.decode((String) request.getAttribute("curruser"));
        String[] strings= info.split(",");
        if (strings.length==4){
            userInfo.setUserId(Long.parseLong(strings[0]));
            userInfo.setPhone(strings[1]);
            userInfo.setUserName(strings[2]);
            userInfo.setUserRoleId(Integer.parseInt(strings[3]));
        }
        return userInfo;
    }

    protected void setCookie(HttpServletResponse response, String value)throws  Exception{
        Cookie cookie=new Cookie("curruser", URLEncoder.encode(value, "UTF-8"));
        cookie.setMaxAge(30 * 60);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    protected boolean isAjax(HttpServletRequest request){
        String requestheader= request.getHeader("X-Requested-With");
        if (requestheader!=null){
            return true;
        }
        return false;
    }

    protected JsonResult jsonResult(Integer _code,String _msg){
        JsonResult jsonResult=new JsonResult(_code,_msg);
        return  jsonResult;
    }

    protected JsonResult jsonResult(Integer _code,String _msg,Object _data){
        JsonResult jsonResult=new JsonResult(_code,_msg,_data);
        return jsonResult;
    }
}
