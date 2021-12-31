package com.tqi.avaliacao.custom.editors;

import java.beans.PropertyEditorSupport;

public class CustomIntegerEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text){
        text = text.replaceAll("[.]+", "");
        if(!text.isEmpty()){
            Integer c = Integer.parseInt(text);
            this.setValue(c);
        }
    }

}
