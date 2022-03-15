
package jsi.cmd;

import jsi.scenario.JSISelectScenario;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToDestroySelectionBox extends XLoggableCmd {
    private JSICmdToDestroySelectionBox(XApp app) {
        super(app);
    }
    
    public static boolean execute(XApp app) {
        JSICmdToDestroySelectionBox cmd = new JSICmdToDestroySelectionBox(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        JSISelectScenario.SelectScene.getSingleton().setSelectionBox(null);
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName());
        return sb.toString();
    }
}
