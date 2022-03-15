
package jsi.cmd;

import jsi.JSIApp;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToDeselectSelectedPtCurves extends XLoggableCmd {
    private JSICmdToDeselectSelectedPtCurves (XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        JSICmdToDeselectSelectedPtCurves cmd = 
                new JSICmdToDeselectSelectedPtCurves(app);
        return cmd.execute();
    }
    
    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;
       
        app.getPtCurveMgr().getSelectedPtCurveIndex().clear();
        return true;
    }
  
    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName());
        return sb.toString();
    }
}
