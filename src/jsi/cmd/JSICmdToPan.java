
package jsi.cmd;

import java.awt.Point;
import jsi.JSIApp;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToPan extends XLoggableCmd {
    //fields
    private Point mPt = null;
    
    private JSICmdToPan(XApp app, Point pt) {
        super(app);
        this.mPt = pt;
    }
    
    public static boolean execute(XApp app, Point pt){
        JSICmdToPan cmd = new JSICmdToPan(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;

        app.getXform().translateTo(mPt);
        app.getCanvas2D().repaint();
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mPt.getX()).append("\t");
        sb.append(this.mPt.getY());
        return sb.toString();
    }
}
