
package jsi.cmd;

import java.awt.Point;
import jsi.JSISelectionBox;
import jsi.scenario.JSISelectScenario;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToCreateSelectionBox extends XLoggableCmd {
    //field
    private Point mScreenPt = null;
    
    //private constructor
    private JSICmdToCreateSelectionBox(XApp app, Point pt) {
        super(app);
        this.mScreenPt = pt;
    }
    
    public static boolean execute(XApp app, Point pt) {
        JSICmdToCreateSelectionBox cmd = new JSICmdToCreateSelectionBox(app, pt);
        return cmd.execute();
    }
    
    @Override
    protected boolean defineCmd() {
        JSISelectScenario.SelectScene.getSingleton().
            setSelectionBox(new JSISelectionBox(this.mScreenPt));
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
