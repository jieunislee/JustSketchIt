
package jsi.cmd;

import java.awt.Color;
import jsi.JSIApp;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToChangeColorOfSelectedPtCurvesTo extends XLoggableCmd {
    //fields 
    private Color mColor = null;
    
    //private constructor
    private JSICmdToChangeColorOfSelectedPtCurvesTo(XApp app, Color c) {
        super(app);
        this.mColor = c;
    }
    
    public static boolean execute(XApp app, Color c) {
        JSICmdToChangeColorOfSelectedPtCurvesTo cmd = 
                new JSICmdToChangeColorOfSelectedPtCurvesTo(app, c);
        return cmd.execute();
    }
    
    @Override
    protected boolean defineCmd() {
        JSIApp app = (JSIApp) this.mApp;

        for(int Index : app.getPtCurveMgr().getSelectedPtCurveIndex()) {
            app.getPtCurveMgr().getPtCurves().get(Index).setColor(this.mColor);
        }
        app.getPtCurveMgr().getSelectedPtCurveIndex().clear();
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
