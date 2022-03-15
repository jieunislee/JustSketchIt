
package jsi.cmd;

import jsi.JSIApp;
import jsi.JSIColorChooser;
import jsi.JSIPenMark;
import jsi.scenario.JSIColorScenario;
import x.XApp;
import x.XLoggableCmd;

/**
 *
 * @author oooo
 */
public class JSICmdToRecalcSaturationOfColorChooser extends XLoggableCmd{
    // fields 
    private float mSaturationBef  = Float.NaN;
    private float mSaturationAft  = Float.NaN;
    
    // private constructor
    private JSICmdToRecalcSaturationOfColorChooser(XApp app) {
        super(app);
    }
    
    public static boolean execute(XApp app) {
        JSICmdToRecalcSaturationOfColorChooser cmd = 
                new JSICmdToRecalcSaturationOfColorChooser(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        JSIApp app =(JSIApp) this.mApp;
        JSIColorScenario scenario = JSIColorScenario.getSingleton();
        JSIColorChooser colorChooser = scenario.getColorChooser();
        JSIPenMark penMark = app.getPenMarkMgr().getLastPenMark();
        
        this.mSaturationBef = colorChooser.getSaturation();
        colorChooser.recalcColors(penMark, app.getCanvas2D().getWidth(),
                app.getCanvas2D().getHeight());
        this.mSaturationAft = colorChooser.getSaturation();
        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append("\t");      
        sb.append(this.mSaturationBef).append("\t");    
        sb.append(this.mSaturationAft);    
        return sb.toString();
    }
}
