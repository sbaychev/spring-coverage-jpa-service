package com.pickcoverage.domain.utils;

import java.beans.PropertyEditorSupport;
import java.util.Locale;

/**
 * Created by stefanbaychev on 3/30/17.
 */
public class ComputeCoverageIndexBinder extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(ComputeCoverageIndex.valueOf(text.toLowerCase(Locale.getDefault())));
    }
}
