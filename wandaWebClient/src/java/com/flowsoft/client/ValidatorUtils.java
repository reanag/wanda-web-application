package com.flowsoft.client;

import java.util.Collection;
import java.util.Hashtable;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;

public class ValidatorUtils {
	public static Hashtable<Field<?>, String> table = new Hashtable<Field<?>, String>();

	private ValidatorUtils() {
	}

	public static void installSingleValidator(Component c, Class<?> className) {
		if (c instanceof Field<?>) {
			Field<?> field = (Field<?>) c;

			Collection<Validator> validators = field.getValidators();

			if (validators == null || validators.isEmpty()) {

				field.addValidator(new BeanValidator(className, table
						.get(field)));
			}
		}
	}
}