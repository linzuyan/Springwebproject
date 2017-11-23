package priv.zwc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by admin on 2016/6/12.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index(){
        return "login";
    }
}
