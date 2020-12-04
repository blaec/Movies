package org.blaec.movies.filters;


import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;


class GZIPServletOutputStream extends ServletOutputStream {
    GZIPOutputStream internalGzipOS;
    /** Decorator constructor */
    GZIPServletOutputStream(ServletOutputStream sos) throws IOException {
        this.internalGzipOS = new GZIPOutputStream(sos);
    }
    public void write(int param) throws java.io.IOException {
        internalGzipOS.write(param);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
