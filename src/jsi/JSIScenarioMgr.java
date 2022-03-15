
package jsi;

import jsi.scenario.JSIColorScenario;
import jsi.scenario.JSIDefaultScenario;
import jsi.scenario.JSIDrawScenario;
import jsi.scenario.JSINavigateScenario;
import jsi.scenario.JSISelectScenario;
import x.XScenarioMgr;

/**
 *
 * @author oooo
 */
public class JSIScenarioMgr extends XScenarioMgr {

    public JSIScenarioMgr(JSIApp app) {
        super(app);
    }
    
    @Override
    protected void addScenarios() {
        this.mScenarios.add(JSIDefaultScenario.createSingleton(this.mApp));
        this.mScenarios.add(JSIDrawScenario.createSingleton(this.mApp));
        this.mScenarios.add(JSISelectScenario.createSingleton(this.mApp));
        this.mScenarios.add(JSINavigateScenario.createSingleton(this.mApp));
        this.mScenarios.add(JSIColorScenario.createSingleton(this.mApp));
    }
  
    @Override
    protected void setInitCurScene() {
        this.setCurScene(JSIDefaultScenario.ReadyScene.getSingleton());
    }
}