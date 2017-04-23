package com.bizideal.whoami.wechat.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
/**
 * 异常处理
 * @author pc
 *
 */
@Controller  
public class ExceptionHandlerController {  
    @ExceptionHandler(ArithmeticException.class)  
    public String operateArithmeticException(ArithmeticException ex,HttpServletRequest request){  
        System.out.println("this is for test");  
        //mod.addAttribute("err", ex.getMessage()); //ExceptionHandler处理异常时，Model，是不能用的，否则会不起作用，这里用了HttpServletRequest  
        request.setAttribute("err", ex.getMessage());  
        return "appNouser";  
    }  
    
    @ExceptionHandler(IOException.class)  
    public String operateIOException(IOException ex,HttpServletRequest request){  
        System.out.println("this is for test");  
        //mod.addAttribute("err", ex.getMessage()); //ExceptionHandler处理异常时，Model，是不能用的，否则会不起作用，这里用了HttpServletRequest  
        request.setAttribute("err", ex.getMessage());  
        return "appNouser";  
    }
    
    @ExceptionHandler(Exception.class)
    public String operateException(Exception ex,HttpServletRequest request){  
        System.out.println("this is for test");  
        //mod.addAttribute("err", ex.getMessage()); //ExceptionHandler处理异常时，Model，是不能用的，否则会不起作用，这里用了HttpServletRequest  
        request.setAttribute("err", ex.getMessage());  
        return "wechaterror";  
    }  
    
}  