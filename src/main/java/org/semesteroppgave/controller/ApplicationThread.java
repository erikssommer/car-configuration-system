package org.semesteroppgave.controller;

/**
 * Interface for controllers to load files in their own thread.
 * The controllers must implement 'intitialize' as well.
 * Used in the 'StartThread' class to enable / disable gui elements
 */

public interface ApplicationThread extends ApplicationController {

    void disableGUI();

    void enableGUI();

}
