package com.epam.project.core.reporter;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestReporter {
    private TestReporter() {
        throw new UnsupportedOperationException("Access denied");
    }

    /**
     * Report to <i>Allure Report System</i> step message.
     * Use <b>String#format</b> parameters for constructing message.
     *
     * @param stepMessage step message
     * @param parameters  message constructing parameters
     * @see String#format(String, Object...)
     */
    public static void reportStep(String stepMessage, Object... parameters) {
        String message = String.format(stepMessage, parameters);
        log.info(message);
        reportAllureStep(message);
    }

    /**
     * Report to <i>Allure Report System</i> debug message.
     * Use <b>String#format</b> parameters for constructing message.
     *
     * @param debugStepMessage debug message
     * @param parameters       message constructing parameters
     * @see String#format(String, Object...)
     */
    public static void reportDebugStep(String debugStepMessage, Object... parameters) {
        String message = String.format(debugStepMessage, parameters);
        log.debug(message);
        reportAllureStep(message);
    }

    /**
     * Report to <i>Allure Report System</i> warning message.
     * Use <b>String#format</b> parameters for constructing message.
     *
     * @param warningStepMessage warning message
     * @param parameters         message constructing parameters
     * @see String#format(String, Object...)
     */
    public static void reportWarningStep(String warningStepMessage, Object... parameters) {
        String message = String.format(warningStepMessage, parameters);
        log.warn(message);
        reportAllureStep(message);
    }

    /**
     * Report to <i>Allure Report System</i> error message.
     * Use <b>String#format</b> parameters for constructing message.
     *
     * @param errorStepMessage error message
     * @param parameters       message constructing parameters
     * @see String#format(String, Object...)
     */
    public static void reportErrorStep(String errorStepMessage, Object... parameters) {
        String message = String.format(errorStepMessage, parameters);
        log.error(message);
        reportAllureStep(message);
    }

    @Step("{0}")
    private static void reportAllureStep(String message) {
    }
}
