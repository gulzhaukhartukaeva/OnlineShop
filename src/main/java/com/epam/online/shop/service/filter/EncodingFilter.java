package com.epam.online.shop.service.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final String CONFIG_ENCODING = "encoding";
    private static final String CONFIG_CONTENT_TYPE = "contentType";
    private String encoding;
    private String contentType;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType(contentType);
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(CONFIG_ENCODING);
        contentType = filterConfig.getInitParameter(CONFIG_CONTENT_TYPE);
        if (encoding == null || contentType == null){
            encoding = "UTF-8";
            contentType = "text/html";
        }
    }
}
