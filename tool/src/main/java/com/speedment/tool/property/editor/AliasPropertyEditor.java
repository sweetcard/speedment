/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.tool.property.editor;

import com.speedment.tool.property.item.DefaultTextFieldItem;
import com.speedment.tool.config.trait.HasAliasProperty;
import com.speedment.tool.property.PropertyEditor;
import java.util.stream.Stream;

/**
 *
 * @author Simon
 * @param <T>  the document type
 */
public class AliasPropertyEditor<T extends HasAliasProperty> implements PropertyEditor<T> {

    @Override
    public Stream<PropertyEditor.Item> fieldsFor(T document) {
        return Stream.of(new DefaultTextFieldItem(
            "Java Alias",
            document.nameProperty(), 
            document.aliasProperty(),
            "The name that will be used for this in generated code.")
        );
    }
}