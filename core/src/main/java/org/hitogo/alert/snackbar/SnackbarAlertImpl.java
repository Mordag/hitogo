package org.hitogo.alert.snackbar;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import org.hitogo.alert.core.AlertImpl;
import org.hitogo.button.core.Button;

import java.security.InvalidParameterException;

/**
 * Implementation for the snackbar alert. This alert requires to have at least one text element.
 *
 * @since 1.0.0
 */
public class SnackbarAlertImpl extends AlertImpl<SnackbarAlertParams> implements SnackbarAlert {

    @Override
    protected void onCheck(@NonNull SnackbarAlertParams params) {
        super.onCheck(params);

        if (params.getTextMap().size() == 0) {
            throw new InvalidParameterException("You need to add a text to this snackbar.");
        }

        if (params.getTextMap().size() > 1) {
            Log.i(SnackbarAlertImpl.class.getName(),
                    "The snackbar alert only supports one text element.");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Object onCreateOther(LayoutInflater inflater, @NonNull Context context, @NonNull SnackbarAlertParams params) {
        Snackbar snackbar = Snackbar.make(getRootView(),
                params.getTextMap().valueAt(0),
                params.getDuration());

        if (!params.getButtons().isEmpty()) {
            final Button button = params.getButtons().get(0);

            if (button != null) {
                snackbar.setAction(button.getParams().getTextMap().valueAt(0), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        button.getParams().getListener().onClick(SnackbarAlertImpl.this,
                                button.getParams().getButtonParameter());

                        if (button.getParams().isClosingAfterClick() && isAttached()) {
                            close();
                        }
                    }
                });
            }
        }

        if (params.getActionTextColor() != null) {
            snackbar.setActionTextColor(params.getActionTextColor());
        }

        if (params.getColorStates() != null) {
            snackbar.setActionTextColor(params.getActionTextColor());
        }

        snackbar.addCallback(new Snackbar.Callback() {

            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                if (isAttached()) {
                    close();
                }
            }
        });

        return snackbar;
    }

    @Override
    protected void onShowDefault(@NonNull Context context) {
        super.onShowDefault(context);
        ((Snackbar) getOther()).show();
    }

    @Override
    protected void onCloseDefault(@NonNull Context context) {
        super.onCloseDefault(context);
        ((Snackbar) getOther()).dismiss();
    }
}
