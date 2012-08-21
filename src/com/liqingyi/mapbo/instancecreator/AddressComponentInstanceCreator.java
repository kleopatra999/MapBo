package com.liqingyi.mapbo.instancecreator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.InstanceCreator;
import com.liqingyi.mapbo.model.google.AddressComponent;

public class AddressComponentInstanceCreator implements
		InstanceCreator<ArrayList<AddressComponent>> {

	@Override
	public ArrayList<AddressComponent> createInstance(Type arg0) {
		ArrayList<AddressComponent> list = new ArrayList<AddressComponent>();
		return list;
	}

}
