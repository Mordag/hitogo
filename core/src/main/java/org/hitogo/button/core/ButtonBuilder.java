package org.hitogo.button.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import org.hitogo.alert.core.Alert;
import org.hitogo.core.HitogoController;
import org.hitogo.core.HitogoHelper;

/**
 * Public api interface for the AlertBuilder. This interface includes all methods that can be
 * used by this builder.
 *
 * @param <C> Type of the AlertBuilder that is using this interface
 * @param <B> Type of the Alert object that will be generated by this builder
 * @see Alert
 * @since 1.0.0
 */
public interface ButtonBuilder<C extends ButtonBuilder, B extends Button> {

    /**
     * Adds a text element to this button which can be used inside the alert implementation. This
     * method implementation will use the provideDefaultButtonTextViewId(Integer) method offered by
     * the HitogoController. This method will define the default view id for this text element.
     * Buttons can have more than one text element. If more than one text element is defined, the
     * method addText(Integer, String) should rather be used to include a view id that can differ
     * between the text elements.
     *
     * @param text Text element for the button object.
     * @return Builder object which has called this method.
     * @see HitogoController
     * @since 1.0.0
     */
    @NonNull
    C addText(@Nullable String text);

    /**
     * Adds a text string resource to this button which can be used inside the alert implementation.
     * This method implementation will use the provideDefaultButtonTextViewId(Integer) method offered
     * by the HitogoController. This method will define the default view id for this text element.
     * Buttons can have more than one text element. If more than one text element is defined, the
     * method addText(Integer, int) should rather be used to include a view id that can differ
     * between the text elements. The string resource will be translated by the builder using the
     * HitogoAccessor.getString(context, int).
     *
     * @param textRes Text element for the alert object.
     * @return Builder object which has called this method.
     * @see HitogoController
     * @see HitogoHelper
     * @since 1.0.0
     */
    @NonNull
    C addText(@StringRes int textRes);

    /**
     * Sets the button listener for this button. If the button listener is null, the button will
     * use the default implementation which will close the button's alert.
     *
     * @param listener ButtonListener for the button
     * @return Builder object which has called this method.
     * @see ButtonListener
     * @see DefaultButtonListener
     * @since 1.0.0
     */
    @NonNull
    <T> C setButtonListener(@Nullable ButtonListener<T> listener);

    /**
     * Sets the button listener for this button. If the button listener is null, the button will
     * use the default implementation which will close the button's alert. The buttonParameter can
     * be used if the button listener should be provided with a certain object that will be needed
     * for this action.
     *
     * @param listener        ButtonListener for the button
     * @param buttonParameter optional button parameter that will be provided to the listener when
     *                        the listener is triggered
     * @return Builder object which has called this method.
     * @see ButtonListener
     * @see DefaultButtonListener
     * @since 1.0.0
     */
    @NonNull
    <T> C setButtonListener(@Nullable ButtonListener<T> listener, T buttonParameter);

    /**
     * Sets the button listener for this button. If the button listener is null, the button will
     * use the default implementation which will close the button's alert. The closeAfterClick
     * parameter defines if the button should close it's alert after being triggered. <b>By default,
     * this value is true.</b>
     *
     * @param listener        ButtonListener for the button
     * @param closeAfterClick true if the button should close it's alert after being trigger,
     *                        false otherwise.
     * @return Builder object which has called this method.
     * @see ButtonListener
     * @see DefaultButtonListener
     * @since 1.0.0
     */
    @NonNull
    <T> C setButtonListener(@Nullable ButtonListener<T> listener, boolean closeAfterClick);

    /**
     * Sets the button listener for this button. If the button listener is null, the button will
     * use the default implementation which will close the button's alert. The buttonParameter can
     * be used if the button listener should be provided with a certain object that will be needed
     * for this action. The closeAfterClick parameter defines if the button should close it's alert
     * after being triggered. <b>By default, this value is true.</b>
     *
     * @param listener        ButtonListener for the button
     * @param buttonParameter optional button parameter that will be provided to the listener when
     *                        the listener is triggered
     * @param closeAfterClick true if the button should close it's alert after being trigger,
     *                        false otherwise.
     * @return Builder object which has called this method.
     * @see ButtonListener
     * @see DefaultButtonListener
     * @since 1.0.0
     */
    @NonNull
    <T> C setButtonListener(@Nullable ButtonListener<T> listener, boolean closeAfterClick, T buttonParameter);

    /**
     * Creates the requested button using the provided builder values. This method is using java
     * reflection to determine the class for the button and it's button params object.
     *
     * @return Requested alert object.
     * @see Button
     * @see ButtonParams
     * @since 1.0.0
     */
    @NonNull
    B build();
}
