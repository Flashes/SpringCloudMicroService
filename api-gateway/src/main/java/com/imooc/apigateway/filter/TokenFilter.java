package com.imooc.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * URL过滤
 * 如果请求中没有加token，则请求不通过
 */
@Component
public class TokenFilter extends ZuulFilter{

    @Override
    public String filterType() {//过滤器类型
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {//顺序
        return PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {//逻辑部分
        //获取请求上下文
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();
        //从参数中获取token，也可以从cookie，header中获取
        String token=request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            //返回没有权限
//            requestContext.setSendZuulResponse(false);
//            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);//权限不足:401
        }
        return null;
    }
}
