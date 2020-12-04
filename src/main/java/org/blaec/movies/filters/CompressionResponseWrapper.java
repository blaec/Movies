package org.blaec.movies.filters;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.zip.GZIPOutputStream;

class CompressionResponseWrapper extends HttpServletResponseWrapper {
    private GZIPServletOutputStream servletGzipOS = null;
    private PrintWriter pw = null;
    CompressionResponseWrapper(HttpServletResponse resp) {
        super(resp);
    }
    public void setContentLength(int len) { }
    public GZIPOutputStream getGZIPOutputStream() {
        return this.servletGzipOS.internalGzipOS;
    }
    private Object streamUsed = null;
    public ServletOutputStream getOutputStream() throws IOException {
        if ((streamUsed != null) && (streamUsed != pw)) {
            throw new IllegalStateException();
        }
        if ( servletGzipOS == null ) {
            servletGzipOS
                    = new GZIPServletOutputStream(getResponse()
                    .getOutputStream());
            streamUsed = servletGzipOS;
        }
        return servletGzipOS;
    }
    public PrintWriter getWriter() throws IOException {
        if ( (streamUsed != null) && (streamUsed != servletGzipOS)) {
            throw new IllegalStateException();
        }
        if ( pw == null ) {
            servletGzipOS
                    = new GZIPServletOutputStream(getResponse()
                    .getOutputStream());
            OutputStreamWriter osw
                    = new OutputStreamWriter(servletGzipOS,
                    getResponse().getCharacterEncoding());
            pw = new PrintWriter(osw);
            streamUsed = pw;
        }
        return pw;
    }
}
