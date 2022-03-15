
package jsi.cmd;

import jsi.JSIApp;
import jsi.JSIPtCurve;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToAddCurPtCurveToPtCurves extends XLoggableCmd {
    private int mNumOfPtCurvesBef = Integer.MIN_VALUE;
    private int mNumOfPtCurvesAft = Integer.MIN_VALUE;
    
    private JSICmdToAddCurPtCurveToPtCurves (XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        JSICmdToAddCurPtCurveToPtCurves cmd = 
                new JSICmdToAddCurPtCurveToPtCurves(app);
        return cmd.execute();
    }
    
    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;
        JSIPtCurve curPtCurve = app.getPtCurveMgr().getCurPtCurve();
        
        if (curPtCurve.getPts().size() >= 2) {
            this.mNumOfPtCurvesBef = app.getPtCurveMgr().getPtCurves().size();
            app.getPtCurveMgr().getPtCurves().add(curPtCurve);
            this.mNumOfPtCurvesAft = app.getPtCurveMgr().getPtCurves().size();
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");
        sb.append(this.mNumOfPtCurvesBef).append("\t");
        sb.append(this.mNumOfPtCurvesAft);
        return sb.toString();
    }
}
