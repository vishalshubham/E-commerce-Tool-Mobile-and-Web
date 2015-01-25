
package com.mba.manager.charts;


import com.mba.charts.*;
import java.io.FileOutputStream;
import java.awt.*;
import javax.swing.JLabel;

import org.jCharts.Chart;
import org.jCharts.encoders.PNGEncoder;

public abstract class UserGuideBase
{
    private Panel p = new Panel();
    private JLabel l = new JLabel();
    public UserGuideBase()
    {
        p.setVisible(true);
        p.setSize(500, 500);
        p.setLocation(100, 100);
        p.add(l);
    }
    abstract void run() throws Throwable;

    void exportImage( Chart chart, String fileName )
    {
        String extension= ".jpg";
	FileOutputStream fileOutputStream;

	try
	{
            fileOutputStream= new FileOutputStream( fileName + extension );
	    PNGEncoder.encode( chart, fileOutputStream );
	    fileOutputStream.flush();
	    fileOutputStream.close();
	}
	catch( Throwable throwable )
	{
	    throwable.printStackTrace();
	}
    }
}