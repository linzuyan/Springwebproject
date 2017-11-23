package priv.zwc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import priv.zwc.Common.JsonResult;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016/3/14.
 */
@Controller
@RequestMapping(value = "/Upload")
public class UploadController extends BaseController {
    //上传单张照片
    @ResponseBody
    @RequestMapping(value = "/uploadbysola",method = {RequestMethod.POST})
    public JsonResult UploadBySola(HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap= multipartHttpServletRequest.getFileMap();
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date=new Date();

        Random random=new Random();
        random.nextInt(999);
        String imgurl="";
        if (fileMap.size()==1){
            for (String item1 :fileMap.keySet()){
                MultipartFile multipartFile=  fileMap.get(item1);
                if (multipartFile!=null){
                    String filename= multipartFile.getOriginalFilename();
                    String lx= filename.substring(filename.lastIndexOf(".")+1).trim();
                    if (lx.equals("png")||lx.equals("jpg")||lx.equals("gif")||lx.equals("bmp")){
                        int imgint=random.nextInt(999);
                        imgurl="/pic/"+simpleDateFormat.format(date)+"/"+simpleDateFormat.format(date)+imgint+"."+lx+"";
                        String newpath="F:\\tomcat\\pic\\"+simpleDateFormat.format(date)+"";
                        File dirpath=new File(newpath);
                        if (!dirpath.exists()&&!dirpath.isDirectory()){
                            dirpath.mkdir();
                        }
                        String newfilepath="F:\\tomcat\\pic\\"+simpleDateFormat.format(date)+"\\"+simpleDateFormat.format(date)+imgint+"."+lx+"";
                        File newfile=new File(newfilepath);
                        multipartFile.transferTo(newfile);
                        return jsonResult(1,imgurl);
                    }
                    return jsonResult(-1,"图片格式不正确");
                }
            }
        }
        return jsonResult(-1,"图片上传失败");
    }

    //上传多张照片
    @ResponseBody
    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    public JsonResult Upload(HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap= multipartHttpServletRequest.getFileMap();
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date=new Date();

        Random random=new Random();
        random.nextInt(999);
        String newfilepath=simpleDateFormat.format(date)+random.nextInt(999)+".png";
        //ext=file.substring(file.lastIndexOf(".")).toLowerCase();
        for (String item :fileMap.keySet()){
            MultipartFile multipartFile=  fileMap.get(item);
            if (multipartFile!=null){
                String filename= multipartFile.getOriginalFilename();
                String[] strings= filename.split(".");
                String lx= strings[strings.length-1].toLowerCase();
                if (lx!="png"||lx!="jpg"||lx!="gif"||lx!="bmp"){
                    return jsonResult(-1,"图片格式不正确");
                }
                File newfile=new File("F:\\tomcat\\pic\\"+newfilepath+"");
                multipartFile.transferTo(newfile);
            }
        }
        return jsonResult(1,newfilepath);
    }
}
