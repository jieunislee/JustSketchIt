
package jsi.cmd;

import java.util.ArrayList;
import jsi.JSIApp;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToDeleteSelectedPtCurves extends XLoggableCmd {
    private JSICmdToDeleteSelectedPtCurves (XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        JSICmdToDeleteSelectedPtCurves cmd = 
                new JSICmdToDeleteSelectedPtCurves(app);
        return cmd.execute();
    }
    
    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;
        int size = app.getPtCurveMgr().getSelectedPtCurveIndex().size();
        
        for(int i = size - 1 ; i >= 0 ; i--) {
            int indexToRemove = 
                    (int) app.getPtCurveMgr().getSelectedPtCurveIndex().get(i);
            app.getPtCurveMgr().getPtCurves().remove(indexToRemove);
        }
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
