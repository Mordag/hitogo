package org.hitogo.button.close;

import org.hitogo.button.view.ViewButtonParams;

/**
 * Params object for the CloseButton.
 *
 * @see ViewButtonParams
 * @see org.hitogo.button.core.ButtonParams
 * @since 1.0.0
 */
public class CloseButtonParams extends ViewButtonParams {

    private Integer iconId;
    private Integer clickId;

    @Override
    protected void initialiseViewIds(Integer holderIconId, Integer holderClickId) {
        Integer defaultIconId = getController().provideDefaultButtonCloseIconId(getButtonType());
        defaultIconId = defaultIconId != null ? defaultIconId : -1;
        iconId = holderIconId != null ? holderIconId : defaultIconId;

        clickId = holderClickId != null ?
                holderClickId :
                getController().provideDefaultButtonCloseClickId(getButtonType());
    }

    @Override
    public int getIconId() {
        return iconId;
    }

    @Override
    public Integer getClickId() {
        return clickId;
    }

    @Override
    public boolean hasButtonView() {
        return getIconId() != -1;
    }
}
