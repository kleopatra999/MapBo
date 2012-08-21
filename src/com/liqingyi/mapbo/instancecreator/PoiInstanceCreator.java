package com.liqingyi.mapbo.instancecreator;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.InstanceCreator;
import com.liqingyi.mapbo.model.Poi;

public class PoiInstanceCreator implements InstanceCreator<ArrayList<Poi>> {

	@Override
	public ArrayList<Poi> createInstance(Type arg0) {
		ArrayList<Poi> list = new ArrayList<Poi>();
		return list;
	}

}
