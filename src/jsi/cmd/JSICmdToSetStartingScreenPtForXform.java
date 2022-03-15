
package jsi.cmd;

import java.awt.Point;
import jsi.JSIApp;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToSetStartingScreenPtForXform extends XLoggableCmd {
    private Point mScreenPt = null;
  
    private JSICmdToSetStartingScreenPtForXform(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    public static boolean execute(XApp app, Point pt) {
        JSICmdToSetStartingScreenPtForXform cmd = 
                new JSICmdToSetStartingScreenPtForXform(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;
        
        app.getXform().setStartingScreenPt(this.mScreenPt);
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");        
        sb.append(this.mScreenPt);
        return sb.toString();
    }
}
