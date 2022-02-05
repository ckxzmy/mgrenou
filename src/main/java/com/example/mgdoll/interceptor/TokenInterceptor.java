package com.example.mgdoll.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.conf.CommonConf;
import com.example.mgdoll.conf.NotCheckTokenAnn;
import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 自定义token拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        String path = request.getServletPath();
        if(path.matches(CommonConf.NO_INTERCEPTOR_PATH)){
            return true;
        }else{
            if(!(handler instanceof HandlerMethod)){
                return true;
            }
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            //判断接口是否需要验证token
            NotCheckTokenAnn methodAnnotation = method.getAnnotation(NotCheckTokenAnn.class);
            //有注解就不需要验证
            if(methodAnnotation != null){
                return true;
            }
            String token = request.getParameter("access_token");
            if(token == null){
                token = request.getHeader("access_token");
            }

            response.setCharacterEncoding("utf-8");
            //token不存在
            if (null != token) {
                //验证token是否正确
                Boolean result = JwtUtil.verify(token);
                if (result) {
                    return true;
                }else {
                    apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.TOKEN_EXPIRE);
                    responseMessage(response,response.getWriter(),apiResponse);
                    return false;
                }
            }else {
                apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.TOKEN_FAIL);
                responseMessage(response,response.getWriter(),apiResponse);
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 返回信息给客户端
     *
     * @param response
     * @param out
     * @param apiResponse
     */
    private void responseMessage(HttpServletResponse response, PrintWriter out, ApiResponse apiResponse) {
        response.setContentType("application/json; charset=utf-8");
        out.print(JSONObject.toJSONString(apiResponse));
        out.flush();
        out.close();
    }
}
