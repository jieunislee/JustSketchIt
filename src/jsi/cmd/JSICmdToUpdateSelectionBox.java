
package jsi.cmd;

import java.awt.Point;
import jsi.scenario.JSISelectScenario;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToUpdateSelectionBox extends XLoggableCmd {
    private Point mScreenPt = null;
    
    private JSICmdToUpdateSelectionBox(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    public static boolean execute(XApp app, Point pt) {
        JSICmdToUpdateSelectionBox cmd = 
                new JSICmdToUpdateSelectionBox(app, pt);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        JSISelectScenario.SelectScene.getSingleton().
                getSelectionBox().update(this.mScreenPt);
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
