
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
public class JSICmdToUpdateCurPtCurve extends XLoggableCmd {
    private Point mScreenPt = null;
    private Point2D.Double mWorldPt = null;
    
    private JSICmdToUpdateCurPtCurve(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    public static boolean execute(XApp app, Point pt) {
        JSICmdToUpdateCurPtCurve cmd = new JSICmdToUpdateCurPtCurve(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;
        JSIPtCurve curPtCurve = app.getPtCurveMgr().getCurPtCurve();
        int size = curPtCurve.getPts().size();
        
        Point2D.Double lastWorldPt = curPtCurve.getPts().get(size - 1);
        Point lastScreenPt = 
                app.getXform().calcPtFromWorldToScreen(lastWorldPt);
        if (this.mScreenPt.distance(lastScreenPt) < 
                JSIPtCurve.MIN_DISTANCE_BTWN_PTS) {
            return false;
        }
        this.mWorldPt = app.getXform().calcPtFromScreenToWorld(this.mScreenPt);
        curPtCurve.addPoint(this.mWorldPt);
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
