
package jsi.cmd;

import java.awt.BasicStroke;
import jsi.JSIApp;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToIncreaseStrokeWidthForCurPtCurve extends XLoggableCmd {
    private float mWBefore = Float.NaN;
    private float mWDelta = Float.NaN;
    private float mWAfter = Float.NaN;
    
    private JSICmdToIncreaseStrokeWidthForCurPtCurve(XApp app, float dw){
        super(app);
        this.mWDelta = dw;
    }
    
    public static boolean execute(XApp app, float dw) {
        JSICmdToIncreaseStrokeWidthForCurPtCurve cmd = 
            new JSICmdToIncreaseStrokeWidthForCurPtCurve(app, dw);
        return cmd.execute();
    } 
    
    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;
        BasicStroke bs = 
                (BasicStroke) app.getCanvas2D().getCurStrokeForPtCurve();
        this.mWBefore = bs.getLineWidth();
        app.getCanvas2D().increaseStrokeWidthForCurPtCurve(this.mWDelta);
        bs = (BasicStroke)app.getCanvas2D().getCurStrokeForPtCurve();
        this.mWAfter = bs.getLineWidth();
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mWBefore).append("\t");
        sb.append(this.mWDelta).append("\t");
        sb.append(this.mWAfter).append("\t");
        return sb.toString();
    }
    
    
}
