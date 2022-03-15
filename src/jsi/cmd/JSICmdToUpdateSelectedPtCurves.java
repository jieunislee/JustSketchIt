
package jsi.cmd;

import jsi.scenario.JSISelectScenario;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToUpdateSelectedPtCurves extends XLoggableCmd {
    private JSICmdToUpdateSelectedPtCurves(XApp app) {
        super(app); 
    }
    
    public static boolean execute(XApp app) {
        JSICmdToUpdateSelectedPtCurves cmd = 
                new JSICmdToUpdateSelectedPtCurves(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        JSISelectScenario.getSingleton().updateSelectedPtCurves();
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName());
        return sb.toString();
    }
}
