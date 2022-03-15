
package jsi.cmd;

import jsi.JSIApp;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToIncreaseStrokeWidthForSelectedPtCurves extends XLoggableCmd {
    private float mWBefore = Float.NaN;
    private float mWDelta = Float.NaN;
    private float mWAfter = Float.NaN;
    
    private JSICmdToIncreaseStrokeWidthForSelectedPtCurves(XApp app, float dw){
        super(app);
        this.mWDelta = dw;
    }
    
    public static boolean execute(XApp app, float dw) {
        JSICmdToIncreaseStrokeWidthForSelectedPtCurves cmd = 
            new JSICmdToIncreaseStrokeWidthForSelectedPtCurves(app, dw);
        return cmd.execute();
    } 
    
    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;
        if(app.getPtCurveMgr().getSelectedPtCurveIndex().size() >= 1) {
            for(int c : app.getPtCurveMgr().getSelectedPtCurveIndex()){
                app.getPtCurveMgr().getPtCurves().get(c).
                        increaseStrokeWidth(this.mWDelta);
            }        
        }
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
