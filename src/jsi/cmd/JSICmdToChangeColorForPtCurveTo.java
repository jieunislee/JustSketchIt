
package jsi.cmd;

import java.awt.Color;
import jsi.JSIApp;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToChangeColorForPtCurveTo extends XLoggableCmd {
    //fields 
    private Color mColor = null;
    
    //private constructor
    private JSICmdToChangeColorForPtCurveTo(XApp app, Color c) {
        super(app);
        this.mColor = c;
    }
    
    public static boolean execute(XApp app, Color c) {
        JSICmdToChangeColorForPtCurveTo cmd = new JSICmdToChangeColorForPtCurveTo(app, c);
        
        return cmd.execute();
    }
    
    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;
        app.getCanvas2D().setCurColorForPtCurve(this.mColor);
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mColor).append("\t");
        return sb.toString();
    }
    
}
