package panels;

import enums.ActionType;

public class ActionSwitch extends Switch {
    private final ActionType actionType;

    public ActionSwitch(ActionType actionType) {
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
