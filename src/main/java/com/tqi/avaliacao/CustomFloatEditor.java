package com.tqi.avaliacao;

import org.springframework.beans.propertyeditors.CustomNumberEditor;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

public class CustomFloatEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text){
        text = text.replaceAll("[R$.]+", "");
        text = text.replaceAll("[,]+", ".");
        if(!text.isEmpty()){
            Float c = Float.parseFloat(text);
            this.setValue(c);
        }

    }

}
