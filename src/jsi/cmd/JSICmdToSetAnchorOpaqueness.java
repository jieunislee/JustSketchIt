
package jsi.cmd;

import jsi.JSIColorChooser;
import jsi.scenario.JSIColorScenario;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToSetAnchorOpaqueness extends XLoggableCmd{
    // fields 
    private float mAnchorOpaqueness  = Float.NaN;
    
    // private constructor
    private JSICmdToSetAnchorOpaqueness(XApp app) {
        super(app);
    }
    
    public static boolean execute(XApp app) {
        JSICmdToSetAnchorOpaqueness cmd = new JSICmdToSetAnchorOpaqueness(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        JSIColorScenario scenario = JSIColorScenario.getSingleton();
        JSIColorChooser colorChooser = scenario.getColorChooser();
        colorChooser.setAnchorOpaqueness();
        this.mAnchorOpaqueness = colorChooser.getOpaqueness();
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");     
        sb.append(this.mAnchorOpaqueness);       
        return sb.toString();
    }
}
