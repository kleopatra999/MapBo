package com.liqingyi.mapbo.instancecreator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.InstanceCreator;
import com.liqingyi.mapbo.model.google.Aspect;

public class AspectInstanceCreator implements
		InstanceCreator<ArrayList<Aspect>> {

	@Override
	public ArrayList<Aspect> createInstance(Type arg0) {
		ArrayList<Aspect> list = new ArrayList<Aspect>();
		return list;
	}

}
