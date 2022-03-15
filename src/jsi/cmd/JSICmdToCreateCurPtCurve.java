
package jsi.cmd;

import java.awt.Point;
import java.awt.geom.Point2D;
import jsi.JSIApp;
import jsi.JSIPtCurve;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToCreateCurPtCurve extends XLoggableCmd {
    //fields
    private Point mScreenPt = null;
    private Point2D.Double mWorldPt = null;
    
    //private constructor
    private JSICmdToCreateCurPtCurve(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    public static boolean execute(XApp app, Point pt) {
        JSICmdToCreateCurPtCurve cmd = new JSICmdToCreateCurPtCurve(app, pt);
        return cmd.execute();
    }
    
    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp)this.mApp;
        this.mWorldPt = app.getXform().calcPtFromScreenToWorld(this.mScreenPt);
        JSIPtCurve ptCurve = new JSIPtCurve(this.mWorldPt,
                app.getCanvas2D().getCurColorForPtCurve(),
                app.getCanvas2D().getCurStrokeForPtCurve());
        app.getPtCurveMgr().setCurPtCurve(ptCurve);
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mScreenPt).append("\t");
        sb.append(this.mWorldPt);
        return sb.toString();
    }
    
}
